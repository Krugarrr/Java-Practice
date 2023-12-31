package gigachad.springpussy;

import gigachad.springpussy.dto.OwnerDto;
import gigachad.springpussy.dto.PussyDto;
import gigachad.springpussy.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<?> addOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.add(ownerDto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.delete(ownerDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> changeOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.change(ownerDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OwnerDto> getById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(ownerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getOwners() {
        return ResponseEntity.ok(ownerService.allOwners());
    }

    @GetMapping("/owned")
    public ResponseEntity<List<PussyDto>> getPussies(@RequestParam long ownerId) {
        return ResponseEntity.ok(ownerService.getPussies(ownerId));
    }
}
