package nl.avans.a1.business;

import nl.avans.a1.domain.Person;

public class MessageSlackService extends MessageService {
    @Override
    void sendMessage() {
        System.out.println("slack sent!");
    }

    @Override
    void createMessage(Person person, String message, String title) {
        System.out.println("slack created!");
    }
}
