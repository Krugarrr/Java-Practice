package gigachad.pussy.services;

import gigachad.pussy.models.Owner;
import gigachad.pussy.models.Pussy;
import java.util.List;

public interface PussyService {
    List<Pussy> allPussies();
    void add(Pussy pussy);
    void destroyPussy(Pussy pussy);
    void change(Pussy pussy);
    Pussy getById(long id);
    void setOwner(Pussy pussy, Owner owner);
    void addFriend(Pussy pussy1, Pussy pussy2);
}
