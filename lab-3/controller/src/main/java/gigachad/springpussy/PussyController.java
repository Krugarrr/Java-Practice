package gigachad.springpussy;

import gigachad.springpussy.dto.PussyDto;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import gigachad.springpussy.services.PussyService;
import java.util.List;

@RestController
@RequestMapping("/pussies")
@Validated
public class PussyController {
    private final PussyService pussyService;

    @Autowired
    public PussyController(PussyService pussyService) {
        this.pussyService = pussyService;
    }

    @GetMapping
    public ResponseEntity<List<PussyDto>> getPussies() {
        return ResponseEntity.ok(pussyService.getAll());
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<List<PussyDto>> getFriends(@PathVariable long id) {
        return ResponseEntity.ok(pussyService.getFriends(id));
    }

    @PostMapping
    public ResponseEntity<?> addPussy(@RequestBody PussyDto pussyDto) {
        pussyService.add(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> destroyPussy(@RequestBody PussyDto pussyDto) {
        pussyService.destroyPussy(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> changePussy(@RequestBody PussyDto pussyDto) {
        pussyService.change(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/{id}")
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
