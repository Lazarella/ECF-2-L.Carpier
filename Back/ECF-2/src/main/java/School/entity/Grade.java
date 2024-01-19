package School.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @Column(name = "id_grade", nullable = false)
    private Integer id;

    @Column(name = "name_grade", length = 50)
    private String nameGrade;

    @Column(name = "level_grade", length = 50)
    private String levelGrade;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_departement", nullable = false)
    private Departement idDepartement;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_student", nullable = false)
    private List<Student> idStudent;

    public Grade() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameGrade() {
        return nameGrade;
    }

    public void setNameGrade(String nameGrade) {
        this.nameGrade = nameGrade;
    }

    public String getLevelGrade() {
        return levelGrade;
    }

    public void setLevelGrade(String levelGrade) {
        this.levelGrade = levelGrade;
    }

    public Departement getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Departement idDepartement) {
        this.idDepartement = idDepartement;
    }

}