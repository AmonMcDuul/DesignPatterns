package nl.avans.a1.controller;

import nl.avans.a1.domain.Note;
import nl.avans.a1.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("note")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping("")
    public Iterable<Note> index() {
        return noteRepository.findAll();
    }

    @RequestMapping("{id}")
    public Optional<Note> noteById(@PathVariable Long id) {
        return noteRepository.findById(id);
    }

    @PostMapping(value = "add", consumes = "application/json")
    public Optional<Note> addNewNote(@RequestBody Note note) {
        noteRepository.save(note);
        return noteRepository.findById(note.getId());
    }
}
