package gigachad.security.dao;

import gigachad.security.models.Pussy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PussyDao extends JpaRepository<Pussy, Long> {
}
