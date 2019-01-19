package nl.avans.a1.business;

import nl.avans.a1.domain.Person;

public class MessageEmailService extends MessageService {
    @Override
    void sendMessage() {
        System.out.println("email sent!");
    }

    @Override
    void createMessage(Person person, String message, String title) {
        System.out.println("email created!");
    }
}
