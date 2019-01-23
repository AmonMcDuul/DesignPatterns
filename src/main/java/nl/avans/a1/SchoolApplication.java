package nl.avans.a1;

import net.bytebuddy.asm.Advice;
import nl.avans.a1.business.NoteObserver;
import nl.avans.a1.business.library.EmailAdapter;
import nl.avans.a1.business.library.SlackAdapter;
import nl.avans.a1.domain.Deal;
import nl.avans.a1.domain.Note;
import nl.avans.a1.domain.Person;
import nl.avans.a1.domain.User;
import nl.avans.a1.repository.DealRepository;
import nl.avans.a1.repository.NoteRepository;
import nl.avans.a1.repository.PersonRepository;
import nl.avans.a1.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Dit is voor de proftaak van groep A1 van de Avans Informatica Deeltijd jaar 3
 *
 * @author bobvdvalk jjenster
 */
@SpringBootApplication
public class SchoolApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(SchoolApplication.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private DealRepository dealRepository;

	@Autowired
	private NoteObserver noteObserver;

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("listeners toevoegen");
		noteObserver.addListener(new EmailAdapter());
		noteObserver.addListener(new SlackAdapter());

		LOG.info("save demo content");
		//NOTE DEMO DATA
		Note note = new Note();
		note.setTitle("eerste notitie");
		note.setDescription("Lorem ipsum blablabalnlabdaskdjaljkdnalksdmaklsdm alkdma lkmaskld m\n" +
				"msdflkmsdklfdsmflksdmdfsmkfmsdlkfmslkfmsdlkmfskdlmfskdmfksmdfksdm\n");
		note.setDate(LocalDateTime.now());

		Note note2 = new Note();
		note2.setTitle("tweede notitie");
		note2.setDescription("Lorem ipsum blablabalnlabdaskdjaljkdnalksdmaklsdm alkdma lkmaskld m\n" +
				"msdflkmsdklfdsmflksdmdfsmkfmsdlkfmslkfmsdlkmfskdlmfskdmfksmdfksdm\n");
		note2.setDate(LocalDateTime.now());

		noteRepository.save(note);
		noteRepository.save(note2);


		//USER DEMO DATA
		User joshua = new User();
		joshua.setName("Joshua Jenster");
		joshua.setUsername("jjenster");
		joshua.setPassword("12345");

		userRepository.save(joshua);

		User bob = new User();
		bob.setName("Bob van der Valk");
		bob.setUsername("bobvdvalk");
		bob.setPassword("12345");

		userRepository.save(bob);

		Deal deal = new Deal();

		Person joshPerson = new Person();
		joshPerson.setName("Joshua");

		dealRepository.save(deal);

		deal.addPerson(joshPerson);

		personRepository.save(joshPerson);

		dealRepository.save(deal);
	}
}
