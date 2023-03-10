package gigachad.pussy.dto;

import lombok.Data;
import org.joda.time.MutableDateTime;

@Data
public class OwnerDto {
    private String name;
    private MutableDateTime birthDate;

    public OwnerDto() {
    }

    public OwnerDto(String name, MutableDateTime birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
