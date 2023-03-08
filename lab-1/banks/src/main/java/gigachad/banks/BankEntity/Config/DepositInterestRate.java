package gigachad.banks.BankEntity.Config;

import lombok.Data;
import lombok.NonNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class DepositInterestRate {
    @NonNull private BigDecimal maxInterestRate;
    private List<DepositInterestPoint> depositInterestPoints;
    public static DepositInterestRateBuilder createBuilder() {
        return new DepositInterestRateBuilder();
    }
    public DepositInterestRate(List<DepositInterestPoint> points, BigDecimal maxInterestRate) {
        depositInterestPoints = new ArrayList<DepositInterestPoint>();
        depositInterestPoints = points;
        this.maxInterestRate = maxInterestRate;
    }

    public BigDecimal findSuitRate(BigDecimal balance) {
        Optional<DepositInterestPoint> point = depositInterestPoints.stream().filter(p -> balance.compareTo(p.getMinSum()) >= 0 && balance.compareTo(p.getMaxSum()) <= 0).findFirst();
        return point.get().getInterestRate();
    }
}