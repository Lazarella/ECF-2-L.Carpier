package School.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "evaluation")
public class Evaluation {
    @EmbeddedId
    private EvaluationId id;

    @MapsId("idSubject")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_subject", nullable = false)
    private Subject idSubject;

    @MapsId("idStudent")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_student", nullable = false)
    private Student idStudent;

    public Evaluation() {
    }

    @Column(name = "id_evaluation")
    private Integer idEvaluation;

    @Column(name = "_value", precision = 3, scale = 1)
    private BigDecimal value;

    @Column(name = "comment")
    private String comment;

    public EvaluationId getId() {
        return id;
    }

    public void setId(EvaluationId id) {
        this.id = id;
    }

    public Subject getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Subject idSubject) {
        this.idSubject = idSubject;
    }

    public Student getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Student idStudent) {
        this.idStudent = idStudent;
    }

    public Integer getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(Integer idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}