package gigachad.security;

import gigachad.security.dto.OwnerDto;
import gigachad.security.dto.PussyDto;
import gigachad.security.services.OwnerService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> addOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.add(ownerDto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @DeleteMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> deleteOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.delete(ownerDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> changeOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.change(ownerDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<OwnerDto> getById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(ownerService.getById(id));
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<OwnerDto>> getOwners() {
        return ResponseEntity.ok(ownerService.allOwners());
    }

    @GetMapping("/owned")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<PussyDto>> getPussies(@RequestParam long ownerId) {
        return ResponseEntity.ok(ownerService.getPussies(ownerId));
    }
}
