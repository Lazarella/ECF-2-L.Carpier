package School.service;

import School.dao.Repository;
import School.dao.impl.DepartementDAOImp;
import School.entity.Departement;
import org.hibernate.query.Query;

import java.util.List;


public class DepartementService extends BaseService  {
    public DepartementService() {super();}
    public DepartementDAOImp departementDao = new DepartementDAOImp(session);

    public boolean create (Departement d) {
        session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            d.setName(d.getName());
            departementDao.create(d);
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
            departementDao.delete(id);
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


    public Departement findByName(String str) {

        try {
            session = sessionFactory.openSession();
            return departementDao.findByName(str);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public Departement findById(int id) {
        try {
            session = session.getSessionFactory().openSession();
            Departement departement = null;
            departement = (Departement) departementDao.findById(id);
            return departement;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public List<Departement> findAll() {
        try {
        session = sessionFactory.openSession();
        return departementDao.findAll();
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
