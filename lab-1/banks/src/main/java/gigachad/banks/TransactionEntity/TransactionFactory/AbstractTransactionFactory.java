package gigachad.banks.TransactionEntity.TransactionFactory;

import gigachad.banks.BankEntity.BankImpl;
import gigachad.banks.TransactionEntity.AbstractTransaction;
import gigachad.banks.TransactionEntity.AddTypeTransaction;
import gigachad.banks.TransactionEntity.TakeTypeTransaction;
import gigachad.banks.TransactionEntity.TransferTypeTransaction;
import java.math.BigDecimal;

public class AbstractTransactionFactory implements TransactionFactory {
    public AbstractTransaction createAddTransaction(int id, BigDecimal money) {
        return new AddTypeTransaction(id, money);
    }

    public AbstractTransaction createTakeTransaction(int id, BigDecimal money) {
        return new TakeTypeTransaction(id, money);
    }

    public AbstractTransaction createTransferTransaction(int id, BigDecimal money, int anotherId, BankImpl bank) {
        return new TransferTypeTransaction(id, money, bank, anotherId);
    }
}