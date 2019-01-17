package com.joshuajenster.school.repository;

import com.joshuajenster.school.domain.BaseOrder;
import org.springframework.data.repository.CrudRepository;

public interface DecoratedOrderRepository extends CrudRepository<BaseOrder, Long> {
        }
