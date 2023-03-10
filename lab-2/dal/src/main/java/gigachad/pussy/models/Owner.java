package gigachad.pussy.models;

import jakarta.persistence.*;
import lombok.Data;
import org.joda.time.MutableDateTime;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private MutableDateTime birthDate;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Pussy> pussies;
    public Owner() {
    }
    public Owner(String name, MutableDateTime birthDate) {
        pussies = new ArrayList<Pussy>();
        this.name = name;
        this.birthDate = birthDate;
    }
}
