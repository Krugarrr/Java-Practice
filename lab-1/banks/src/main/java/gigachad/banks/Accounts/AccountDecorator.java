package gigachad.banks.Accounts;

import gigachad.banks.BankEntity.BankImpl;
import gigachad.banks.TransactionEntity.AbstractTransaction;
import lombok.Data;

import java.math.BigDecimal;

@Data
public abstract class AccountDecorator
{
    protected AccountDecorator(Account account)
    {
        AccountWrap = account;
    }

    protected Account AccountWrap;
    public void susInvoke() {
        AccountWrap.approveClient();
    }

    public void addMoney(BigDecimal money)
    {
        AccountWrap.addMoney(money);
    }

    public void takeMoney(BigDecimal money)
    {
        AccountWrap.takeMoney(money);
    }

    public void transferMoney(BigDecimal money, int id, BankImpl bank)
    {
        AccountWrap.transferMoney(money, id, bank);
    }

    public AbstractTransaction getTransaction(int id)
    {
        return AccountWrap.getTransactionHistory().stream().filter(t -> t.getId() == id).findFirst().orElseThrow();
    }

    public void calculateInterest(int days) { }

    public void cancelTransaction(int id)
    {
        getTransaction(id).cancelTransactionTemplateMethod(this);
    }
}