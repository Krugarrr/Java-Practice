package gigachad.banks.BankEntity.Config;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Bank configuration model class
 * @author Krugarrr
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class BankConfiguration
{

    /**debit interest rate field*/
    private BigDecimal debitInterestRate;

    /**transaction limit field*/
    private BigDecimal transactionLimit;

    /**credit limit field*/
    private BigDecimal creditLimit;

    /**credit commission field*/
    private BigDecimal creditCommission;

    /**deposit interest rate field
     * @see DepositInterestRate
     * */
    private DepositInterestRate depositInterestRate;

    /**deposit account time field*/
    private int depositAccountTime;
}

