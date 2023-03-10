package gigachad.pussy.dto;

import gigachad.pussy.models.Breed;
import gigachad.pussy.models.Color;
import lombok.Data;
import org.joda.time.MutableDateTime;

@Data
public class PussyDto {
    private String name;
    private MutableDateTime birthDate;
    private Breed breed;
    private Color color;
    public PussyDto(String name, MutableDateTime birthDate, Breed breed, Color color) {
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }
}
