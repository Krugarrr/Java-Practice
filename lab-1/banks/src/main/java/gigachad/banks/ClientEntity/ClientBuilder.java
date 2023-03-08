package gigachad.banks.ClientEntity;


/**
 * Client entity builder pattern
 * @see Client
 * @author Krugarrr
 * @version 1.0
 */
public class ClientBuilder {

    /** client name field */
    private String clientName;

    /** client second name field */
    private String clientSurname;

    /** client address
     * @see Address
     *  field */
    private Address clientAddress;

    /** client document number field */
    private int clientDocument;

    /**
     * Constructor - creating client builder object
     */
    public ClientBuilder() {
        clientName = null;
        clientSurname = null;
        clientAddress = null;
        clientDocument = 0;
    }

    /**
     * Builder method - adding client name
     * @param name
     * @return ClientBuilder
     */
    public ClientBuilder withName(String name) {
        clientName = name;
        return this;
    }

    /**
     * Builder method - adding client second name
     * @param surname
     * @return ClientBuilder
     */
    public ClientBuilder withSurname(String surname) {
        clientSurname = surname;
        return this;
    }

    /**
     * Builder method - adding client address
     * @see Address
     * @param address
     * @return ClientBuilder
     */

    public ClientBuilder withAddress(Address address) {
        clientAddress = address;
        return this;
    }

    /**
     * Builder method - adding client document number
     * @param document
     * @return ClientBuilder
     */
    public ClientBuilder withDocument(int document) {
        clientDocument = document;
        return this;
    }

    /**
     * Builder method - returns complete client object after building
     * @return Client
     */
    public Client build() {
        return new Client(
                clientName,
                clientSurname,
                clientAddress,
                clientDocument);
    }
}
