package nl.avans.a1.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    private Date endDate;

    private Date startDate;

    @OneToMany
    private List<Person> persons = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Note> notes = new ArrayList<>();


    public void addPerson(Person p) {
        getPersons().add(p);
    }

    public void addNote(Note n) {
        getNotes().add(n);
    }


}
