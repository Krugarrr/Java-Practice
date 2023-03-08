package gigachad.banks.TransactionEntity.TransactionFactory;

import gigachad.banks.BankEntity.BankImpl;
import gigachad.banks.TransactionEntity.AbstractTransaction;

import java.math.BigDecimal;

public interface TransactionFactory {
    public AbstractTransaction createAddTransaction(int id, BigDecimal money);
    public AbstractTransaction createTakeTransaction(int id, BigDecimal money);
    public AbstractTransaction createTransferTransaction(int id, BigDecimal money, int anotherId, BankImpl bank);
}