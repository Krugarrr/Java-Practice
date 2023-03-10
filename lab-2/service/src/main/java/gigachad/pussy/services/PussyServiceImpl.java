package gigachad.pussy.services;

import gigachad.pussy.dao.PussyDao;
import gigachad.pussy.dao.PussyDaoImpl;
import gigachad.pussy.models.Pussy;
import gigachad.pussy.services.PussyService;

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
    public void delete(Pussy pussy) {
        pussyDao.delete(pussy);
    }

    @Override
    public void change(Pussy pussy) {
        pussyDao.update(pussy);
    }

    @Override
    public Pussy getById(int id) {
        return pussyDao.getById(id);
    }
}
