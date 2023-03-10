package gigachad.pussy.dao;

import gigachad.pussy.hibernate.HibernateSessionFactoryUtil;
import gigachad.pussy.models.Pussy;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PussyDaoImpl implements PussyDao {
    @Override
    public List<Pussy> allPussies() {
        return (List<Pussy>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Pussy").list();
    }

    @Override
    public void add(Pussy pussy) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(pussy);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Pussy pussy) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(pussy);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Pussy pussy) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(pussy);
        tx1.commit();
        session.close();
    }

    @Override
    public Pussy getById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Pussy.class, id);
    }
}
