package nl.avans.a1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.a1.business.MessageService;
import nl.avans.a1.business.MessageServiceFactory;
import nl.avans.a1.business.MessageType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ManyToOne
    private Deal deal;

    public void sendMessage(String message, String title, String channel) {
        MessageServiceFactory.getMessageImplementation(channel).send(this, message, title);
    }

}
