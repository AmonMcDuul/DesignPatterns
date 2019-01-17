package com.joshuajenster.school.repository;

import com.joshuajenster.school.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
