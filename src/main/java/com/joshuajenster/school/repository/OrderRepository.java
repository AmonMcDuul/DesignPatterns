package com.joshuajenster.school.repository;

import com.joshuajenster.school.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
