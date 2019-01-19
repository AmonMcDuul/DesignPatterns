package nl.avans.a1.controller;

import nl.avans.a1.business.MessageType;
import nl.avans.a1.domain.Person;
import nl.avans.a1.domain.PersonMessage;
import nl.avans.a1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("")
    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping(value = "/{id}/sendmessage" , consumes = "application/json")
    public String sendMessage(@PathVariable long id, @RequestBody PersonMessage message) {
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()) {
            Person p = person.get();
            p.sendMessage(message);
            return "works!!";
        } else {
            return "person not found :(";
        }
    }
}
