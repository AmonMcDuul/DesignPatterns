package nl.avans.a1.repository;

import nl.avans.a1.domain.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
}
