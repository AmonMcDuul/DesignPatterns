package nl.avans.a1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PersonMessage {

    String message, title, channel;

    public PersonMessage(String message, String title, String channel) {
        this.message = message;
        this.title = title;
        this.channel = channel;
    }
}
