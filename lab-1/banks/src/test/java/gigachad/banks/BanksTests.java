package gigachad.banks;

import gigachad.banks.BankEntity.BankImpl;
import gigachad.banks.BankEntity.Config.BankConfiguration;
import gigachad.banks.BankEntity.Config.DepositInterestRate;
import gigachad.banks.BankEntity.Config.BankConfigurationBuilder;
import gigachad.banks.BankEntity.Config.DepositInterestPoint;
import gigachad.banks.CentralBank.CentralBankSingleton;
import gigachad.banks.ClientEntity.Address;
import gigachad.banks.ClientEntity.ClientAddressBuilder;
import gigachad.banks.ClientEntity.Client;
import gigachad.banks.ClientEntity.ClientBuilder;
import gigachad.banks.ClientEntity.Interfaces.AddressBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

public class BanksTests {
    private AddressBuilder addressBuilder = new ClientAddressBuilder();
    private TimeManager timeManager = new TimeManager();
    private CentralBankSingleton centralBank;
    private Address address;
    private ClientBuilder clientBuilder = new ClientBuilder();
    private BankConfigurationBuilder bankConfigurationBuilder = new BankConfigurationBuilder();
    private Client client;
    private BankConfiguration configuration;
    private BankConfiguration newConfiguration;
    private Client suss;

    public BanksTests() {
        centralBank = CentralBankSingleton.getInstance();

        address = addressBuilder
                .withStreet("Кронва")
                .withCity("Kukuevo")
                .withHouse(147)
                .build();

        suss = clientBuilder
                .withName("Амогус")
                .withSurname("ВнатуреСус").build();

        client = clientBuilder
                .withName("Амогус")
                .withSurname("Сус")
                .withAddress(address)
                .withDocument(123123).build();

        var dpir = DepositInterestRate.createBuilder()
                .addInterestPoint(new DepositInterestPoint(BigDecimal.valueOf(3),
                        BigDecimal.valueOf(10),
                        BigDecimal.valueOf(5)))
                .addMaxRate(BigDecimal.valueOf(4))
                .build();

        configuration = bankConfigurationBuilder
                .withCreditLimit(BigDecimal.valueOf(3))
                .withCreditComission(BigDecimal.valueOf(3))
                .withTransactionLimit(BigDecimal.valueOf(1000000))
                .withDebitInterestRate(BigDecimal.valueOf(3))
                .withDepositInterestRate(dpir)
                .build();

        newConfiguration = bankConfigurationBuilder
                .withCreditLimit(BigDecimal.valueOf(4))
                .withCreditComission(BigDecimal.valueOf(3))
                .withTransactionLimit(BigDecimal.valueOf(100000))
                .withDebitInterestRate(BigDecimal.valueOf(3))
                .withDepositInterestRate(dpir)
                .build();
    }

    @Test
    public void BaseOperations() {
        BankImpl bank = centralBank.addBank("СперБанк", configuration);
        BankImpl anotherBank = centralBank.addBank("МакаревичМани", configuration);
        bank.createDebitAccount(client);
        anotherBank.createDebitAccount(client);

        var account = bank.getAccount(0, client);
        account.addMoney(BigDecimal.valueOf(50000));
        account.takeMoney(BigDecimal.valueOf(4000));
        account.cancelTransaction(1);
        Assertions.assertEquals(BigDecimal.valueOf(50000), account.getAccountWrap().getBalance());

        account.transferMoney(BigDecimal.valueOf(4000), 0, anotherBank);
        Assertions.assertEquals(BigDecimal.valueOf(4000), anotherBank.getAccount(0, client).getAccountWrap().getBalance());
    }

    @Test
    public void SussyClients_CantTakeEnough() {
        BankImpl bank = centralBank.addBank("СперБанк", configuration);
        bank.createDebitAccount(suss);
        bank.changeConfiguration(newConfiguration);
        bank.getAccounts().get(bank.getAccounts().size() - 1).addMoney(BigDecimal.valueOf(500000000));
        var account = bank.getAccount(0, suss);
        Assertions.assertThrows(IllegalArgumentException.class, () -> account.takeMoney(BigDecimal.valueOf(2000000)));
    }

    @Test
    public void TimeMashineDoStuff() {
        centralBank = CentralBankSingleton.getInstance();
        BankImpl bank = centralBank.addBank("СперБанк", configuration);
        bank.createDebitAccount(client);
        var account = bank.getAccount(0, client);
        account.addMoney(BigDecimal.valueOf(50000));
        centralBank.getTimeManager().addDay(1);
        centralBank.getTimeManager().addMonth(2);
        centralBank.fundraising();
        Assertions.assertTrue(account.getAccountWrap().getBalance().compareTo(BigDecimal.valueOf(50000)) > 0);
    }
}