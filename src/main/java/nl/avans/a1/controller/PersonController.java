package nl.avans.a1.controller;

import nl.avans.a1.domain.Person;
import nl.avans.a1.domain.PersonMessage;
import nl.avans.a1.domain.ResponseObject;
import nl.avans.a1.repository.PersonRepository;
import nl.avans.a1.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("person")
public class PersonController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @GetMapping("")
    public Iterable<Person> getAllPersons() {
        LOG.info("get all Persons called");
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        LOG.info("get Person called");
        return personRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Person not found"));
    }

    @PostMapping("")
    public Person addPerson(@RequestBody Person person) {
        LOG.info("add Person called");
        return personRepository.save(person);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public Person editPerson(@PathVariable long id, @RequestBody Person person) {
        LOG.info("edit Person called on id: "+id);
        return personService.editPerson(person, id);
    }

    @DeleteMapping("/{id}")

    public void deletePerson(@PathVariable long id) {
        LOG.info("delete Person called on id: "+id);
        if(personRepository.findById(id).isPresent())
            personRepository.deleteById(id);
    }

    @PostMapping(value = "/{id}/sendmessage", consumes = "application/json", produces = "application/json")
    public ResponseObject sendMessage(@PathVariable long id, @RequestBody PersonMessage message) {
        LOG.info("sendMessage Person called on id: "+id);

        Person p = personRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Person not found"));

        return personService.sendMessage(p, message);
    }
}
