package com.mahmoud.Blog_Platform.controllers;

import com.mahmoud.Blog_Platform.Mappers.TagMapper;
import com.mahmoud.Blog_Platform.dtos.CreateTagsRequest;
import com.mahmoud.Blog_Platform.dtos.TagDto;
import com.mahmoud.Blog_Platform.entities.Tag;
import com.mahmoud.Blog_Platform.services.TagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag", description = "Operations related to tags")
@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @Operation(summary = "Get all tags")
    @GetMapping
    public ResponseEntity<List<TagDto>> findAllTags() {
        List<TagDto> tags = tagService.findAllTags()
                .stream().map(tagMapper::toTagResponse).toList();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @Operation(summary = "Create a new tag")
    @PostMapping
    public ResponseEntity<List<TagDto>> createTags(@RequestBody CreateTagsRequest createTagsRequest) {
        List<Tag> savedTags = tagService.createTags(createTagsRequest.names());
        List<TagDto> tags = savedTags.stream().map(tagMapper::toTagResponse).toList();
        return new ResponseEntity<>(tags, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a specific tag")
    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID tagId) {
        tagService.deleteTag(tagId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
