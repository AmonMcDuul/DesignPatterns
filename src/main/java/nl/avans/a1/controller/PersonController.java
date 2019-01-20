package nl.avans.a1.controller;


import nl.avans.a1.domain.Person;
import nl.avans.a1.domain.PersonMessage;
import nl.avans.a1.domain.ResponseObject;
import nl.avans.a1.repository.PersonRepository;
import nl.avans.a1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @GetMapping("")
    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> getPerson(@PathVariable Long id) {
        return personRepository.findById(id);
    }

    @PostMapping("")
    public Person addPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public Person editPerson(@PathVariable long id, @RequestBody Person person) {
        return personService.editPerson(personRepository.findById(id).get(), person, id);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable long id) {
        personRepository.deleteById(id);
    }

    @PostMapping(value = "/{id}/sendmessage", consumes = "application/json", produces = "application/json")
    public ResponseObject sendMessage(@PathVariable long id, @RequestBody PersonMessage message) {

        return personService.sendMessage(personRepository.findById(id).get(), message);
    }
}
