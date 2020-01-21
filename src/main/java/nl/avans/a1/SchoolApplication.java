package nl.avans.a1;

import nl.avans.a1.business.NoteObserver;
import nl.avans.a1.business.library.EmailAdapter;
import nl.avans.a1.business.library.SlackAdapter;
import nl.avans.a1.domain.Deal;
import nl.avans.a1.domain.Note;
import nl.avans.a1.domain.NoteType;
import nl.avans.a1.domain.Person;
import nl.avans.a1.prototype.PrototypeConfiguration;
import nl.avans.a1.repository.DealRepository;
import nl.avans.a1.repository.NoteRepository;
import nl.avans.a1.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

/**
 * Dit is voor de proftaak van groep A1 van de Avans Informatica Deeltijd jaar 3
 *
 * @author bobvdvalk jjenster
 */
@SpringBootApplication
public class SchoolApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(SchoolApplication.class);

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private DealRepository dealRepository;

	@Autowired
	private NoteObserver noteObserver;

	@Autowired
	private PrototypeConfiguration protoTypeConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("listeners toevoegen");
		noteObserver.addPrivateListener(new EmailAdapter());
		noteObserver.addPrivateListener(new SlackAdapter());
		noteObserver.addPPublicListener(new EmailAdapter());
		noteObserver.addPPublicListener(new SlackAdapter());

		LOG.info("save demo content");
		//NOTE DEMO DATA
		Note note = new Note();
		note.setTitle("eerste notitie");
		note.setDescription("Lorem ipsum blablabalnlabdaskdjaljkdnalksdmaklsdm alkdma lkmaskld m\n" +
				"msdflkmsdklfdsmflksdmdfsmkfmsdlkfmslkfmsdlkmfskdlmfskdmfksmdfksdm\n");
		note.setDate(LocalDateTime.now());

		LOG.info("save demo content");

		Note note1 = Note.builder()
				.title("Added private note with builder pattern")
				.description("Lorem ipsum blablabalnlabdaskdjaljkdnalksdmaklsdm alkdma lkmaskld m\\n\" +\n" +
						"\t\t\t\t\"msdflkmsdklfdsmflksdmdfsmkfmsdlkfmslkfmsdlkmfskdlmfskdmfksmdfksdm\\n")
				.date(LocalDateTime.now())
				.type(NoteType.NO_FILE)
				.build();

		Note note2 = new Note();
		note2.setTitle("tweede notitie");
		note2.setDescription("Lorem ipsum blablabalnlabdaskdjaljkdnalksdmaklsdm alkdma lkmaskld m\n" +
				"msdflkmsdklfdsmflksdmdfsmkfmsdlkfmslkfmsdlkmfskdlmfskdmfksmdfksdm\n");
		note2.setDate(LocalDateTime.now());

		Note note3 = protoTypeConfiguration.NoteNoFile();
		note3.setTitle("Added prototype note");
		note3.setDescription("Added prototype");
		note3.setDate(LocalDateTime.now());

		noteRepository.save(note);
		noteRepository.save(note1);
		noteRepository.save(note2);
		noteRepository.save(note3);

		Deal deal = new Deal();

		Person joshPerson = new Person();
		joshPerson.setName("Joshua");

		dealRepository.save(deal);

		deal.addPerson(joshPerson);

		personRepository.save(joshPerson);

		dealRepository.save(deal);
	}
}
