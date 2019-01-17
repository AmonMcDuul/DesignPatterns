package com.joshuajenster.school.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class OrderOption extends DecoratedOrder {

    private String name;

    @Transient
    BaseOrder decoratedOrder;

    private int price;

    public OrderOption( String name, int price, BaseOrder decoratedOrder) {
        super(decoratedOrder);
        this.decoratedOrder=decoratedOrder;
        this.name = name;
        this.price = price;
    }

    @Override
    public int price() {
        return decoratedOrder.price()+price;
    }

    public String toString() {
        return decoratedOrder.toString()+ ", "+name;
    }
}
