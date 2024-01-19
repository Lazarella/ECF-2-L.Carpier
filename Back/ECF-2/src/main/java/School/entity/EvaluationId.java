package School.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EvaluationId implements Serializable {
    private static final long serialVersionUID = 8220575220269133758L;
    @Column(name = "id_subject", nullable = false)
    private Integer idSubject;

    @Column(name = "id_student", nullable = false, length = 50)
    private String idStudent;

    public EvaluationId() {
    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EvaluationId entity = (EvaluationId) o;
        return Objects.equals(this.idSubject, entity.idSubject) &&
                Objects.equals(this.idStudent, entity.idStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubject, idStudent);
    }

}