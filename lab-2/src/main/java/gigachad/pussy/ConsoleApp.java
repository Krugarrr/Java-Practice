package gigachad.pussy;

import gigachad.pussy.controllers.OwnerController;
import gigachad.pussy.controllers.PussyController;
import gigachad.pussy.dto.PussyDto;
import gigachad.pussy.models.Breed;
import gigachad.pussy.models.Color;
import org.joda.time.MutableDateTime;

import java.util.Scanner;

public class ConsoleApp {
    private PussyController pussyController = new PussyController();
    private OwnerController ownerController = new OwnerController();
    Scanner in = new Scanner(System.in);
    MutableDateTime date = MutableDateTime.now();

    public void Input(String[] args) {
        boolean input = true;
        System.out.println("Commands for done:\n\n" +
                "1 - добавить киску\n" +
                "2 - удалить киску\n" +
                "3 - поменять киску\n" +
                "4 - добавить хозяина киски\n" +
                "5 - удалить хозяина киски\n" +
                "6 - вырезать хозяина киски\n" +
                "7 - поменять хозяина киски \n");

        while (input) {
            switch (in.next())
            {
                case "1":
                    PussyDto pussy = new PussyDto(
                            in.next(),
                            date,
                            Breed.DOMINANT,
                            Color.BLUE
                    );
                    pussyController.addPussy(pussy);
                case "/break":
                    input = false;
                    break;
            }
        }
    }
}
