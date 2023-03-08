package gigachad.banks.Handlers;

import gigachad.banks.BankEntity.Config.BankConfiguration;
import gigachad.banks.BankEntity.Config.BankConfigurationBuilder;
import gigachad.banks.BankEntity.Config.DepositInterestPoint;
import gigachad.banks.BankEntity.Config.DepositInterestRate;
import gigachad.banks.CentralBank.CentralBankSingleton;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankCreator implements IEntityCreatorHandler {
    Scanner in;
    private BankConfigurationBuilder bankConfigurationBuilder = new BankConfigurationBuilder();
    private String name;
    private BankConfiguration configuration;

    public BankCreator(Scanner in) {
        this.in = in;
    }
    public BankCreator(String name, BankConfiguration configuration) {
        this.name = name;
        this.configuration = configuration;
    }

    public void createEntity() {
        var bank = CentralBankSingleton.getInstance().addBank(name, configuration);
        System.out.println("Bank" + bank.toString() + "created");
    }
    public IEntityCreatorHandler onNext() {
        var rateBuilder = DepositInterestRate.createBuilder();

        System.out.println("Назовите свой банк:" );
        String name = in.next();
        System.out.println("Задайте его конфигурацию:" );
        System.out.println("\tЗадайте кредитный предел:" );
        bankConfigurationBuilder.withCreditLimit(new BigDecimal(in.next()));
        System.out.println("\tЗадайте комиссию на кредит:" );
        bankConfigurationBuilder.withCreditComission(new BigDecimal(in.next()));
        System.out.println("\tЗадайте предел на перевод:" );
        bankConfigurationBuilder.withTransactionLimit(new BigDecimal(in.next()));
        System.out.println("\tЗадайте процент по дебиту:" );
        bankConfigurationBuilder.withDebitInterestRate(new BigDecimal(in.next()));
        System.out.println("\tСколько у банка будет интервалов под проценты?:" );
        int i = in.nextInt();
        if (i < 0) {
            System.out.println("Ты дурак?" );
            return new BankCreator(in);
        }
        while (i > 0){
            System.out.println("Введите левую и правую границу, и процент (в новых строчках):" );
            rateBuilder.addInterestPoint(
                    new DepositInterestPoint(in.nextBigDecimal(),
                            in.nextBigDecimal(),
                            in.nextBigDecimal()));
            System.out.println("Продолжайте..." );
            i--;
        }
        System.out.println("Введите процент, который будет распросраняться на оставшиеся интервалы:" );
        var dpir = rateBuilder.addMaxRate(in.nextBigDecimal()).build();
        BankConfiguration configuration = bankConfigurationBuilder.withDepositInterestRate(dpir).build();
        return new BankCreator(name, configuration);
    }
}
