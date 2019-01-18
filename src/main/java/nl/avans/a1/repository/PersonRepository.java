package nl.avans.a1.repository;

import nl.avans.a1.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
