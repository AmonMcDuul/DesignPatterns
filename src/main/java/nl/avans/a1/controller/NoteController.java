package nl.avans.a1.controller;

import nl.avans.a1.domain.Note;
import nl.avans.a1.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
