package com.hari.htrack;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/notes")
public class NotesController {
    private List<Note> notes = new ArrayList<>();

    public NotesController() {
        notes.add(new Note(1, "orci, adipiscing non, luctus sit amet, faucibus ut, nulla.", "feugiat. Sed nec metus facilisis lorem", "http://naver.com", "nec orci. Donec nibh.", "enim. Nunc ut erat. Sed"));
        notes.add(new Note(2, "tempor arcu. Vestibulum ut eros", "dui nec urna suscipit", "http://walmart.com", "elit, pharetra ut, pharetra sed, hendrerit a, arcu. Sed", "luctus lobortis. Class aptent taciti sociosqu ad litora torquent"));
        notes.add(new Note(3, "ligula. Nullam feugiat", "sapien. Nunc pulvinar arcu et pede. Nunc sed orci", "http://google.com", "lectus quis", "lorem lorem, luctus ut, pellentesque eget, dictum"));
        notes.add(new Note(4, "vulputate, posuere vulputate, lacus. Cras", "ipsum nunc id enim. Curabitur massa.", "http://youtube.com", "faucibus lectus, a sollicitudin orci sem", "libero lacus, varius"));
        notes.add(new Note(5, "urna, nec luctus felis purus ac tellus. Suspendisse sed", "commodo hendrerit. Donec porttitor", "http://cnn.com", "consectetuer mauris id", "eu neque pellentesque massa lobortis"));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<Note>>> notesList() {
        return ResponseUtil.success(notes.stream().toList());
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Note>> createItem(@RequestBody Note note) {
        notes.add(note);
        return ResponseUtil.success(note);
    }

    @PutMapping
    public ResponseEntity<BaseResponse<Note>> updateNote(@RequestBody Note note) {
        Note n = null;
        for (Note cn : notes) {
            if (note.getId() == cn.getId()) {
                n = cn;
                break;
            }
        }
        if (null != n) {
            n.setId(note.getId());
            n.setInfo(note.getInfo());
            n.setLink(note.getLink());
            n.setTags(note.getTags());
            n.setTitle(note.getTitle());
            n.setType(note.getType());
            return ResponseUtil.success(n);
        } else {
            return ResponseUtil.error(HttpStatus.NOT_FOUND, "Note not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Note>> deleteNote(@PathVariable int id) {
        Note n = null;
        for (Note cn : notes) {
            if (id == cn.getId()) {
                n = cn;
                break;
            }
        }
        if (null != n) {
            notes.remove(n);
            return ResponseUtil.success(n);
        } else
            return ResponseUtil.error(HttpStatus.NOT_FOUND, "Note not found");
    }
}
