package gigachad.banks.ClientEntity.Interfaces;

import gigachad.banks.BankEntity.Config.BankConfiguration;
import gigachad.banks.BankEntity.Bank;

public interface Client {

    /**
     * Method that implements the client notification mechanism (about changing the bank's policy)
     * @param configuration - new config
     * @param bank - client's bank
     */
    void notify(BankConfiguration configuration, Bank bank);
}