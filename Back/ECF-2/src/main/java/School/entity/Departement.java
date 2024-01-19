package School.entity;

import javax.persistence.*;

@Entity
@Table(name = "departement")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_departement", nullable = false)
    private Integer id;
    @Column(name="name_departement")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_teacher", nullable = false)
    private Teacher idTeacher;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_grade", nullable = false)
    private Grade idGrade;

    public Departement() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Departement(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}