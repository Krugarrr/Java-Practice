package gigachad.security.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String password;
    private String role;
    private boolean tokenExpired;


    public UserEntity(long id, String email, String password, String role, boolean tokenExpired) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.tokenExpired = tokenExpired;
    }
}
