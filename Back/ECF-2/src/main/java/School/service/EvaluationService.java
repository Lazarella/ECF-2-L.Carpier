package School.service;

import School.dao.impl.EvaluationDAOImpl;
import School.entity.Evaluation;

import java.util.List;

public class EvaluationService extends BaseService{

    EvaluationDAOImpl evaluationDAO = new EvaluationDAOImpl(session);
    public EvaluationService() {super();}


    public boolean create (Evaluation e) {
        session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            evaluationDAO.create(e);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
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
            evaluationDAO.delete(id);
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
            evaluationDAO.findByName(str);
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


    public Evaluation findById(int id) {
        try {
            session = session.getSessionFactory().openSession();
            Evaluation evaluation = null;
            evaluation = (Evaluation) evaluationDAO.findById(id);
            return evaluation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public List<Evaluation> findAll() {
        try {
            session = sessionFactory.openSession();
            return evaluationDAO.findAll();
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
