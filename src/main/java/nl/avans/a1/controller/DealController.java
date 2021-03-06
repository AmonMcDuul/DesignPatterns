package nl.avans.a1.controller;

import nl.avans.a1.domain.*;
import nl.avans.a1.repository.DealRepository;
import nl.avans.a1.repository.NoteRepository;
import nl.avans.a1.repository.PersonRepository;
import nl.avans.a1.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deal")
public class DealController {

    @Autowired
    DealRepository dealRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    NoteRepository noteRepository;

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);


    @GetMapping("")
    public ResponseEntity<Iterable<Deal>> getAllDeals() {
        LOG.info("get all Deals called");
        return new ResponseEntity<>(dealRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable long id) {
        LOG.info("get Deals called for id: "+id);

        return dealRepository.findById(id).map(deal -> {
            return new ResponseEntity<>(deal, HttpStatus.OK);
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Deal not found"));
    }

    @PostMapping("")
    public ResponseEntity<Deal> addDeal(@Valid @RequestBody Deal deal) {
        deal.setId(null);
        return new ResponseEntity<>(dealRepository.save(deal), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deal> editDeal(@PathVariable long id, @Valid @RequestBody Deal deal) {
        deal.setId(id);
        return new ResponseEntity<>(dealRepository.save(deal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeal(@PathVariable long id) {
        return dealRepository.findById(id).map(deal -> {
            dealRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Deal not found"));
    }

    /*
        ALL THE PERSON ENDPOINTS BELOW
     */

    @GetMapping("/{id}/persons")
    public ResponseEntity<List<Person>> getPersonsOfDeal(@PathVariable long id) {
        return dealRepository.findById(id).map(deal -> new ResponseEntity<>(deal.getPersons(), HttpStatus.OK)).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Deal not found"));
    }

    @PostMapping("/{dealId}/persons/{personId}")
    public ResponseEntity<List<Person>> addPersonToDeal(@PathVariable("dealId") long dealId, @PathVariable("personId") long personId ) {
        return dealRepository.findById(dealId).map(deal -> {
            return personRepository.findById(personId).map(person -> {
                if(deal.getPersons().contains(person))
                    return new ResponseEntity<>(deal.getPersons(), HttpStatus.CREATED);
                deal.addPerson(person);
                return new ResponseEntity<>(deal.getPersons(), HttpStatus.CREATED);
            }).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Person not found"));
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Deal not found"));
    }

    @DeleteMapping("/{dealId}/persons/{personId}")
    public ResponseEntity<?> deletePersonFromDeal(@PathVariable("dealId") long dealId, @PathVariable("personId") long personId ) {
        return dealRepository.findById(dealId).map(deal -> {
            return personRepository.findById(personId).map(person -> {
                    deal.getPersons().remove(person);
                    dealRepository.save(deal);
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Person not found"));
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Deal not found"));
    }

    /*
        ALL THE NOTE ENDPOINTS BELOW
     */

    @GetMapping("/{id}/notes")
    public ResponseEntity<List<Note>> getNotesOfDeal(@PathVariable long id) {
        return dealRepository.findById(id).map(deal -> new ResponseEntity<>(deal.getNotes(), HttpStatus.OK)).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Deal not found"));
    }

    @PostMapping("/{dealId}/notes/{noteId}")
    public ResponseEntity<List<Note>> addNoteToDeal(@PathVariable("dealId") long dealId, @PathVariable("noteId") long noteId ) {
        return dealRepository.findById(dealId).map(deal -> {
            return noteRepository.findById(noteId).map(note -> {
                if(deal.getNotes().contains(note))
                    return new ResponseEntity<>(deal.getNotes(), HttpStatus.CREATED);
                deal.addNote(note);
                return new ResponseEntity<>(deal.getNotes(), HttpStatus.CREATED);
            }).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Note not found"));
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Deal not found"));
    }

    @DeleteMapping("/{dealId}/notes/{noteId}")
    public ResponseEntity<?> deleteNoteFromDeal(@PathVariable("dealId") long dealId, @PathVariable("noteId") long noteId ) {
        return dealRepository.findById(dealId).map(deal -> {
            return noteRepository.findById(noteId).map(note -> {
                deal.getNotes().remove(note);
                dealRepository.save(deal);
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Person not found"));
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Deal not found"));
    }

}
