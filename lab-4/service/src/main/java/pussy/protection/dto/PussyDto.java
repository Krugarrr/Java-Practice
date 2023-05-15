package pussy.protection.dto;


import pussy.protection.models.Breed;
import pussy.protection.models.Color;
import lombok.Data;

import java.util.Date;

@Data
public class PussyDto {
    private long number;
    private String name;
    private Date birthDate;
    private Breed breed;
    private Color color;
    private OwnerDto owner;

    public PussyDto() {
    }
    public PussyDto(long number, String name, Date birthDate, Breed breed, Color color, OwnerDto ownerDto) {
        this.number = number;
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
        this.owner = ownerDto;
    }
}
