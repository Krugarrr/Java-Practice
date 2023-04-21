package gigachad.springpussy.dao;

import gigachad.springpussy.models.Pussy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PussyDao extends JpaRepository<Pussy, Long> {
}
