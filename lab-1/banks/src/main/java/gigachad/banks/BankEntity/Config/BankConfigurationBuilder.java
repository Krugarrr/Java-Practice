package gigachad.banks.BankEntity.Config;

import java.math.BigDecimal;

public class BankConfigurationBuilder {
    private BigDecimal debitInterestRate;
    private BigDecimal transactionLimit;
    private BigDecimal creditLimit;
    private BigDecimal creditComission;
    private DepositInterestRate depositInterestRate;
    private int depositAccountTime;

    public BankConfigurationBuilder() {
        debitInterestRate = BigDecimal.valueOf(0);
        transactionLimit = BigDecimal.valueOf(0);
        creditLimit = BigDecimal.valueOf(0);
        creditComission = BigDecimal.valueOf(0);
        depositInterestRate = null;
        depositAccountTime = 0;
    }

    public BankConfigurationBuilder withDebitInterestRate(BigDecimal rate) {
        debitInterestRate = rate;
        return this;
    }

    public BankConfigurationBuilder withTransactionLimit(BigDecimal limit) {
        transactionLimit = limit;
        return this;
    }

    public BankConfigurationBuilder withCreditLimit(BigDecimal limit) {
        creditLimit = limit;
        return this;
    }

    public BankConfigurationBuilder withCreditComission(BigDecimal comission) {
        creditComission = comission;
        return this;
    }

    public BankConfigurationBuilder withDepositInterestRate(DepositInterestRate rate) {
        depositInterestRate = rate;
        return this;
    }

    public BankConfigurationBuilder withDepositAccountTime(int time) {
        depositAccountTime = time;
        return this;
    }

    public BankConfiguration build() {
        return new BankConfiguration(
                debitInterestRate,
                transactionLimit,
                creditLimit,
                creditComission,
                depositInterestRate,
                depositAccountTime);
    }
}
