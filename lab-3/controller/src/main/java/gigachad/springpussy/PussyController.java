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

    @GetMapping("/get/all")
    public ResponseEntity<List<PussyDto>> getPussies() {
        return ResponseEntity.ok(pussyService.getAll());
    }

    @GetMapping("/get/pussy/friends")
    public ResponseEntity<List<PussyDto>> getFriends(@RequestParam long pussyId) {
        return ResponseEntity.ok(pussyService.getFriends(pussyId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPussy(@RequestBody PussyDto pussyDto) {
        pussyService.add(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @DeleteMapping("/destroy")
    public ResponseEntity<?> destroyPussy(@RequestBody PussyDto pussyDto) {
        pussyService.destroyPussy(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/change")
    public ResponseEntity<?> changePussy(@RequestBody PussyDto pussyDto) {
        pussyService.change(pussyDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/get/by/id")
    public ResponseEntity<PussyDto> findById(@RequestParam long id) throws Exception {
        return ResponseEntity.ok(pussyService.findById(id));
    }

    @PostMapping("/set/owner")
    public ResponseEntity<?> setOwner(@RequestParam @Nonnull long pussyId, @RequestParam @Nonnull long ownerId) throws Exception {
        pussyService.setOwner(pussyId, ownerId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PostMapping("/make/friend")
    public ResponseEntity<?> addFriend(@RequestParam @Nonnull long firstPussyId, @RequestParam @Nonnull long secondPussyId) {
        pussyService.addFriend(firstPussyId, secondPussyId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
