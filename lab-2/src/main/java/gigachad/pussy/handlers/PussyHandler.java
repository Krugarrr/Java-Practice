package gigachad.pussy.handlers;

import gigachad.pussy.controllers.PussyController;
import gigachad.pussy.dto.PussyDto;
import gigachad.pussy.models.Breed;
import gigachad.pussy.models.Color;
import org.joda.time.MutableDateTime;
import java.util.Scanner;

public class PussyHandler implements EntityDBHandler {
    private Scanner in;
    private PussyController pussyController;
    private long number;
    private String name;
    private MutableDateTime birthDate;
    private Breed breed;
    private Color color;

    public PussyHandler(long number, Scanner in, PussyController pussyController) {
        this.number = number;
        this.pussyController = pussyController;
        this.in = in;
    }

    public PussyHandler(PussyController pussyController, long number, String name, MutableDateTime birthDate, Breed breed, Color color) {
        this.pussyController = pussyController;
        this.number = number;
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }

    public void createEntity() {
        pussyController.addPussy(new PussyDto(number, name, birthDate, breed, color));
    }

    public EntityDBHandler onNext() {
        System.out.println("Как кошака зовут?:");
        name = in.next();
        System.out.println("Давай типа он родился сегодня... ты ведь не хочешь писать DATE-FORMAT вручную?)");
        birthDate = MutableDateTime.now();
        System.out.println("Породистый у вас??)" +
                "\n 1 - SUBMISSIVE;" +
                "\n 2 - NEKO_GIRL;" +
                "\n 3 - DOMINANT;" +
                "\n 4 - VANILLA;" +
                "\n 5 - HORNY.");
        switch (in.next()) {
            case "1" -> {
                breed = Breed.SUBMISSIVE;
                break;
            }
            case "2" -> {
                breed = Breed.NEKO_GIRL;
                break;
            }
            case "3" -> {
                breed = Breed.DOMINANT;
                break;
            }
            case "4" -> {
                breed = Breed.VANILLA;
                break;
            }
            case "5" -> {
                breed = Breed.HORNY;
                break;
            }
            default -> System.out.println("Чет ты мискликнул");
        }
        System.out.println("И цветастый небось?)" +
                "\n 1 - RED;" +
                "\n 2 - GREEN;" +
                "\n 3 - BLUE.");

        switch (in.next()) {
            case "1" -> {
                color = Color.RED;
                break;
            }
            case "2" -> {
                color = Color.GREEN;
                break;
            }
            case "3" -> {
                color = Color.BLUE;
                break;
            }
            default -> System.out.println("Чет ты мискликнул");
        }
        return new PussyHandler(pussyController, number, name, birthDate, breed, color);
    }
}
