package gigachad.pussy.services;

import gigachad.pussy.models.Pussy;
import java.util.List;

public interface PussyService {
    List<Pussy> allPussies();
    void add(Pussy pussy);
    void delete(Pussy pussy);
    void change(Pussy pussy);
    Pussy getById(int id);
}
