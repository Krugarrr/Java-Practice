package gigachad.banks.BankEntity.Config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DepositInterestRateBuilder {
    private List<DepositInterestPoint> points;
    private BigDecimal maxRate;
    public DepositInterestRateBuilder() {
        points = new ArrayList<DepositInterestPoint>();
        maxRate = BigDecimal.valueOf(0);
    }

    public DepositInterestRateBuilder addInterestPoint(DepositInterestPoint point) {
        points.add(point);
        return this;
    }

    public DepositInterestRateBuilder addMaxRate(BigDecimal rate) {
        maxRate = rate;
        return this;
    }

    public DepositInterestRate build()
    {
        return new DepositInterestRate(points, maxRate);
    }
}