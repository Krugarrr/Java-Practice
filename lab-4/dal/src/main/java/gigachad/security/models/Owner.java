package gigachad.security.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date birthDate;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Pussy> pussies;
    public Owner() {
    }
    public Owner(long id, String name, Date birthDate) {
        pussies = new ArrayList<Pussy>();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
}
