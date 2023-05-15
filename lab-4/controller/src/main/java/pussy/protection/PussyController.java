package pussy.protection;

import pussy.protection.dto.PussyDto;
import pussy.protection.services.PussyService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pussies")
@RequiredArgsConstructor
@Validated
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class PussyController {
    private final PussyService pussyService;
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<PussyDto>> getPussies() {
        return ResponseEntity.ok(pussyService.getAll());
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<List<PussyDto>> getFriends(@PathVariable long id) {
        return ResponseEntity.ok(pussyService.getFriends(id));
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> addPussy(@RequestBody PussyDto pussyDto) {
        pussyService.add(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @DeleteMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> destroyPussy(@RequestBody PussyDto pussyDto) {
        pussyService.destroyPussy(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> changePussy(@RequestBody PussyDto pussyDto) {
        pussyService.change(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<PussyDto> findById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(pussyService.findById(id));
    }

    @PostMapping("/{id}/owners/")
    public ResponseEntity<?> setOwner(@PathVariable @Nonnull long id, @RequestParam @Nonnull long ownerId) throws Exception {
        pussyService.setOwner(id, ownerId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PostMapping("/{id}/befriend")
    public ResponseEntity<?> addFriend(@PathVariable @Nonnull long id, @RequestParam @Nonnull long secondId) {
        pussyService.addFriend(id, secondId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
