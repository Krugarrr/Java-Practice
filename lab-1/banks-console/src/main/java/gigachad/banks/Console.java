package gigachad.banks;

import gigachad.banks.BankEntity.BankImpl;
import gigachad.banks.CentralBank.CentralBankSingleton;
import gigachad.banks.Handlers.*;

import java.util.Scanner;

public class Console {
    Scanner in = new Scanner(System.in);
    private CentralBankSingleton centralBank = CentralBankSingleton.getInstance();

    public void Input(String[] args) {
        boolean input = true;
        System.out.println("Commands for done:\n\n" +
                    "1 - создать новый банк\n" +
                    "2 - добавить клиента в нужный банк\n" +
                    "3 - открыть счёт\n" +
                    "4 - совершить транзацкию\n" +
                    "9 - вывести список клиентов\n" +
                    "10 - вывести существующие банки\n");

        while (input) {
            IEntityCreatorHandler handler;
            switch (in.next())
            {
                case "1":
                    handler = new BankCreator(in);
                    handler = handler.onNext();
                    handler.createEntity();
                    break;
                case "2":
                    handler = new ClientCreator(in);
                    handler = handler.onNext();
                    handler.createEntity();
                    break;
                case "3":
                    handler = new AccountCreator(in);
                    handler = handler.onNext();
                    handler.createEntity();
                    break;
                case "4":
                    handler = new TransactionHandler(in);
                    handler = handler.onNext();
                    handler.createEntity();
                    break;
                case "9":
                    for (var client : CentralBankSingleton.getInstance().getClients()) {
                        System.out.println(client.getName() + ' ' + client.getSurname());
                    }
                    break;
                case "10":
                    for (BankImpl bank : CentralBankSingleton.getInstance().getBanks()) {
                        System.out.println(bank.getName());
                    }
                    break;
                case "/break":
                    input = false;
                    break;
                }
            }
        }
}