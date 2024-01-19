package School.service;

import School.dao.impl.SubjectDAOImpl;
import School.dao.impl.SubjectDAOImpl;
import School.entity.Subject;
import School.entity.Subject;

import java.util.List;

public class SubjectService extends BaseService{
    public SubjectService() {super();}
    public SubjectDAOImpl subjectDAO = new SubjectDAOImpl(session);

    public boolean create (Subject s) {
        session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            subjectDAO.create(s);
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
            subjectDAO.delete(id);
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
            subjectDAO.findByName(str);
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


    public Subject findById(int id) {
        try {
            session = session.getSessionFactory().openSession();
            Subject subject = null;
            subject = (Subject) subjectDAO.findById(id);
            return subject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public List<Subject> findAll() {
        try {
            session = sessionFactory.openSession();
            return subjectDAO.findAll();
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
