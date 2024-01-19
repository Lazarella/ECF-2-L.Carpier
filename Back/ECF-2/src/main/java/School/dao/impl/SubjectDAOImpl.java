package School.dao.impl;

import School.dao.Repository;
import School.entity.Subject;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SubjectDAOImpl implements Repository<Subject> {
    private Session session;

    public SubjectDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public boolean create(Subject s) {

        session.save(s);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        session.delete(id);
        return true;
    }

    @Override
    public Subject findByName(String str) {
        String hql = "FROM Subject WHERE titled = :name";
        Query query = session.createQuery(hql, Subject.class);
        query.setParameter("name", str);
        return (Subject) query.uniqueResult();
    }

    @Override
    public Subject findById(int id) {
        return session.get(Subject.class, id);
    }

    @Override
    public List findAll() {

        String sql = "SELECT * FROM subject";
        Query<Subject> query = session.createNativeQuery(sql, Subject.class);
        return query.getResultList();
    }
}
