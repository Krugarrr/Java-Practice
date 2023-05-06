package gigachad.security.dao;

import gigachad.security.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
