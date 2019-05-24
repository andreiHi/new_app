package com.app.controller;

import com.app.entities.Topic;
import com.app.repository.TopicRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
@RestController
public class TopicController {

    private final TopicRepository topicRepository;

    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping("/topics")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Topic>> searchTopic(@RequestParam(value = "searchString", required = false) String searchString) {
        // return topicRepository.findByDescriptionLikeIgnoreCase("%spring%");
        return ResponseEntity.ok(topicRepository.findByAttributeContainsText("description", searchString));
    }

    @GetMapping(value = "/topics/{id}")
    public ResponseEntity<Topic> searchTopics(@PathVariable Long id) {
        return this.topicRepository.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Topic with id = %s not found", id)));
    }

    @PostMapping("/topics")
    public ResponseEntity createTopic(@RequestBody Topic topic) {
        final Topic newTopic = this.topicRepository.save(topic);
        return new ResponseEntity<>(newTopic, HttpStatus.OK);
    }

    @PatchMapping("/topic/{id}")
    public ResponseEntity patchTopic(@PathVariable Long id, @RequestBody Topic topic) {
        Topic updatedTopic = topicRepository.updateWith(topic, id);
        if (null == updatedTopic) {
            return new ResponseEntity<>("No topic found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTopic, HttpStatus.OK);
    }

    @PutMapping("/topic/{id}")
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
        final Topic topicDb = topicRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Topic with id = %s not found", id)));
        BeanUtils.copyProperties(topic, topicDb, "id");
        topicRepository.save(topicDb);
        return ResponseEntity.ok(topicDb);
    }

    @DeleteMapping("/topic/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        return this.topicRepository.findById(id).map(c -> {
            topicRepository.delete(c);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Topic with id = %s not found", id)));
    }
}
