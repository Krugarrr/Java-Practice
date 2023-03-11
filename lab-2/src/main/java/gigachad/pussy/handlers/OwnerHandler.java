package gigachad.pussy.handlers;

import gigachad.pussy.controllers.OwnerController;
import gigachad.pussy.controllers.PussyController;
import gigachad.pussy.dto.OwnerDto;
import gigachad.pussy.dto.PussyDto;
import org.joda.time.MutableDateTime;
import java.util.Scanner;

public class OwnerHandler implements EntityDBHandler {
    private Scanner in;
    private OwnerController ownerController;
    private long id;
    private String name;
    private MutableDateTime birthDate;

    public OwnerHandler(long number, Scanner in, OwnerController ownerController) {
        this.id = number;
        this.ownerController = ownerController;
        this.in = in;
    }

    public OwnerHandler(OwnerController ownerController, long number, String name, MutableDateTime birthDate) {
        this.ownerController = ownerController;
        this.id = number;
        this.name = name;
        this.birthDate = birthDate;
    }

    public void createEntity() {
        ownerController.addOwner(new OwnerDto(id, name, birthDate));
    }

    public EntityDBHandler onNext() {
        System.out.println("Как зовут?:");
        name = in.next();
        System.out.println("Давай типа он родился сегодня... ты ведь не хочешь писать DATE-FORMAT вручную?)");
        birthDate = MutableDateTime.now();
        return new OwnerHandler(ownerController, id, name, birthDate);
    }
}