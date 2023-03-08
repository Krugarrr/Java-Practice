package gigachad.banks.ClientEntity.Interfaces;

import gigachad.banks.ClientEntity.Address;

/**
 * Address builder interface
 * @author Krugarrr
 * @version 1.0
 */
public interface AddressBuilder {

    /**
     * Builder method - adding address city
     * @param city
     * @return IAddressBuilder
     */
    AddressBuilder withCity(String city);

    /**
     * Builder method - adding address street
     * @param street
     * @return IAddressBuilder
     */
    AddressBuilder withStreet(String street);

    /**
     * Builder method - adding address house number
     * @param house
     * @return IAddressBuilder
     */
    AddressBuilder withHouse(int house);

    /**
     * Builder method - returns complete address object after building
     * @return IAddressBuilder
     */
    Address build();
}