package com.hari.htrack;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class NotesController {
    @RequestMapping("/notes")
    public List<Note> notesList() {
        return Arrays.asList(
                new Note(1, "a", "aaa"),
                new Note(1, "b", "bbb")
        );
    }
}
