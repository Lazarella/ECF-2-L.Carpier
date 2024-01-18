package School.service;

import School.dao.impl.Repository;
import School.entity.Departement;
import org.hibernate.query.Query;

import java.util.List;


public class DepartementService extends BaseService implements Repository<Departement> {
    public DepartementService() {super();}

    public boolean create (Departement d) {
        session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            d.setName(d.getName());
            session.save(d);
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

    @Override
    public boolean delete(Long id) {
        session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.delete(id);
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

    @Override
    public boolean findByName(String str) {

            session = sessionFactory.openSession();
        try {
            String sql = "SELECT * FROM departement WHERE name_departement = :name";
            Query query = session.createNativeQuery(sql, Departement.class);
            query.setParameter("name", str);
            Departement departement = (Departement) query.getSingleResult();

            if (departement == null) {
                System.out.println("Ce département n'existe pas!");
                return false;
            }
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

    @Override
    public Departement findById(int id) {
        session = session.getSessionFactory().openSession();
        Departement departement = null;

        departement = (Departement) session.get(Departement.class, id);
        session.close();
        return departement;
    }

    @Override
    public List<Departement> findAll() {
        session = sessionFactory.openSession();
        try {
            String sql = "SELECT * FROM departement";
            Query<Departement> query = session.createNativeQuery(sql, Departement.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Remplacez par un logger approprié dans votre projet
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
