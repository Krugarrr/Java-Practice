package gigachad.pussy.dto;

import gigachad.pussy.models.Breed;
import gigachad.pussy.models.Color;
import gigachad.pussy.models.Owner;
import lombok.Data;
import org.joda.time.MutableDateTime;

@Data
public class PussyDto {
    private long number;
    private String name;
    private MutableDateTime birthDate;
    private Breed breed;
    private Color color;
    private Owner owner;
    public PussyDto(long number, String name, MutableDateTime birthDate, Breed breed, Color color) {
        this.number = number;
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }
}
