package School.controller;

import School.entity.*;
import School.service.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.time.Period;
import java.util.regex.Pattern;

public class IHM {

    private StudentService studentService;
    private TeacherService teacherService;
    private DepartementService departementService;
    private GradeService gradeService;
    private EvaluationService evaluationService;
    private SubjectService subjectService;
    private Scanner scan;

    public IHM() {
        this.studentService = new StudentService();
        this.teacherService = new TeacherService();
        this.departementService = new DepartementService();
        this.gradeService = new GradeService();
        this.evaluationService = new EvaluationService();
        this.subjectService = new SubjectService();
        this.scan = new Scanner(System.in);
    }

    public void start() {
        String choice;
        do {

            menu();
            choice = scan.nextLine();
            switch (choice) {
                case "1":
                    createDepartement();
                    break;
                case "2":
                    createTeacher();
                    break;
                case "3":
                    createStudent();
                    break;
                case "4":
                    createSubject();
                    break;
                case "5":
                    addEvaluation();
                    break;
                case "6":
                    addGrade();
                    break;
                case "7":
                    displayStudent();
                    break;
                case "8":
                    displayStudentBySubject();
                    break;
                case "9":
                    dispalyEvaluationFromStudent();
                    break;
                case "10":
                    displayStudentByDepartement();
                    break;
                case "11":
                    displayStudentByLevelGrade();
                    break;
                case "12":
                    deleteStudent();
                    break;
                case "13":
                    deleteStudentFromGrade();
                    break;
                case "14":
                    deleteDepartement();
                    break;
                default:
                    System.out.println("Je n'ai pas compris");
            }
        } while (!choice.equals("0"));
        //sessionFactory.close();
    }

    private void menu() {
        System.out.println("⁂ ﾉ◕ヮ◕)ﾉ*:･ﾟ✧  Bienvenue à Poudlard      ᓚᘏᗢ ⁂ ");
        System.out.println("1 -- Créer une maison (ou un département)");
        System.out.println("2 -- Enregistrer un.e nouvel.le enseignant.e");
        System.out.println("3 -- Enregistrer un.e nouvel.le élève");
        System.out.println("4 -- Enregistrer une nouvelle matière");
        System.out.println("5 -- Noter un.e élève");
        System.out.println("6 -- Créer une classe\n");
        System.out.println("7 -- Consulter la liste des élèves");
        System.out.println("8 -- Consulter le nombre d'élève suivant une matière en particulier");
        System.out.println("9 -- Consulter les notes d'un.e élève");
        System.out.println("10 -- Consulter le nombre d'élève appartenant à une maison (ou département)");
        System.out.println("11 -- Consulter la liste des élève par niveau\n");
        System.out.println("12 -- Supprimer un.e élève");
        System.out.println("13 -- Supprimer tous les élèves d'une classe");
        System.out.println("14 -- Supprimer une maison (ou département ");
        System.out.println("0 -- Quitter ");


    }

    private void createDepartement() {
        System.out.println("Quel est le nom de notre nouvelle maison?");

        String departementName = scan.nextLine().trim();

        try {
            departementService.create(new Departement(departementName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTeacher() {
        Random rand = new Random();
        Teacher teacher = new Teacher();
        int randomNumber = rand.nextInt(10000);
        teacher.setRegistration("T" + String.valueOf(randomNumber));

        System.out.println("  (｡･∀･)ﾉﾞ  Bienvenue à la nouvelle recrue （＾∀＾●）ﾉｼ");
        System.out.println("Quel est votre prénom?");
        String teacherName = getInput(scan);
        teacher.setNameTeacher(teacherName);

        System.out.println("Quel est votre nom de famille?");
        String teacherLastName = getInput(scan);
        teacher.setLastnameTeacher(teacherLastName);

        System.out.println("Quelle est votre Date de naissance (AAAA-MM-JJ)");
        LocalDate birthdate = null;
        boolean isValidDate = false;
        while (!isValidDate) {
            System.out.println("Quelle est votre date de naissance (AAAA-MM-JJ) ?");
            String dateInput = scan.nextLine().trim();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                birthdate = LocalDate.parse(dateInput, formatter);
                if (Period.between(birthdate, LocalDate.now()).getYears() < 18) {
                    System.out.println("Vous devez avoir au moins 18 ans.");
                } else {
                    teacher.setBirthdateTeacher(birthdate);
                    isValidDate = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Format de date invalide. Veuillez réessayer.");
            }
        }
        System.out.println("A quelle maison êtes vous rattaché?");
        String teacherDepartement = scan.next();

        try {
            Departement departement = departementService.findByName(teacherDepartement);
            if (departement == null) {
                System.out.println("Cette maison n'existe pas! ");
            } else {
                teacher.setIdDepartement(departement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Etes vous le professeur référent de cette Maison O/N");
        String teacherIsHead = scan.next().trim().toUpperCase();

        switch (teacherIsHead) {
            case "O" -> {
                System.out.println("On va vérifier ça petit malin");
                teacher.setIsHeadDepartment(true);
                //todo méthode vérification : 1 - chercher tout les professeurs d'un département dont le bool Is head est set à true ; si le résultat est nul, alors le nouvel élément peut devenir chef du département
            }
            case "N" -> teacher.setIsHeadDepartment(false);

            default -> System.out.println("Je n'ai pas compris");

        }
        teacher.setIsPrincipal(false);
        teacherService.create(teacher);
    }

    private void createStudent() {
        Student student = new Student();
        System.out.println("  (｡･∀･)ﾉﾞ  Bienvenue au nouvel.le élève! （＾∀＾●）ﾉｼ");

        System.out.println("Veuillez entrer le prénom de l'élève");
        String name = getInput(scan);
        student.setNameStudent(name);

        System.out.println("Veuillez entrer le nom de famille de l'élève");
        String lastName = getInput(scan);
        student.setLastNameStudent(lastName);

        System.out.println("Veuillez entrez la date de naissance de l'élève");
        String dateInput = scan.nextLine().trim();
        LocalDate birthdate = null;
        boolean isValidDate = false;
        while (!isValidDate) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                birthdate = LocalDate.parse(dateInput, formatter);
                student.setBirthdateStudent(birthdate);
            } catch (DateTimeParseException e) {
                System.out.println("Format de date invalide. Veuillez réessayer.");
            }
        }

        System.out.println("Veuillez entrer l'email de l'élève (au cas ou les chouettes fassent grève)");
        String mail = scan.next();
        Boolean checkMail = Pattern.matches(".*@gmail\\.com$", mail); // méthode de codeurjava.com
        if (checkMail) {
            student.setMail(mail);
        } else {
            System.out.println("Le mail de l'étudiant doit finir par '@gmail.com', on a des actions!");
        }
        System.out.println("A quel classe apartient lélève?");
        String grade = scan.next();
        try {
            Grade gradeStudent = gradeService.findByName(grade);
            if (grade == null) {
                System.out.println("Cette classe n'existe pas! ");
            } else {
                student.setIdGrade(gradeStudent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        studentService.create(student);

    }

    private void createSubject() {
        System.out.println("Etoffons notre catalogue de matières!");
        Subject subject = new Subject();

        System.out.println("Quelle est le nom de la nouvelle matière?");
        String titled = scan.nextLine().trim();
        subject.setTitled(titled);

        System.out.println("Décrivez la matière en quelques mots");
        String description = scan.nextLine().trim();
        subject.setDescription(description);

        System.out.println("Combien de temps dure un cours? (en heure)");
        int duration = scan.nextInt();
        subject.setDuration(duration);

        System.out.println("Quel est le coefficient de la matière (de 1 à 5 inclus)");
        BigDecimal coef = scan.nextBigDecimal(); //Jpa buddy a généré cette étonnant type...
        if (coef.compareTo(BigDecimal.ONE) <= 5 && coef.compareTo(BigDecimal.ONE) >= 1) {
            subject.setCoefficient(coef);
        } else {
            System.out.println("Heureusement, vous ne vouliez pas enseigner les mathématiques o(≧∀≦)o");
        }

        subjectService.create(subject);

    }

    private void addEvaluation() {
        Evaluation eval = new Evaluation();
        System.out.println("C'est l'heure dévaluer ces chers petits");
        System.out.println("Entrer l'identifiant numérique de l'élève à évaluer");
        int idStudent = scan.nextInt();
        Student student = studentService.findById(idStudent);
        if (student != null) {
            eval.setIdStudent(student);
        } else {
            System.out.println("l'élève est introuvable");
        }
        System.out.println("pour quelle matière l'élève va-t-il être évaluer? (entrez l'id");
        int idSubject = scan.nextInt();
        Subject subject = subjectService.findById(idSubject);
        if (subject!= null) {
            eval.setIdSubject(subject);
        } else {
            System.out.println("Cette matière n'est pas enseignée chez nous on dirait ...");
        }
        System.out.println("Quelle est la valeur de la note?");
       BigDecimal valueEval = scan.nextBigDecimal();
       // la valeur est vérifié dans la base de donnée
       eval.setValue(valueEval);
        System.out.println("Ajoutez un commentaire");
        String comment = scan.nextLine().trim();
        eval.setComment(comment);
        evaluationService.create(eval);
    }

    private void addGrade(){

    }
    private void displayStudent(){

    }
    private void displayStudentBySubject(){

    }
    private void dispalyEvaluationFromStudent(){

    }
    private void displayStudentByDepartement(){

    }
    private void displayStudentByLevelGrade(){}
    private void deleteStudent(){}
    private void deleteStudentFromGrade(){}
    private void deleteDepartement(){}
    private String getInput(Scanner scan){
            String input = "";
            boolean isValid = false;
            while (!isValid) {
                try {
                    input = scan.nextLine().trim();
                    if (input.isEmpty() || input.length() < 3 || !input.matches("[a-zA-Z]+")) {
                        throw new IllegalArgumentException("Le nom (ou prénom) doit faire plus de trois caractères et ne doit contenir que des lettres !");
                    }
                    isValid = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            return input;
        }


    }



