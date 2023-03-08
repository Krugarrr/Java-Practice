package gigachad.banks.Handlers;

import gigachad.banks.BankEntity.BankImpl;
import gigachad.banks.CentralBank.CentralBankSingleton;
import gigachad.banks.ClientEntity.Client;


import java.util.Scanner;

public class TransactionHandler implements IEntityCreatorHandler{
    private Scanner in;
    private Client client;
    private BankImpl bank;
    private int accountNumber;
    public TransactionHandler(Scanner in) {
        this.in = in;
    }
    public TransactionHandler(Scanner in, Client client, BankImpl bank) {
        this.in = in;
        this.client = client;
        this.bank = bank;
    }
    public void createEntity() {
        if (bank == null || client == null) {
            return;
        }
        System.out.println("Номер счёта помнишь?) да/нет" );
        String choice = in.next();
        if (choice.equals("да") || choice.equals("Да")) {
            System.out.println("Укажите номер: " );
            int unverifiedNumber = in.nextInt();
            var accountDecorator = bank.getAccounts().stream().filter(i -> i.getAccountWrap().getId() == unverifiedNumber).findAny().orElse(null);
            if (accountDecorator == null) {
                System.out.println("Эу вася не твой номер, попробуй ещё разок " );
                return;
            }
            accountNumber = accountDecorator.getAccountWrap().getId();
        }
        System.out.println("Че сделать хочешь?\n 1 - снять бабки\n 2 - положить бабки \n 3 - перевести бабки \n 4 - снять бабку (не жми сюда)" );
        switch (in.next()) {
            case "1" -> {
                var account = bank.getAccount(accountNumber, client);
                System.out.println("Сколько снимать будем?) " );
                account.takeMoney(in.nextBigDecimal());
                System.out.println("Готово. Ваш баланс: " + account.getAccountWrap().getBalance() );
                break;
            }
            case "2" -> {
                var account = bank.getAccount(accountNumber, client);
                System.out.println("Сколько класть будем?) " );
                account.addMoney(in.nextBigDecimal());
                System.out.println("Готово. Ваш баланс: " + account.getAccountWrap().getBalance() );
                break;
            }
            case "3" -> {
                var centralBank = CentralBankSingleton.getInstance();
                var account = bank.getAccount(accountNumber, client);
                System.out.println("Куда переводим?) (имя банка) " );
                String name = in.next();
                var anotherBank = centralBank.getBanks().stream().filter(n -> n.getName().equals(name)).findFirst().orElse(null);
                if (anotherBank == null) {
                    System.out.println("НЕТ ТАКОГО БАНКА" );
                    return;
                }
                var anotherAccount = anotherBank.getAccounts().stream().filter(n -> n.getAccountWrap().getOwner().equals(client)).findAny().orElse(null);
                if (anotherAccount == null) {
                    System.out.println("Нет счёта в этом банке лол " );
                    return;
                }
                int anotherNumber = anotherAccount.getAccountWrap().getId();
                System.out.println("А сколько переводим?) " );
                account.transferMoney(in.nextBigDecimal(), anotherNumber, anotherBank);
                System.out.println("Готово. Ваш баланс на первом аккаунте: " + account.getAccountWrap().getBalance());
                System.out.println("Ваш баланс на втором аккаунте: " + account.getAccountWrap().getBalance());
                break;
            }
            default -> System.out.println("Чет ты мискликнул");
        }
    }

    public IEntityCreatorHandler onNext() {
        System.out.println("Где твой счёт находится? (имя банка): " );
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
        return new TransactionHandler(in, client, bank);
    }
}
