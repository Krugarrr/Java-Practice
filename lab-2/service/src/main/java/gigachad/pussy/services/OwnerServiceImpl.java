package gigachad.pussy.services;

import gigachad.pussy.dao.OwnerDao;
import gigachad.pussy.dao.OwnerDaoImpl;
import gigachad.pussy.models.Owner;
import gigachad.pussy.models.Pussy;
import gigachad.pussy.services.OwnerService;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    private OwnerDao ownerDao = new OwnerDaoImpl();
    @Override
    public List<Owner> allOwners() {
        return ownerDao.allOwners();
    }

    @Override
    public void add(Owner owner) {
        ownerDao.add(owner);
    }

    @Override
    public void delete(Owner owner) {
        ownerDao.delete(owner);
    }

    @Override
    public void change(Owner owner) {
        ownerDao.update(owner);
    }

    @Override
    public Owner getById(long id) {
        return ownerDao.getById(id);
    }

    @Override
    public void addPussy(Pussy pussy, Owner owner) {
        ownerDao.addPussy(pussy, owner);
    }
}
