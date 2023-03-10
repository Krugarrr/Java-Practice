package gigachad.pussy.dao;

import gigachad.pussy.hibernate.HibernateSessionFactoryUtil;
import gigachad.pussy.models.Owner;
import gigachad.pussy.models.Pussy;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    @Override
    public List<Owner> allOwners() {
        return (List<Owner>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Owner").list();
    }

    @Override
    public void add(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public Owner getById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Owner.class, id);
    }
}
