package com.joshuajenster.school.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Deal {

    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private List<Person> products = new ArrayList<>();


    public void add(Person p) {
        products.add(p);
    }

    public Person find(Long id) {
        for(Person p : products) {
            if(p.getId() == id)
                return p;
        }
        return null;
    }
}
