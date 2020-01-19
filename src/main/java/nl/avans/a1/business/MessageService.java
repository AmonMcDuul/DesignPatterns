package nl.avans.a1.business;

import nl.avans.a1.domain.Person;

public abstract class MessageService {

    public boolean send(Person person, String message, String title) {
        createMessage(person, message, title);
        Logger logger = Logger.getInstance();
        logger.noteLogger(person.toString(), message, title);
        return sendMessage();
    }

    abstract boolean sendMessage();

    abstract void createMessage(Person person, String message, String title);
}
