package com.joshuajenster.school.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;

    public Person(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Person(Person p) {
        this.name = p.name;
        this.price = p.price;
    }
}
