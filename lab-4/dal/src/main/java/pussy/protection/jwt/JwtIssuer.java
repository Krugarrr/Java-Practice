package pussy.protection.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwtProperties properties;
    private static final int JWT_EXPIRATION_DURATION = 1;
    public String issue(long userId, String userEmail, List<String> roles) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(JWT_EXPIRATION_DURATION, ChronoUnit.DAYS)))
                .withClaim("e", userEmail)
                .withClaim("r", roles)
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}
