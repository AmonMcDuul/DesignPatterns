package nl.avans.a1.service;

import nl.avans.a1.domain.Person;
import nl.avans.a1.domain.PersonMessage;
import nl.avans.a1.domain.ResponseObject;
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
    public ResponseObject sendMessage(Person person, PersonMessage message) {
        if(person != null) {
            if(person.sendMessage(message))
                return new ResponseObject("Success", "The message has been sent succesfully.", 200);
            else
                return new ResponseObject("Error", "Something went wrong while sending the message.", 500);
        } else {
            return new ResponseObject("Error", "Person was not found.", 404);
        }
    }

    public Person editPerson(Person newPerson, long id) {
        newPerson.setId(id);
        return personRepository.save(newPerson);
    }

}
