package nl.avans.a1.prototype;

import nl.avans.a1.domain.Note;
import nl.avans.a1.domain.NoteType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PrototypeConfiguration {
    @Bean
    @Scope("prototype")
    public Note NoteNoFile() {
        return Note.builder()
                .type(NoteType.NO_FILE)
                .build();
    }
    @Bean
    @Scope("prototype")
    public Note NoteWithFile() {
        return Note.builder()
                .type(NoteType.FILE)
                .build();
    }
}
