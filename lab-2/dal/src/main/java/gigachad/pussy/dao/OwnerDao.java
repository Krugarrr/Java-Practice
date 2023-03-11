package gigachad.pussy.dao;

import gigachad.pussy.models.Owner;
import gigachad.pussy.models.Pussy;

import java.util.List;

public interface OwnerDao {
    List<Owner> allOwners();
    void add(Owner owner);
    void delete(Owner owner);
    void update(Owner owner);
    Owner getById(long id);
    void addPussy(Pussy pussy, Owner owner);
}
