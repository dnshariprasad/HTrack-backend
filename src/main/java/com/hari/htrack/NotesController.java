package com.hari.htrack;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/notes")
public class NotesController {
    private List<Note> notes;

    private ItemService itemService;

    public NotesController(ItemService itemService) {
        this.itemService = itemService;
        notes = itemService.getAllItems();
    }


    @GetMapping
    public ResponseEntity<BaseResponse<List<Note>>> notesList() {
        return ResponseUtil.success(notes);
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
