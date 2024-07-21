package com.hari.htrack;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ItemService {

    private final List<Note> items;

    public ItemService() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/data.json")) {
            items = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        }
    }

    public List<Note> getAllItems() {
        return items;
    }
}
