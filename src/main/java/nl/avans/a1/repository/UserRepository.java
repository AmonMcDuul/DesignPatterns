package nl.avans.a1.repository;

import nl.avans.a1.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
