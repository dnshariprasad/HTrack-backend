package com.hari.htrack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private TagRepository tagRepository;


    @GetMapping
    public ResponseEntity<BaseResponse<List<Note>>> getNotes() {
        return ResponseUtil.success(noteRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Note>> createNotes(@RequestBody Note noteRequest) {
        Note note = new Note();
        note.setTitle(noteRequest.getTitle());
        note.setInfo(noteRequest.getInfo());
        note.setType(noteRequest.getType());
        note.setLink(noteRequest.getLink());
        Set<Tag> tags = new HashSet<>();
        for (Tag tagName : noteRequest.getTags()) {
            Tag tag = tagRepository.findByName(tagName.getName()).orElseGet(() -> {
                Tag newTag = new Tag();
                newTag.setName(tagName.getName());
                return tagRepository.save(newTag);
            });
            tags.add(tag);
        }
        note.setTags(tags);
        note.setTags(tags);
        noteRepository.save(note);
        return ResponseUtil.success(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Note>> deleteNote(@PathVariable Long id) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if (noteOptional.isPresent()) {
            noteRepository.deleteById(id);
            return ResponseUtil.success(noteOptional.get());
        } else {
            return ResponseUtil.error(HttpStatus.NOT_FOUND, "Note not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Note>> updateNote(@PathVariable Long id, @RequestBody Note updateNote) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if (noteOptional.isPresent()) {
            Note note = noteOptional.get();
            note.setTitle(updateNote.getTitle());
            note.setInfo(updateNote.getInfo());
            note.setTags(updateNote.getTags());
            note.setType(updateNote.getType());
            note.setLink(updateNote.getLink());
            Note updatedNote = noteRepository.save(note);
            return ResponseUtil.success(updatedNote);
        } else {
            return ResponseUtil.error(HttpStatus.NOT_FOUND, "Note not found");
        }
    }
}
