package gigachad.banks.Accounts;

import gigachad.banks.BankEntity.BankImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DepositAccount extends AccountDecorator {
    private final int daysInMonth = 31;
    private int dayLimit;
    private int daysSpend;
    private BigDecimal interestSum;
    public DepositAccount(Account account) {
        super(account);
        dayLimit = AccountWrap.getConfiguration().getDepositAccountTime();
        daysSpend = 0;
    }

    @Override
    public void addMoney(BigDecimal money)
    {
        AccountWrap.addMoney(money);
    }

    @Override
    public void takeMoney(BigDecimal money) {
        if (daysSpend != 0)
            throw new IllegalArgumentException();
        if (AccountWrap.getBalance().compareTo(money) == -1)
            throw new IllegalArgumentException();
        AccountWrap.takeMoney(money);
    }

    @Override
    public void transferMoney(BigDecimal money, int id, BankImpl bank) {
        if (daysSpend != 0)
            throw new IllegalArgumentException();
        if (AccountWrap.getBalance().compareTo(money) == -1)
            throw new IllegalArgumentException();
        AccountWrap.transferMoney(money, id, bank);
    }

    @Override
    public void calculateInterest(int days) {
        if (days + daysSpend > daysInMonth) {
            int reminderAfter = daysSpend + days - daysInMonth;
            int reminderBefore = daysInMonth - daysSpend;
            interestSum.add(BigDecimal.valueOf(reminderBefore).multiply(Calculate()));
            AccountWrap.addMoney(interestSum);
            interestSum = BigDecimal.valueOf(0);
            daysSpend = reminderAfter;
        }
        else {
            interestSum.add(BigDecimal.valueOf(days).multiply(Calculate()));
            daysSpend += days;
        }
    }

    private BigDecimal Calculate() {
        return AccountWrap.getBalance()
                .multiply(AccountWrap.getConfiguration()
                        .getDepositInterestRate()
                        .findSuitRate(AccountWrap.getBalance())
                        .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_DOWN)
                        .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_DOWN));
    }
}