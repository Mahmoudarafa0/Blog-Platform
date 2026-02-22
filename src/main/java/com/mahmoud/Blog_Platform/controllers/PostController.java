package com.mahmoud.Blog_Platform.controllers;

import com.mahmoud.Blog_Platform.Mappers.PostMapper;
import com.mahmoud.Blog_Platform.dtos.*;
import com.mahmoud.Blog_Platform.entities.Post;
import com.mahmoud.Blog_Platform.entities.User;
import com.mahmoud.Blog_Platform.services.PostService;
import com.mahmoud.Blog_Platform.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Posts", description = "Operations related to posts")
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @Operation(summary = "Get all published posts, you can filter by category and tag")
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false)UUID categoryId,
            @RequestParam(required = false)UUID tagId
            ) {
        List<Post> posts = postService.findAllPosts(categoryId, tagId);
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @Operation(summary = "Get all draft posts")
    @GetMapping("/drafts")
    public ResponseEntity<List<PostDto>> getAllDraftPosts(@RequestAttribute UUID userId) {
        User user = userService.findUserById(userId);
        List<Post> posts = postService.findDraftPosts(user);
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @Operation(summary = "Create new post")
    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody CreatePostRequest createPostRequest,
            @RequestAttribute UUID userId
            ) {
        User user = userService.findUserById(userId);
        Post post = postService.createPost(user, createPostRequest);
        PostDto postDto = postMapper.toDto(post);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a specific post")
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable UUID postId) {
        Post post = postService.findPostById(postId);
        PostDto postDto = postMapper.toDto(post);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @Operation(summary = "Update a specific post")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable UUID postId, @Valid @RequestBody UpdatePostRequest updatePostRequest) {
        Post updatedPost = postService.updatePost(postId, updatePostRequest);
        PostDto postDto = postMapper.toDto(updatedPost);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @Operation(summary = "Delete a specific post")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
