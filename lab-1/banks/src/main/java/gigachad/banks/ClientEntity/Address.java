package gigachad.banks.ClientEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Address model class
 * @author Krugarrr
 * @version 1.0
 */
@Data
public class Address {

    /** Constant that does not allow you to specify a house number less than zero  */
    private final int minimalHouseNumber = 0;

    /**address city field*/
    private String city;

    /**address street field*/
    private String street;

    /**address house number field*/
    private int houseNumber;

    /**
     * Constructor - creating new address object
     * @param city - address city
     * @param street - address street
     * @param houseNumber - address house number
     */
    public Address(String city, String street, int houseNumber) {
            validateAddress(city, street, houseNumber);
            this.city = city;
            this.street = street;
            this.houseNumber = houseNumber;
    }

    /**
     * Validation method
     * @param city
     * @param street
     * @param houseNumber
     */
    private void validateAddress(String city, String street, int houseNumber) {
        if (StringUtils.isEmpty(street))
            throw new IllegalArgumentException();

        if (StringUtils.isEmpty(city))
            throw new IllegalArgumentException();

        if (houseNumber < minimalHouseNumber)
            throw new IllegalArgumentException();
    }
}