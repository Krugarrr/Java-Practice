package gigachad.springpussy.dto;


import gigachad.springpussy.models.Pussy;
import lombok.Data;
import gigachad.springpussy.models.Breed;
import gigachad.springpussy.models.Color;
import gigachad.springpussy.models.Owner;
import java.util.Date;
import java.util.List;

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
