package gigachad.banks.Accounts;

import gigachad.banks.BankEntity.BankImpl;
import java.math.BigDecimal;

public class CreditAccount extends AccountDecorator {
    public CreditAccount(Account account)
    {
        super(account);
    }

    @Override
    public void addMoney(BigDecimal money)
    {
        AccountWrap.addMoney(money.subtract(TakeComission(money)));
    }

    @Override
    public void takeMoney(BigDecimal money) {
        if (AccountWrap.getBalance().subtract(money).compareTo(AccountWrap.getConfiguration().getCreditLimit()) == -1)
            throw new IllegalArgumentException();
        AccountWrap.takeMoney(TakeComission(money));
        AccountWrap.takeMoney(money);
    }

    @Override
    public void transferMoney(BigDecimal money, int id, BankImpl bank) {
        if (AccountWrap.getBalance().subtract(money).compareTo(AccountWrap.getConfiguration().getCreditLimit()) == -1)
            throw new IllegalArgumentException();
            AccountWrap.transferMoney(money.subtract(TakeComission(money)), id, bank);
    }

    private BigDecimal TakeComission(BigDecimal money) {
        return (money.multiply(AccountWrap
                .getConfiguration()
                .getCreditCommission()))
                .divide(BigDecimal.valueOf(100));
    }
}