package nl.avans.a1.repository;

import nl.avans.a1.domain.Note;
import nl.avans.a1.domain.NoteType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findNoteByType(NoteType note_type);
}
