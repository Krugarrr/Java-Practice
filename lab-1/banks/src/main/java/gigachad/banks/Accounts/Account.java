package gigachad.banks.Accounts;

import gigachad.banks.BankEntity.BankImpl;
import gigachad.banks.BankEntity.Config.BankConfiguration;
import gigachad.banks.ClientEntity.Client;
import gigachad.banks.TransactionEntity.AbstractTransaction;
import gigachad.banks.TransactionEntity.TransactionFactory.AbstractTransactionFactory;
import gigachad.banks.TransactionEntity.TransactionFactory.TransactionFactory;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Account {
    private final BigDecimal StartSum = BigDecimal.valueOf(0);
    private List<AbstractTransaction> transactionHistory;
    private int idGenerator;
    private int id;
    private BigDecimal balance;
    private BankConfiguration configuration;
    private Suspection sussy;
    private TransactionFactory transactionFactory;
    private Client owner;

    public Account(int id, BankConfiguration configuration, Client owner) {
        this.configuration = configuration;
        this.owner = owner;
        this.id = id;
        balance = StartSum;

        idGenerator = 0;
        transactionHistory = new ArrayList<AbstractTransaction>();
        transactionFactory = new AbstractTransactionFactory();
        if (this.owner.getAddress() == null || this.owner.getDocument() == 0)
            sussy = Suspection.Sus;
    }

    public void addMoney(BigDecimal money) {
        balance = balance.add(money);
        AbstractTransaction transaction = transactionFactory.createAddTransaction(idGenerator++, money);
        transactionHistory.add(transaction);
    }

    public void takeMoney(BigDecimal money) {
        transactionLimitValidation(money);
        balance = balance.subtract(money);
        AbstractTransaction transaction = transactionFactory.createTakeTransaction(idGenerator++, money);
        transactionHistory.add(transaction);
    }

    public void transferMoney(BigDecimal money, int id, BankImpl bank) {
        transactionLimitValidation(money);
        bank.getAccount(id, getOwner()).addMoney(money);
        AbstractTransaction transaction = transactionFactory.createTransferTransaction(idGenerator++, money, id, bank);
        transactionHistory.add(transaction);
    }

    public void approveClient()
    {
        sussy = Suspection.NotSus;
    }

    public AbstractTransaction getTransaction(int id) {
        return transactionHistory.stream().filter(t -> t.getId() == id).findFirst().orElseThrow();
    }
    private void transactionLimitValidation(BigDecimal money) {
        if (sussy == Suspection.Sus) {
            if (money.compareTo(configuration.getTransactionLimit()) == 1)
                throw new IllegalArgumentException();
        }
    }
}