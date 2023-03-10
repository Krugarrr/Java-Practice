package gigachad.pussy;

import gigachad.pussy.dao.PussyDao;
import gigachad.pussy.dao.PussyDaoImpl;
import gigachad.pussy.models.Pussy;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        PussyDao pd = new PussyDaoImpl();
        pd.add(new Pussy());
//        Configuration configuration = new Configuration().configure();
//        new Configuration().configure().buildSessionFactory();
    }
}