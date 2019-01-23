package nl.avans.a1.business;

import nl.avans.a1.business.library.SlackAdapter;
import nl.avans.a1.domain.Person;

public class MessageSlackService extends MessageService {


    Person person;
    String message;

    @Override
    boolean sendMessage() {
        if(person.getSlackEmail() != null)
            return SlackAdapter.sendSlackMessage(person.getSlackEmail(), message);
        else
            return false;
    }

    @Override
    void createMessage(Person person, String message, String title) {
        this.person = person;
        this.message = title+"/n"+message;
    }
}
