package gigachad.pussy.services;

import gigachad.pussy.dao.PussyDao;
import gigachad.pussy.dao.PussyDaoImpl;
import gigachad.pussy.models.Owner;
import gigachad.pussy.models.Pussy;

import java.util.List;

public class PussyServiceImpl implements PussyService {
    private PussyDao pussyDao = new PussyDaoImpl();
    @Override
    public List<Pussy> allPussies() {
        return pussyDao.allPussies();
    }

    @Override
    public void add(Pussy pussy) {
        pussyDao.add(pussy);
    }

    @Override
    public void destroyPussy(Pussy pussy) {
        pussyDao.destroyPussy(pussy);
    }

    @Override
    public void change(Pussy pussy) {
        pussyDao.update(pussy);
    }

    @Override
    public Pussy getById(long id) {
        return pussyDao.getById(id);
    }

    @Override
    public void setOwner(Pussy pussy, Owner owner) {
        pussyDao.setOwner(pussy, owner);
    }

    @Override
    public void addFriend(Pussy pussy1, Pussy pussy2) {
        pussyDao.addFriend(pussy1, pussy2);
    }
}
