package nl.avans.a1.business;

import nl.avans.a1.business.library.EmailAdapter;
import nl.avans.a1.domain.Person;

import javax.validation.constraints.Email;

public class MessageEmailService extends MessageService {

    Person person;
    String message, subject;

    @Override
    boolean sendMessage() {
        if(person.getEmail() != null)
            return EmailAdapter.sendEmailMessage(person.getEmail(), subject, message);
        else
            return false;
    }

    @Override
    void createMessage(Person person, String message, String title) {
        this.person = person;
        this.message = message;
        this.subject = title;
    }
}
