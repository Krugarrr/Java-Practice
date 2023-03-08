package gigachad.banks.TransactionEntity;

import gigachad.banks.Accounts.AccountDecorator;
import gigachad.banks.BankEntity.BankImpl;
import java.math.BigDecimal;

public class TransferTypeTransaction extends AbstractTransaction {

    private BankImpl transferBank;
    private int transferAccountId;
    public TransferTypeTransaction(
            int id,
            BigDecimal money,
            BankImpl transferBank,
            int transferAccountId) {
        super(id, money);
        setType(TransactionType.Transfer);
        this.transferBank = transferBank;
        this.transferAccountId = transferAccountId;
    }
    @Override
    protected void cancelTransferMoney(AccountDecorator account) {
        account.addMoney(getSum());
        transferBank.getAccount(transferAccountId, account.getAccountWrap().getOwner()).takeMoney(getSum());
    }
}