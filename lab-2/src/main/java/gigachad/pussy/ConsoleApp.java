package gigachad.pussy;

import gigachad.pussy.controllers.OwnerController;
import gigachad.pussy.controllers.PussyController;
import gigachad.pussy.dto.OwnerDto;
import gigachad.pussy.dto.PussyDto;
import gigachad.pussy.handlers.EntityDBHandler;
import gigachad.pussy.handlers.OwnerHandler;
import gigachad.pussy.handlers.PussyHandler;
import gigachad.pussy.handlers.PussyUpdater;
import org.joda.time.MutableDateTime;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleApp {
    private static final int INITIAL_ID = 0;
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(INITIAL_ID);
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
                "4 - подружить кисок\n" +
                "5 - дать киске хозяина\n" +
                "6 - добавить хозяина киски\n" +
                "7 - вырезать хозяина киски\n");

        while (input) {
            EntityDBHandler handler;
            switch (in.next())
            {
                case "1":
                    handler = new PussyHandler(ID_GENERATOR.getAndIncrement(), in, pussyController);
                    handler = handler.onNext();
                    handler.createEntity();
                case "2":
                    System.out.println("Введи номер кошака для удаления: ");
                    pussyController.destroyPussy(pussyController.getById(in.nextLong()));
                    System.out.println("Киска уничтожена!");
                    break;
                case "3":
                    System.out.println("Какую пуську хочешь поменять? (номер)");
                    PussyDto pussyDto = pussyController.getById(in.nextLong());
                    handler = new PussyUpdater(pussyDto, in, pussyController);
                    handler = handler.onNext();
                    handler.createEntity();
                    break;
                case "4":
                    System.out.println("Каких кисок подружим? (2 номера)");
                    PussyDto pussyDto1 = pussyController.getById(in.nextLong());
                    PussyDto pussyDto2 = pussyController.getById(in.nextLong());
                    pussyController.addFriend(pussyDto1, pussyDto2);
                    break;
                case "5":
                    System.out.println("Ты кто? (номер) ");
                    OwnerDto ownerDto = ownerController.getById(in.nextLong());
                    System.out.println("Какую пуську хочешь взять? (номер)");
                    PussyDto pussyDto3 = pussyController.getById(in.nextLong());
                    pussyController.setOwner(pussyDto3, ownerDto);
                    ownerController.addPussy(pussyDto3, ownerDto);
                    break;
                case "6":
                    handler = new OwnerHandler(ID_GENERATOR.getAndIncrement(), in, ownerController);
                    handler = handler.onNext();
                    handler.createEntity();
                    break;
                case "7":
                    System.out.println("Введи номер человека для удаления: ");
                    ownerController.deleteOwner(ownerController.getById(in.nextLong()));
                    System.out.println("Человек вырезан!");
                    break;
                case "/break":
                    input = false;
                    break;
            }
        }
    }
}
