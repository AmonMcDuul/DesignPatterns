package nl.avans.a1.business;

import nl.avans.a1.domain.Person;

public class MessageService {

    public void sendMessage(Person person, String message, MessageType platform) {
        MessageFactory messageFactory = new MessageFactory();
        Message sender = messageFactory.getMessageImplementation(platform);
        sender.create(message);
        sender.send(person);
    }
}
