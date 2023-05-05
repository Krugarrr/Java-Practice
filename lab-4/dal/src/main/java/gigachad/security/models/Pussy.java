package gigachad.security.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Pussy")
public class Pussy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;
    private String name;
    private Date birthDate;
    private Breed breed;
    private Color color;

    @ManyToOne(fetch = FetchType.EAGER)
    private Owner owner;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pussy> friends;
    public Pussy() {
    }
    public Pussy(long number, String name, Date birthDate, Breed breed, Color color) {
        friends = new ArrayList<Pussy>();
        this.number = number;
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }
}
