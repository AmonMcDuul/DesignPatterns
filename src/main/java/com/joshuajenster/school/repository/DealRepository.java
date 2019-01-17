package com.joshuajenster.school.repository;

import com.joshuajenster.school.domain.Deal;
import org.springframework.data.repository.CrudRepository;

public interface DealRepository extends CrudRepository<Deal, Long> {
}
