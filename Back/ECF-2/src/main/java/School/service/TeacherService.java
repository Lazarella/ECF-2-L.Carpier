package School.service;

import School.dao.impl.DepartementDAOImp;
import School.dao.impl.TeacherDAOImpl;
import School.entity.Departement;
import School.entity.Teacher;

import java.util.List;

public class TeacherService extends BaseService {
    public TeacherDAOImpl teacherDAO = new TeacherDAOImpl(session);

    public TeacherService() {super();}

    public boolean create (Teacher t) {
        session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            t.setNameTeacher(t.getNameTeacher());
            teacherDAO.create(t);
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
            teacherDAO.delete(id);
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
            teacherDAO.findByName(str);
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


    public Teacher findById(int id) {
        try {
            session = session.getSessionFactory().openSession();
            Teacher teacher = null;
           teacher = (Teacher) teacherDAO.findById(id);
            return teacher;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public List<Teacher> findAll() {
        try {
            session = sessionFactory.openSession();
            return teacherDAO.findAll();
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
