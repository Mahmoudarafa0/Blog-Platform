package com.mahmoud.Blog_Platform.services;

import com.mahmoud.Blog_Platform.dtos.CreatePostRequest;
import com.mahmoud.Blog_Platform.dtos.UpdatePostRequest;
import com.mahmoud.Blog_Platform.entities.Post;
import com.mahmoud.Blog_Platform.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post findPostById(UUID id);
    List<Post> findAllPosts(UUID categoryId, UUID tagId);
    List<Post> findDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
    void deletePost(UUID id);
}
