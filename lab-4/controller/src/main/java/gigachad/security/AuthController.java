package gigachad.security;

import gigachad.security.dto.UserDto;
import gigachad.security.services.AuthService;
import gigachad.security.securities.LoginRequest;
import gigachad.security.securities.LoginResponse;
import gigachad.security.securities.UserPrincipal;
import gigachad.security.services.UserService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDto user) {
        userService.addUser(user);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("/secured")
    @SecurityRequirement(name = "Bearer Authentication")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {
        return "Xuy" + principal.getEmail() + " " + principal.getUserId();
    }
}
