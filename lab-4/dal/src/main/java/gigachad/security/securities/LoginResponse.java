package gigachad.security.securities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private final String accessToken;
}
