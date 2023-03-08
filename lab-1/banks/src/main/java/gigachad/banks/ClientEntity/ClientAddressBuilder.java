package gigachad.banks.ClientEntity;

import gigachad.banks.ClientEntity.Interfaces.AddressBuilder;

/**
 * Address model builder pattern
 * Implements
 * @see AddressBuilder
 * @author Krugarrr
 * @version 1.0
 */
public class ClientAddressBuilder implements AddressBuilder {

    /** address city field */
    private String city;

    /** address street field */
    private String street;

    /** address house number field */
    private int house;

    /**
     * @see AddressBuilder#withCity(String)
     */
    public AddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * @see AddressBuilder#withStreet(String)
     */
    public AddressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    /**
     * @see AddressBuilder#withHouse(int)
     */
    public AddressBuilder withHouse(int house) {
        this.house = house;
        return this;
    }
    
    /**
     * @see AddressBuilder#build()
     */
    public Address build() {
        return new Address(
                city,
                street,
                house);
    }
}
