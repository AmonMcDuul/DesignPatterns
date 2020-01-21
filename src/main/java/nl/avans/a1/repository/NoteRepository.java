package nl.avans.a1.repository;

import nl.avans.a1.domain.Note;
import nl.avans.a1.domain.NoteType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
    List<Note> findNoteByType(NoteType note_type);
}
