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
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product(Product p) {
        this.name = p.name;
        this.price = p.price;
    }
}
