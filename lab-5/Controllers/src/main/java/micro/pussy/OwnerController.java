package micro.pussy;



import micro.pussy.kafka.OwnerChangesProducer;
import micro.pussy.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import micro.pussy.dto.OwnerDto;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private OwnerChangesProducer ownerChangesProducer;
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerChangesProducer ownerChangesProducer, OwnerService ownerService) {
        this.ownerChangesProducer = ownerChangesProducer;
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<?> addOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.add(ownerDto);
        ownerChangesProducer.sendMessage(ownerDto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.delete(ownerDto);
        ownerChangesProducer.sendMessage(ownerDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> changeOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.change(ownerDto);
        ownerChangesProducer.sendMessage(ownerDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getById(@PathVariable long id) throws Exception {
        var owner = ownerService.getById(id);
        ownerChangesProducer.sendMessage(owner);
        return ResponseEntity.ok(owner);
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getOwners() {
        var owners = ownerService.allOwners();
        ownerChangesProducer.sendMessage(owners);
        return ResponseEntity.ok(owners);
    }
}
