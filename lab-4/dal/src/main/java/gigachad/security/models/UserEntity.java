package gigachad.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
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
