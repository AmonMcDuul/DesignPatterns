package com.joshuajenster.school.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseOrder implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public abstract int price();
}
