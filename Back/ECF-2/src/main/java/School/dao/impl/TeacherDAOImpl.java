package School.dao.impl;

import School.dao.Repository;
import School.entity.Departement;
import School.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TeacherDAOImpl implements Repository<Teacher> {

    private Session session;

    public TeacherDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public boolean create(Teacher t) {
        session.save(t);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        session.delete(id);
        return true;
    }

    @Override
    public Teacher findByName(String str) {
        String hql = "FROM Teacher WHERE nameTeacher = :name";
        Query query = session.createQuery(hql, Teacher.class);
        query.setParameter("name", str);
        return (Teacher) query.uniqueResult();
    }

    @Override
    public Teacher findById(int id) {
        return session.get(Teacher.class, id);
    }

    @Override
    public List<Teacher> findAll() {
        String sql = "SELECT * FROM teacher";
        Query<Teacher> query = session.createNativeQuery(sql, Teacher.class);
        return query.getResultList();
    }
}
