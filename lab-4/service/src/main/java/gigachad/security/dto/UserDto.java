package gigachad.security.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class UserDto {
    private long id;
    private String email;
    private String password;
    private String role;
    private boolean tokenExpired;

    public UserDto(long id, String email, String password, String role, boolean tokenExpired) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.tokenExpired = tokenExpired;
    }
}
