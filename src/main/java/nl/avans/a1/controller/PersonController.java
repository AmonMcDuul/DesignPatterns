package nl.avans.a1.controller;

import nl.avans.a1.domain.Person;
import nl.avans.a1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}/sendmessage")
    public String sendMessage(@RequestParam long id) {
        Optional<Person> person = personRepository.findById(id);
        return "works";
    }
}
