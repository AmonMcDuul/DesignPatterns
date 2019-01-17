package com.joshuajenster.school.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public abstract class DecoratedOrder extends BaseOrder {

    @OneToOne
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private BaseOrder baseOrder;

    protected DecoratedOrder() {

    }

    protected DecoratedOrder(BaseOrder baseOrder) {
        this.baseOrder = baseOrder;
    }
}
