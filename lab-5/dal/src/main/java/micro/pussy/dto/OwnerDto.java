package micro.pussy.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class OwnerDto {
    private long id;
    private String name;
    private Date birthDate;

    public OwnerDto() {
    }

    public OwnerDto(long id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
}
