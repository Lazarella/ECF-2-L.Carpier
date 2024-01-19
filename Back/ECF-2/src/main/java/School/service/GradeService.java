package School.service;

import School.dao.impl.GradeDAOImpl;
import School.entity.Grade;

import java.util.List;

public class GradeService extends  BaseService {
    public GradeDAOImpl gradeDAO = new GradeDAOImpl(session);

    public GradeService() {
        super();
    }
    public boolean create (Grade d) {
        try {
        session = sessionFactory.openSession();
            session.getTransaction().begin();
            gradeDAO.create(d);
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
        try {
        session = sessionFactory.openSession();
            session.getTransaction().begin();
            gradeDAO.delete(id);
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
    public Grade findByName(String str) {

        try {
            session = sessionFactory.openSession();
            return gradeDAO.findByName(str);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Grade findById(int id) {
        try {
            session = session.getSessionFactory().openSession();
            Grade grade = null;
            grade = (Grade) gradeDAO.findById(id);
            return grade;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public List<Grade> findAll() {
        try {
            session = sessionFactory.openSession();
            return gradeDAO.findAll();
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
