package gigachad.pussy.dao;
import gigachad.pussy.models.Pussy;
import java.util.List;

public interface PussyDao {
    List<Pussy> allPussies();
    void add(Pussy pussy);
    void delete(Pussy pussy);
    void update(Pussy pussy);
    Pussy getById(int id);
}
