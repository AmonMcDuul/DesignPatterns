package nl.avans.a1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Deal {

    public Deal(int value, Date endDate, Date startDate, List<Person> persons) {
        this.value = value;
        this.endDate = endDate;
        this.startDate = startDate;
        this.persons = persons;
    }

    @Id
    @GeneratedValue
    private Long id;

    private int value;

    private Date endDate;

    private Date startDate;

    @OneToMany
    private List<Person> persons;


}
