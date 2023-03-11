package gigachad.pussy.dto;

import lombok.Data;
import org.joda.time.MutableDateTime;

@Data
public class OwnerDto {
    private long id;
    private String name;
    private MutableDateTime birthDate;

    public OwnerDto() {
    }

    public OwnerDto(long id, String name, MutableDateTime birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
}
