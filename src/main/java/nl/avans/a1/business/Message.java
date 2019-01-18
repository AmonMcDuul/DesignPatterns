package nl.avans.a1.business;

import nl.avans.a1.domain.Person;

public interface Message {
    void create(String message);

    void send(Person person);
}
