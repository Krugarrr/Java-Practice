package gigachad.banks.BankEntity.Config;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DepositInterestPoint {
    private BigDecimal minSum;
    private BigDecimal maxSum;
    private BigDecimal interestRate;
}