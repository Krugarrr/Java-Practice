package gigachad.banks.BankEntity;

import gigachad.banks.Accounts.AccountDecorator;
import gigachad.banks.BankEntity.Config.BankConfiguration;
import gigachad.banks.ClientEntity.Address;
import gigachad.banks.ClientEntity.Client;

public interface Bank {

    /**
     * Debit Account Creation Method
     * @param client  - account holder
     */
    void createDebitAccount(Client client);

    /**
     * Deposit account creation method
     * @param client  - account holder
     */
    void createDepositAccount(Client client);

    /**
     * Credit account creation method
     * @param client  - account holder
     */
    void createCreditAccount(Client client);

    /**
     * Bank configuration change method
     * @param newConfiguration - new generated config
     */
    void changeConfiguration(BankConfiguration newConfiguration);

    /**
     * The method of updating accounts at the rate offered by the bank
     * @param days - how many days have passed since the last update
     */
    void fundraising(int days);

    /**
     * The method of gaining access to the account, works only if it matches the identity of the owner
     * @param id - account number
     * @param client - account owner
     * @return account wrap
     */
    AccountDecorator getAccount(int id, Client client);

    /**
     * Verification method for a client who previously did not have a passport and document
     * @see Client#Client(String, String, Address, int)
     * @param client - client
     */
    void approveClient(Client client);

}