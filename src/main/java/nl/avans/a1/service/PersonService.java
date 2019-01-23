package nl.avans.a1.service;

import nl.avans.a1.domain.Person;
import nl.avans.a1.domain.PersonMessage;
import nl.avans.a1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Sends a message to a Person.
     * @param person
     * @param message
     * @return
     */
    public boolean sendMessage(Person person, PersonMessage message) {
        if(person != null)
            return person.sendMessage(message);
        else
            return false;
    }

    public Person editPerson(Person newPerson, long id) {
        newPerson.setId(id);
        return personRepository.save(newPerson);
    }

}
