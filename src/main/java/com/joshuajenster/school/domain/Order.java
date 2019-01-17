package com.joshuajenster.school.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Orders")
@Getter
@Setter
@NoArgsConstructor
public class Order extends BaseOrder {

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @Transient
    private BaseOrder baseOrder;

    public int price() {
        int price = 0;
        for(Product product: products) {
            price += product.getPrice();
        }
        return price;
    }

    public void add(Product p) {
        products.add(p);
    }
}
