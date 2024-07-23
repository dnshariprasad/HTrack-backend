package com.hari.htrack;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<Tag>>> getTags() {
        return ResponseUtil.success(tagRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Tag>> createTag(@RequestBody Tag tag) {
        try {
            if (tagRepository.findByName(tag.getName()).isEmpty()) {
                tagRepository.save(tag);
                return ResponseUtil.success(tag);
            } else {
                return ResponseUtil.error(HttpStatus.BAD_REQUEST, "Tag with name '" + tag.getName() + "' already exists.");
            }
        } catch (Exception e) {
            return ResponseUtil.error(HttpStatus.BAD_REQUEST, "Tag with name '" + tag.getName() + "' already exists.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Tag>> deleteTag(@PathVariable Long id) {
        Optional<Tag> productOptional = tagRepository.findById(id);
        if (productOptional.isPresent()) {
            tagRepository.deleteById(id);
            return ResponseUtil.success(productOptional.get());
        } else {
            return ResponseUtil.error(HttpStatus.NOT_FOUND, "Tag not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Tag>> updateTag(@PathVariable Long id, @RequestBody Tag updateTag) {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        if (tagOptional.isPresent()) {
            Tag tag = tagOptional.get();
            tag.setName(updateTag.getName());
            Tag updatedProduct = tagRepository.save(tag);
            return ResponseUtil.success(updatedProduct);
        } else {
            return ResponseUtil.error(HttpStatus.NOT_FOUND, "Tag not found");
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Void>> deleteAll() {
        try {
            tagRepository.deleteAll();
        } catch (Exception e) {
            return ResponseUtil.error(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        return ResponseUtil.success();
    }
}
