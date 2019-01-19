package nl.avans.a1.business;

import nl.avans.a1.domain.Person;

public abstract class MessageService {

    public void send(Person person, String message, String title) {
        createMessage(person, message, title);
        sendMessage();
    }

    abstract void sendMessage();

    abstract void createMessage(Person person, String message, String title);
}
