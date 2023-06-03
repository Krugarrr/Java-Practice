package micro.pussy;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import micro.pussy.dto.PussyDto;
import micro.pussy.kafka.OwnerChangesProducer;
import micro.pussy.kafka.PussyChangesProducer;
import micro.pussy.services.PussyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pussies")
@RequiredArgsConstructor
@Validated
public class PussyController {
    private PussyChangesProducer pussyChangesProducer;
    private final PussyService pussyService;
    @GetMapping
    public ResponseEntity<List<PussyDto>> getPussies() {
        var pussies = pussyService.getAll();
        pussyChangesProducer.sendMessage(pussies);
        return ResponseEntity.ok(pussies);
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<List<PussyDto>> getFriends(@PathVariable long id) {
        var pussies = pussyService.getFriends(id);
        pussyChangesProducer.sendMessage(pussies);
        return ResponseEntity.ok(pussies);
    }

    @PostMapping
    public ResponseEntity<?> addPussy(@RequestBody PussyDto pussyDto) {
        pussyService.add(pussyDto);
        pussyChangesProducer.sendMessage(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @DeleteMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> destroyPussy(@RequestBody PussyDto pussyDto) {
        pussyService.destroyPussy(pussyDto);
        pussyChangesProducer.sendMessage(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> changePussy(@RequestBody PussyDto pussyDto) {
        pussyService.change(pussyDto);
        pussyChangesProducer.sendMessage(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<PussyDto> findById(@PathVariable long id) throws Exception {
        var pussy = pussyService.findById(id);
        pussyChangesProducer.sendMessage(pussy);
        return ResponseEntity.ok(pussy);
    }

    @PostMapping("/{id}/befriend")
    public ResponseEntity<?> addFriend(@PathVariable @Nonnull long id, @RequestParam @Nonnull long secondId) {
        pussyService.addFriend(id, secondId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
