package gigachad.banks.CentralBank;

import gigachad.banks.BankEntity.BankImpl;
import gigachad.banks.BankEntity.Config.BankConfiguration;
import gigachad.banks.BankEntity.Bank;
import gigachad.banks.ClientEntity.Client;
import gigachad.banks.TimeManager;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.Data;
import lombok.NonNull;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 * Central bank singleton pattern
 * @author Krugarrr
 * @version 1.0
 */
@Data
public final class CentralBankSingleton implements CentralBank {

    /**singleton instance*/
    private static CentralBankSingleton instance;

    /**list of subsidiary banks*/
    private List<BankImpl> banks = new ArrayList<BankImpl>();

    /**client base list*/
    private List<Client> clients = new ArrayList<Client>();

    /**time-skipping mechanism*/
    private TimeManager timeManager;

    /** A Subject is a sort of bridge or proxy that acts both as Subscriber and as an Observable.
     * Because it is a Subscriber, it can subscribe to one or more Observables, and because it is an Observable,
     * it can pass through the items it observes by remitting them, and it can also emit new items.
     * {@link <a href="https://github.com/ReactiveX">...</a>}
     * */
    private Subject<TimeManager> subject = new Subject<TimeManager>() {
        @Override
        public boolean hasObservers() {
            return false;
        }

        @Override
        public boolean hasThrowable() {
            return false;
        }

        @Override
        public boolean hasComplete() {
            return false;
        }

        @Override
        public @Nullable Throwable getThrowable() {
            return null;
        }

        @Override
        protected void subscribeActual(@io.reactivex.rxjava3.annotations.NonNull Observer<? super TimeManager> observer) {

        }

        @Override
        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

        }

        @Override
        public void onNext(@io.reactivex.rxjava3.annotations.NonNull TimeManager timeManager) {

        }

        @Override
        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    /**
     * Private constructor - singleton realisation
     */
    private CentralBankSingleton() {
        timeManager = new TimeManager();
        subject.subscribe(t -> t.updateTime());
    }

    /**
     * Static method - returns the state of the central bank
     * @return CentralBankSingleton
     */
    public static CentralBankSingleton getInstance() {
            if (instance == null) {
                instance = new CentralBankSingleton();
            }
            return instance;
    }

    /**
     * @see CentralBank#addBank(String, BankConfiguration)
     */
    public BankImpl addBank(@NonNull String bankName, BankConfiguration configuration) {
            var newBank = new BankImpl(bankName, configuration);
            banks.add(newBank);
            return newBank;
    }

    /**
     * @see CentralBank#addBank(String, BankConfiguration)
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * @see CentralBank#fundraising()
     */
    public void fundraising() {
        for (Bank bank : banks) {
            bank.fundraising(timeManager.updateTime());
        }
    }
}