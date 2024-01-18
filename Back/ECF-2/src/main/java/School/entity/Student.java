package School.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "id_student", nullable = false, length = 50)
    private String idStudent;

    @Column(name = "name_student", length = 50)
    private String nameStudent;

    @Column(name = "last_name_student", length = 50)
    private String lastNameStudent;

    @Column(name = "birthdate_student")
    private LocalDate birthdateStudent;

    @Column(name = "mail", length = 50)
    private String mail;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_grade", nullable = false)
    private Grade idGrade;

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getLastNameStudent() {
        return lastNameStudent;
    }

    public void setLastNameStudent(String lastNameStudent) {
        this.lastNameStudent = lastNameStudent;
    }

    public LocalDate getBirthdateStudent() {
        return birthdateStudent;
    }

    public void setBirthdateStudent(LocalDate birthdateStudent) {
        this.birthdateStudent = birthdateStudent;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Grade getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(Grade idGrade) {
        this.idGrade = idGrade;
    }

}