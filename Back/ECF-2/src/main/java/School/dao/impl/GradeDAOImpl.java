package School.dao.impl;

import School.dao.Repository;
import School.entity.Departement;
import School.entity.Grade;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class GradeDAOImpl implements Repository<Grade> {
    private Session session;

    public GradeDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public boolean create(Grade g) {

        session.save(g);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        session.delete(id);
        return true;
    }

    @Override
    public Grade findByName(String str) {
        String hql = "FROM Grade WHERE nameGrade = :name";
        Query query = session.createQuery(hql, Departement.class);
        query.setParameter("name", str);
        return (Grade) query.uniqueResult();
    }

    @Override
    public Grade findById(int id) {

        return session.get(Grade.class, id);
    }

    @Override
    public List findAll() {

        String sql = "SELECT * FROM grade";
        Query<Departement> query = session.createNativeQuery(sql, Departement.class);
        return query.getResultList();
    }
}
