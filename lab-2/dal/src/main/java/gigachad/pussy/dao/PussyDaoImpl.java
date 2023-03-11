package gigachad.pussy.dao;

import gigachad.pussy.hibernate.HibernateSessionFactoryUtil;
import gigachad.pussy.models.Owner;
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
    public void destroyPussy(Pussy pussy) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(pussy);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Pussy pussy) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(pussy);
        tx1.commit();
        session.close();
    }

    @Override
        public Pussy getById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Pussy.class, id);
    }

    @Override
    public void setOwner(Pussy pussy, Owner owner) {
        pussy.setOwner(owner);
        update(pussy);
    }

    @Override
    public void addFriend(Pussy pussy1, Pussy pussy2) {
        pussy1.getFriends().add(pussy2);
        pussy2.getFriends().add(pussy1);
        update(pussy1);
        update(pussy2);
    }
}
