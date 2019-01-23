package nl.avans.a1.controller;

import nl.avans.a1.domain.Note;
import nl.avans.a1.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("note")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping("")
    public ResponseEntity<Iterable<Note>> index() {
        return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping("{id}")
    public ResponseEntity<Note> noteById(@PathVariable Long id) {
        return noteRepository.findById(id).map(note -> new ResponseEntity<>(note, HttpStatus.OK)).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Note not found"));
    }

    @PostMapping(value = "add", consumes = "application/json")
    public Optional<Note> addNewNote(@Valid @RequestBody Note note) {
        noteRepository.save(note);
        return noteRepository.findById(note.getId());
    }
}
