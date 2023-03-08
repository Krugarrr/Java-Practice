package gigachad.banks.ClientEntity;

import gigachad.banks.BankEntity.Config.BankConfiguration;
import gigachad.banks.BankEntity.Bank;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.Subject;
import lombok.Data;

/**
 * Client entity class
 * Implements
 * @see gigachad.banks.ClientEntity.Interfaces.Client
 * @author Krugarrr
 * @version 1.0
 */
@Data
public class Client implements gigachad.banks.ClientEntity.Interfaces.Client {

    /** client name field */
    private String name;

    /** client second name field */
    private String surname;

    /** client address field
     * @see Address#Address(String, String, int)
     */
    private Address address;

    /** client document number field */
    private int document;

    /** A Subject is a sort of bridge or proxy that acts both as Subscriber and as an Observable.
     * Because it is a Subscriber, it can subscribe to one or more Observables, and because it is an Observable,
     * it can pass through the items it observes by remitting them, and it can also emit new items.
     * {@link <a href="https://github.com/ReactiveX">...</a>}
     * */
    private Subject<Integer> Sub;

    /**
     * Constructor - creating a new client object
     * @param name - client name
     * @param surname - client second name
     * @param address - client address
     * @param document - client document number
     */
    public Client(
            String name,
            String surname,
            Address address,
            int document) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.document = document;
        Sub = new Subject<>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            protected void subscribeActual(@NonNull Observer<? super Integer> observer) {

            }

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
        };
    }

    /**
     * @see gigachad.banks.ClientEntity.Interfaces.Client#notify(BankConfiguration, Bank)
     */
    public void notify(BankConfiguration configuration, Bank bank) {
        System.out.println("Interest list in bank" + bank + "has changed:");
        System.out.println("Debit interest rate:" + configuration.getDebitInterestRate());
        System.out.println("Credit comission:" + configuration.getCreditCommission());
        System.out.println("Credit limit:" + configuration.getCreditLimit());
        System.out.println("Deposit account time:" + configuration.getDepositAccountTime());
        System.out.println("Transaction limit:" + configuration.getTransactionLimit());
        System.out.println("Deposit interest rates:");
        for (var dip : configuration.getDepositInterestRate().getDepositInterestPoints())
        {
            System.out.println("\tFrom" + dip.getMinSum() + "to" + dip.getMaxSum() + " deposit interest rate is: " + dip.getInterestRate());
        }
        System.out.println("\tCommon deposit interest rate: " + configuration.getDepositInterestRate().getMaxInterestRate());
    }
}

