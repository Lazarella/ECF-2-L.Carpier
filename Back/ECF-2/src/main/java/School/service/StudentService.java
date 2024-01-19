package School.service;


import School.dao.impl.StudentDAOImpl;
import School.entity.Student;

import java.util.List;

public class StudentService extends BaseService{
    public StudentService() {super();}
    public StudentDAOImpl studentDAO = new StudentDAOImpl(session);

    public boolean create (Student s) {
        session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            studentDAO.create(s);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
            return true;

        }
    }


    public boolean delete(Long id) {
        session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            studentDAO.delete(id);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
            return true;
        }
    }


    public boolean findByName(String str) {

        try {
            session = sessionFactory.openSession();
            studentDAO.findByName(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public Student findById(int id) {
        try {
            session = session.getSessionFactory().openSession();
            Student student = null;
            student = (Student) studentDAO.findById(id);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public List<Student> findAll() {
        try {
            session = sessionFactory.openSession();
            return studentDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
