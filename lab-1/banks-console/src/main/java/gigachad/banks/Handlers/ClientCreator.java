package gigachad.banks.Handlers;

import gigachad.banks.CentralBank.CentralBankSingleton;
import gigachad.banks.ClientEntity.Address;
import gigachad.banks.ClientEntity.ClientAddressBuilder;
import gigachad.banks.ClientEntity.Client;
import gigachad.banks.ClientEntity.ClientBuilder;
import gigachad.banks.ClientEntity.Interfaces.AddressBuilder;

import java.util.Scanner;

public class ClientCreator implements IEntityCreatorHandler {
    private Scanner in;
    private ClientBuilder clientBuilder = new ClientBuilder();
    private ClientBuilder builder = new ClientBuilder();
    private ClientAddressBuilder clientAddressBuilder = new ClientAddressBuilder();
    private Client client;
    public ClientCreator(Scanner in) {
        this.in = in;
    }

    public ClientCreator (Client client) {
        this.client = client;
    }

    public void createEntity() {
        var centralBank = CentralBankSingleton.getInstance();
        centralBank.addClient(client);
    }
    public IEntityCreatorHandler onNext() {
        AddressBuilder addressBuilder = new ClientAddressBuilder();
        Address address;
        address = addressBuilder
                .withStreet("Кронва")
                .withCity("Kukuevo")
                .withHouse(147)
                .build();
        System.out.println("Задайте имя вашему рабу системы:" );
        clientBuilder.withName(in.next());
        System.out.println("и фамилию:" );
        clientBuilder.withSurname(in.next());
        clientBuilder.withAddress(address);
        System.out.println("Адресочек не хочешь сказать?: да/нет" );
        String choice = in.next();
        if (choice.equals("да") || choice.equals("Да")) {
            System.out.println("Город: " );
            addressBuilder.withCity(in.next());
            System.out.println("Улица: " );
            addressBuilder.withStreet(in.next());
            System.out.println("Номер хаты: " );
            addressBuilder.withHouse(in.nextInt());
        }
        else {
            System.out.println("Блиииин, теперь не получится поджечь твою хату :(" );
        }

        System.out.println("Может, документики хотите указать?: да/нет" );
        choice = in.next();
        if (choice.equals("да") || choice.equals("Да")) {
            System.out.println("Укажите номер: " );
            clientBuilder.withDocument(in.nextInt());
        }
        else {
            System.out.println("Попуск.... ты в муте" );
        }

        return new ClientCreator(clientBuilder.build());
    }
}

