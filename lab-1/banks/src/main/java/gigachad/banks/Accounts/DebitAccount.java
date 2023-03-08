package gigachad.banks.Accounts;

import gigachad.banks.BankEntity.BankImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DebitAccount extends AccountDecorator {
    private final int DaysInMonth = 31;
    private int daysSpend;
    private BigDecimal interestSum;
    public DebitAccount(Account account) {
        super(account);
        daysSpend = 0;
        interestSum = BigDecimal.valueOf(0);
    }

    @Override
    public void addMoney(BigDecimal money)
    {
        AccountWrap.addMoney(money);
    }

    @Override
    public void takeMoney(BigDecimal money) {
        if (AccountWrap.getBalance().compareTo(money) == -1)
            throw new IllegalArgumentException();
        AccountWrap.takeMoney(money);
    }

    @Override
    public void transferMoney(BigDecimal money, int id, BankImpl bank) {
        if (AccountWrap.getBalance().compareTo(money) == -1)
            throw new IllegalArgumentException();
        AccountWrap.transferMoney(money, id, bank);
    }

    @Override
    public void calculateInterest(int days) {
        if (days + daysSpend > DaysInMonth) {
            int reminderAfter = daysSpend + days - DaysInMonth;
            int reminderBefore = DaysInMonth - daysSpend;
            interestSum = interestSum.add(BigDecimal.valueOf(reminderBefore).multiply(Calculate()));
            daysSpend = reminderAfter;
            AccountWrap.addMoney(interestSum);
            interestSum = BigDecimal.valueOf(0);
            daysSpend = reminderAfter;
        }
        else {
            interestSum = interestSum.add(BigDecimal.valueOf(days).multiply(Calculate()));
            daysSpend += days;
        }
    }
    private BigDecimal Calculate() {
        BigDecimal balance = AccountWrap.getBalance();
        BigDecimal rate = AccountWrap.getConfiguration().getDebitInterestRate();
        rate = rate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_DOWN);
        rate = rate.divide(BigDecimal.valueOf(365), 10, RoundingMode.HALF_DOWN);
        balance = balance.multiply(rate);
        return balance;
    }
}