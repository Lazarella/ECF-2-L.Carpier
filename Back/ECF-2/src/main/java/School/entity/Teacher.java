package School.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "id_teacher", nullable = false)
    private Integer id;

    @Column(name = "registration", length = 50)
    private String registration;

    @Column(name = "name_teacher", length = 50)
    private String nameTeacher;

    @Column(name = "lastname_teacher", length = 50)
    private String lastnameTeacher;

    @Column(name = "birthdate_teacher")
    private LocalDate birthdateTeacher;

    @Column(name = "level_teacher")
    private Integer levelTeacher;

    @Column(name = "is_principal")
    private Boolean isPrincipal;

    @Column(name = "is_head_department")
    private Boolean isHeadDepartment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public String getLastnameTeacher() {
        return lastnameTeacher;
    }

    public void setLastnameTeacher(String lastnameTeacher) {
        this.lastnameTeacher = lastnameTeacher;
    }

    public LocalDate getBirthdateTeacher() {
        return birthdateTeacher;
    }

    public void setBirthdateTeacher(LocalDate birthdateTeacher) {
        this.birthdateTeacher = birthdateTeacher;
    }

    public Integer getLevelTeacher() {
        return levelTeacher;
    }

    public void setLevelTeacher(Integer levelTeacher) {
        this.levelTeacher = levelTeacher;
    }

    public Boolean getIsPrincipal() {
        return isPrincipal;
    }

    public void setIsPrincipal(Boolean isPrincipal) {
        this.isPrincipal = isPrincipal;
    }

    public Boolean getIsHeadDepartment() {
        return isHeadDepartment;
    }

    public void setIsHeadDepartment(Boolean isHeadDepartment) {
        this.isHeadDepartment = isHeadDepartment;
    }

}