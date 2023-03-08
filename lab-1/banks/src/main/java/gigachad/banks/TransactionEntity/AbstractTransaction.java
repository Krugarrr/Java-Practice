package gigachad.banks.TransactionEntity;

import gigachad.banks.Accounts.AccountDecorator;
import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@Data
public abstract class AbstractTransaction {
    private DateTime time;
    private int id;
    private BigDecimal sum;
    private TransactionType type;
    private TransactionStatus status;
    public AbstractTransaction(int id, BigDecimal money) {
        this.id = id;
        time = new DateTime();
        sum = money;
        status = TransactionStatus.Committed;
    }

    public void cancelTransactionTemplateMethod(AccountDecorator account) {
        cancelAddMoney(account);
        cancelTakeMoney(account);
        cancelTransferMoney(account);
    }

    protected void cancelAddMoney(AccountDecorator account) { }
    protected void cancelTakeMoney(AccountDecorator account) { }
    protected void cancelTransferMoney(AccountDecorator account) { }
    protected void ChangeStatus() {
        status = TransactionStatus.Aborted;
    }
}