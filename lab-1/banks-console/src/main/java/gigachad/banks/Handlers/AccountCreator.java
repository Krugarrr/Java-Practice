package gigachad.banks.Handlers;

import gigachad.banks.BankEntity.BankImpl;
import gigachad.banks.CentralBank.CentralBankSingleton;
import gigachad.banks.ClientEntity.Client;
import lombok.Data;

import java.util.Scanner;

@Data
public class AccountCreator implements IEntityCreatorHandler{
    private Scanner in;
    private Client client;
    private BankImpl bank;
    public AccountCreator(Scanner in) {
        this.in = in;
    }
    public AccountCreator(Scanner in, Client client, BankImpl bank) {
        this.in = in;
        this.client = client;
        this.bank = bank;
    }
    public void createEntity() {
        if (bank == null || client == null) {
            return;
        }
        System.out.println("Какой счёт хочшеь открыть?)\n 1 - Дебетовый;\n 2 - Депозитный;\n 3 - Кредитный." );
        switch (in.next()) {
            case "1" -> {
                bank.createDebitAccount(client);
                System.out.println("Ёмаё, открыли, номер твоего счёта: " + (bank.getAccountIdGenerator() - 1));
                break;
            }
            case "2" -> {
                bank.createDepositAccount(client);
                System.out.println("Ёмаё, открыли, номер твоего счёта: " + (bank.getAccountIdGenerator() - 1));
                break;
            }
            case "3" -> {
                bank.createCreditAccount(client);
                System.out.println("Ёмаё, открыли, номер твоего счёта: " + (bank.getAccountIdGenerator() - 1));
                break;
            }
            default -> System.out.println("Чет ты мискликнул");
        }
    }

    public IEntityCreatorHandler onNext() {
        System.out.println("Где счёт открыть хочешь? (имя банка): " );
        String bankName = in.next();
        var centralBank = CentralBankSingleton.getInstance();
        var bank = centralBank.getBanks().stream().filter(n -> n.getName().equals(bankName)).findFirst().orElse(null);
        if (bank == null) {
            System.out.println("НЕТ ТАКОГО БАНКА" );
            return new AccountCreator(in);
        }

        System.out.println("А ты кто вообще?\n 1 - ввести паспорт; \n 2 - Ввести ФИ" );
        Client client = null;
        switch (in.next()) {
            case "1" -> {
                System.out.println("Ну вводи");
                int document = in.nextInt();
                client = centralBank.getClients().stream().filter(d -> d.getDocument() == document).findAny().orElse(null);
                if (client == null) { System.out.println("Чет ты не то ввёл, попробуй ещё раз" ); }
                break;
            }
            case "2" -> {
                System.out.println("Имя: ");
                String name = in.next();
                System.out.println("Фамилия: ");
                String secondName = in.next();
                client = centralBank.getClients().stream().filter(n -> n.getName().equals(name) && n.getSurname().equals(secondName)).findAny().orElse(null);
                if (client == null) { System.out.println("Чет ты не то ввёл, попробуй ещё раз" ); }
                break;
            }
            default -> System.out.println("Ты мисскликнул чел" );
        }
        return new AccountCreator(in, client, bank);
    }
}
