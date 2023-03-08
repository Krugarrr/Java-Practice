package gigachad.banks.TransactionEntity;

import gigachad.banks.Accounts.AccountDecorator;
import java.math.BigDecimal;

public class TakeTypeTransaction extends AbstractTransaction {
    public TakeTypeTransaction(int id, BigDecimal money) {
        super(id, money);
        setType(TransactionType.Take);
    }

    @Override
    protected void cancelTakeMoney(AccountDecorator account)
    {
        account.addMoney(getSum());
    }
}
