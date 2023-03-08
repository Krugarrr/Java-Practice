package gigachad.banks.TransactionEntity;

import gigachad.banks.Accounts.AccountDecorator;
import java.math.BigDecimal;

public class AddTypeTransaction extends AbstractTransaction {
    public AddTypeTransaction(int id, BigDecimal money) {
        super(id, money);
        setType(TransactionType.Add);
    }

    @Override
    protected void cancelAddMoney(AccountDecorator account)
    {
        account.takeMoney(getSum());
    }
}
