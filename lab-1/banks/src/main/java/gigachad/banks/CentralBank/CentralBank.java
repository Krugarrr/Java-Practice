package gigachad.banks.CentralBank;

import gigachad.banks.BankEntity.BankImpl;
import gigachad.banks.BankEntity.Config.BankConfiguration;
import gigachad.banks.ClientEntity.Client;

interface CentralBank {

    /**
     * Method - adding bank to list of subsidiary banks
     * @param bankName
     * @param configuration
     * @see BankImpl#BankImpl(String, BankConfiguration)
     * @return Bank
     */
    BankImpl addBank(String bankName, BankConfiguration configuration);

    /**
     * Method - adding client to list of clients
     * @param client
     */
    void addClient(Client client);

    /**
     * The method of updating accounts at the rate offered by the bank
     */
    void fundraising();
}
