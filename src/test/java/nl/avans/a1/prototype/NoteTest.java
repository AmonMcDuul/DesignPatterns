package nl.avans.a1.prototype;

import nl.avans.a1.domain.Note;
import nl.avans.a1.domain.NoteType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PrototypeConfiguration.class)
public class NoteTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testClone() {
        LocalDateTime now = LocalDateTime.now();

        Note note1 = Note.builder()
                .id(1L)
                .title("test")
                .description("test")
                .date(now)
                .type(NoteType.NO_FILE)
                .build();

        Note note2 = new Note();

        BeanUtils.copyProperties(note1, note2);

        assertEquals(note1, note2);
    }

    @Test
    public void testPrototypeScope() {
        Note note1 = applicationContext.getBean("NoteNoFile", Note.class);
        Note note2 = applicationContext.getBean("NoteNoFile", Note.class);

        assertEquals(note1, note2);
        assertNotSame(note1, note2);
    }
}
