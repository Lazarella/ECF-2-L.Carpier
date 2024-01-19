package School.dao.impl;

import School.dao.Repository;
import School.entity.Departement;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DepartementDAOImp implements Repository <Departement> {

    private Session session;

    public DepartementDAOImp(Session session) {
        this.session = session;
    }

    @Override
    public boolean create(Departement d) {

        session.save(d);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        session.delete(id);
        return true;
    }

    @Override
    public Departement findByName(String str) {
            String hql = "FROM Departement WHERE name = :name";
            Query query = session.createQuery(hql, Departement.class);
            query.setParameter("name", str);
            return (Departement) query.uniqueResult();
        }

    @Override
    public Departement findById(int id) {
        return session.get(Departement.class, id);
    }

    @Override
    public List findAll() {

        String sql = "SELECT * FROM departement";
        Query<Departement> query = session.createNativeQuery(sql, Departement.class);
        return query.getResultList();
    }
}
