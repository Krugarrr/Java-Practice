package gigachad.banks.BankEntity;

import gigachad.banks.Accounts.Account;
import gigachad.banks.Accounts.AccountDecorator;
import gigachad.banks.Accounts.DebitAccount;
import gigachad.banks.Accounts.DepositAccount;
import gigachad.banks.BankEntity.Config.BankConfiguration;
import gigachad.banks.ClientEntity.Client;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Bank entity class
 * @author Krugarrr
 * @version 1.0
 */
@Data
public class BankImpl implements Bank {

    /** Customer account list field, wrapped in a decorator */
    private List<AccountDecorator> accounts;

    /** Customer list field */
    private List<Client> clients;

    /** id generation mechanism - simple increment*/
    private int accountIdGenerator;

    /** Bank name field */
    private String name;

    /**
     * Bank configuration field
     * @see BankConfiguration
     * */
    private BankConfiguration configuration;

    /**
     * Constructor - creating a new bank object
     * @param name  - bank name
     * @param configuration - it's configuration (transaction limit & etc.) @see BankConfiguration
     */
    public BankImpl(String name, BankConfiguration configuration) {
        accounts = new ArrayList<AccountDecorator>();
        clients = new ArrayList<Client>();
        this.name = name;
        this.configuration = configuration;
        accountIdGenerator = 0;
    }

    /**
     * @see Bank#createDebitAccount(Client)
     */
    public void createDebitAccount(Client client) {
        clients.add(client);
        accounts.add(new DebitAccount(new Account(accountIdGenerator++, configuration, client)));
    }

    /**
     * @see Bank#createDepositAccount(Client)
     */
    public void createDepositAccount(Client client) {
        accounts.add(new DepositAccount(new Account(accountIdGenerator++, configuration, client)));
    }

    /**
     * @see Bank#createCreditAccount(Client)
     */
    public void createCreditAccount(Client client) {
        accounts.add(new DepositAccount(new Account(accountIdGenerator++, configuration, client)));
    }

    /**
     * @see Bank#changeConfiguration(BankConfiguration)
     */
    public void changeConfiguration(BankConfiguration newConfiguration) {
        configuration = newConfiguration;
        for (var client : clients) {
            client.notify(configuration, this);
        }
    }

    /**
     * @see Bank#fundraising(int)
     */
    public void fundraising(int days) {
        for (AccountDecorator account : accounts) {
            account.calculateInterest(days);
        }
    }

    /**
     * @see Bank#approveClient(Client)
     */
    public void approveClient(Client client) {
        var findClient = clients.stream().filter(c -> c == client).findFirst().orElseThrow();
        if (findClient.getAddress() != null && findClient.getDocument() != 0) {
            accounts.stream().filter(a -> a.getAccountWrap().getOwner() == findClient).forEach(a -> a.susInvoke());
        }
    }

    /**
     * @see Bank#getAccount(int, Client)
     */
    public AccountDecorator getAccount(int id, Client client) {
        var account = accounts.stream().filter(a -> a.getAccountWrap().getId() == id).findFirst().orElseThrow();
        if (!account.getAccountWrap().getOwner().equals(client))
            throw new IllegalArgumentException("Опааа не тот владелец");
        return account;
    }
}