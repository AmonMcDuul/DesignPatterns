package nl.avans.a1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.avans.a1.business.NoteObserver;
import nl.avans.a1.domain.Note;
import nl.avans.a1.domain.NoteType;
import nl.avans.a1.helpers.FileHelper;
import nl.avans.a1.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("note")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteObserver noteObserver;

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("")
    public ResponseEntity<Iterable<Note>> index() {
        LOG.info("get all Notes called");
        return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Note> noteById(@PathVariable Long id) {
        LOG.info("get Note by id called id "+id);
        return noteRepository.findById(id).map(note -> new ResponseEntity<>(note, HttpStatus.OK)).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Note not found"));
    }

    @PostMapping("")
    public ResponseEntity<Note> addNewNote(@Valid @RequestBody Note note) {
        LOG.info("add note called");
        note.setId(null);
        Note savedNote = noteRepository.save(note);
        noteObserver.notifyListeners("info@avans.nl", savedNote.getDescription());
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @PostMapping("/file")
    public ResponseEntity<Note> addNewNoteWithFile(@RequestParam("model") String model, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        LOG.info("add note called with file");
        ObjectMapper mapper = new ObjectMapper();
        Note note = mapper.readValue(model, Note.class);
        note.setId(null);
        File converted_file = FileHelper.convert(file);
        note.setFile(converted_file);
        Note savedNote = noteRepository.save(note);
        noteObserver.notifyListeners("info@avans.nl", savedNote.getDescription());
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/file")
    public ResponseEntity<?> addFileToNote(@PathVariable long id,
                                              @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return noteRepository.findById(id).map(note -> {
            File converted_file = null;
            try {
                converted_file = FileHelper.convert(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            note.setFile(converted_file);
            Note savedNote = noteRepository.save(note);
            noteObserver.notifyListeners("info@avans.nl", savedNote.getDescription());
            return new ResponseEntity<>(savedNote, HttpStatus.CREATED);

        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Note not found"));
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Note> editNote(@PathVariable long id, @RequestBody Note note) {
        LOG.info("edit Person called on id: "+id);
        return new ResponseEntity<>(noteRepository.save(note), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable long id) {
        return noteRepository.findById(id).map(note -> {
            noteRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Note not found"));
    }

}
