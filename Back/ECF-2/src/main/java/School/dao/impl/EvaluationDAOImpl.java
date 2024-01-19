package School.dao.impl;

import School.dao.Repository;
import School.entity.Evaluation;
import School.entity.Evaluation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EvaluationDAOImpl implements Repository<Evaluation> {

    private Session session;

    public EvaluationDAOImpl(Session session) {
        this.session = session;
    }
    @Override
    public boolean create(Evaluation e) {

        session.save(e);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        session.delete(id);
        return true;
    }

    @Override
    public Evaluation findByName(String str) {
       return null;
    }
    // je n'en ai pas besoin je la laisse vide.

    @Override
    public Evaluation findById(int id) {
        return session.get(Evaluation.class, id);
    }

    @Override
    public List findAll() {

        String sql = "SELECT * FROM evaluation";
        Query<Evaluation> query = session.createNativeQuery(sql, Evaluation.class);
        return query.getResultList();
    }

}
