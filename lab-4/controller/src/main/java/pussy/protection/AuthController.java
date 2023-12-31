package pussy.protection;

import pussy.protection.dto.UserDto;
import pussy.protection.securities.LoginRequest;
import pussy.protection.securities.LoginResponse;
import pussy.protection.securities.UserPrincipal;
import pussy.protection.services.AuthService;
import pussy.protection.services.UserService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
        return ResponseEntity.ok(authService.attemptLogin(request.getEmail(), request.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto user) {
        userService.addUser(user);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @GetMapping("/secured")
    @SecurityRequirement(name = "Bearer Authentication")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {
        return "Secured as:" + principal.getEmail() + "\n Id: " + principal.getUserId();
    }
}
