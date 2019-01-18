package nl.avans.a1.controller;

import nl.avans.a1.domain.User;
import nl.avans.a1.exceptions.UserNotFoundException;
import nl.avans.a1.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "login")
    public User login(@RequestHeader String username, @RequestHeader String password) throws UserNotFoundException {
        LOG.info("user requested");
        User user = userRepository.findByUsernameAndPassword(username, password);
        if(user != null) {
            LOG.info("user found");
            return user;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
