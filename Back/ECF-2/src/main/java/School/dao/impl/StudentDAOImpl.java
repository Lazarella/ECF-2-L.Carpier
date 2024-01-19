package School.dao.impl;

import School.dao.Repository;
import School.entity.Student;
import School.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements Repository <Student> {
    private Session session;

    public StudentDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public boolean create(Student s) {

        session.save(s);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        session.delete(id);
        return true;
    }

    @Override
    public Student findByName(String str) {
        String hql = "FROM Student WHERE nameStudent = :name";
        Query query = session.createQuery(hql, Student.class);
        query.setParameter("name", str);
        return (Student) query.uniqueResult();
    }

    @Override
    public Student findById(int id) {
        return session.get(Student.class, id);
    }

    @Override
    public List findAll() {

        String sql = "SELECT * FROM student";
        Query<Student> query = session.createNativeQuery(sql, Student.class);
        return query.getResultList();
    }
}
