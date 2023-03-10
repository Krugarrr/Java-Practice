package gigachad.pussy.services;

import gigachad.pussy.models.Owner;
import java.util.List;

public interface OwnerService {
    List<Owner> allOwners();
    void add(Owner owner);
    void delete(Owner owner);
    void change(Owner owner);
    Owner getById(int id);
}
