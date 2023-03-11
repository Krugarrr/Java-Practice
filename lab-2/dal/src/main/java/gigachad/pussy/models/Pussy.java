package gigachad.pussy.models;

import jakarta.persistence.*;
import lombok.Data;
import org.joda.time.MutableDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Pussy")
public class Pussy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;
    private String name;
    private MutableDateTime birthDate;
    private Breed breed;
    private Color color;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "Owner")
    private Owner owner;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Pussy> friends;
    public Pussy() {
    }
    public Pussy(long number, String name, MutableDateTime birthDate, Breed breed, Color color) {
        friends = new ArrayList<Pussy>();
        this.number = number;
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }
}
