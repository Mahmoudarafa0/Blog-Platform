package com.mahmoud.Blog_Platform.services.impl;

import com.mahmoud.Blog_Platform.dtos.CreatePostRequest;
import com.mahmoud.Blog_Platform.dtos.UpdatePostRequest;
import com.mahmoud.Blog_Platform.entities.Category;
import com.mahmoud.Blog_Platform.entities.Post;
import com.mahmoud.Blog_Platform.entities.Tag;
import com.mahmoud.Blog_Platform.entities.User;
import com.mahmoud.Blog_Platform.entities.enums.PostStatus;
import com.mahmoud.Blog_Platform.repositories.PostRepository;
import com.mahmoud.Blog_Platform.services.CategoryService;
import com.mahmoud.Blog_Platform.services.PostService;
import com.mahmoud.Blog_Platform.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    private static final int WORDS_PER_MINUTE = 200;

    @Override
    public Post findPostById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post with id " + id + " not found"));
    }

    @Override
    public List<Post> findAllPosts(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null) {
            Category category =  categoryService.findCategoryById(categoryId);
            Tag tag =  tagService.findTagById(tagId);
            return postRepository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED, category, tag);
        }

        if (categoryId != null) {
            Category category =  categoryService.findCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED, category);
        }

        if (tagId != null) {
            Tag tag =  tagService.findTagById(tagId);
            return postRepository.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED, tag);
        }

        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> findDraftPosts(User user) {
        return postRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }

    @Transactional
    @Override
    public Post createPost(User user, CreatePostRequest createPostRequest) {
        Post post = new Post();
        post.setTitle(createPostRequest.title());
        post.setContent(createPostRequest.content());
        post.setStatus(createPostRequest.status());
        post.setAuthor(user);
        post.setReadingTime(calculateReadingTime(createPostRequest.content()));
        Category category = categoryService.findCategoryById(createPostRequest.categoryId());
        post.setCategory(category);
        List<Tag> tags = tagService.findTagByIds(createPostRequest.tagIds());
        post.setTags(new HashSet<>(tags));

        return postRepository.save(post);
    }

    @Transactional
    @Override
    public Post updatePost(UUID id, UpdatePostRequest updatePostRequest) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post with id " + id + " not found"));
        existingPost.setTitle(updatePostRequest.title());
        existingPost.setContent(updatePostRequest.content());
        existingPost.setStatus(updatePostRequest.status());
        existingPost.setReadingTime(calculateReadingTime(updatePostRequest.content()));

        if (!existingPost.getCategory().getId().equals(updatePostRequest.categoryId())) {
            existingPost.setCategory(categoryService.findCategoryById(updatePostRequest.categoryId()));
        }

        Set<UUID> existingTagIds = existingPost.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
        if (!existingTagIds.equals(updatePostRequest.tagIds())) {
            existingPost.setTags(new HashSet<>(tagService.findTagByIds(updatePostRequest.tagIds())));
        }

        return postRepository.save(existingPost);
    }

    @Override
    public void deletePost(UUID id) {
        Post post = findPostById(id);
        postRepository.deleteById(id);
    }

    private Integer calculateReadingTime(String content) {
        if (content == null || content.isEmpty()) {
            return 0;
        }
        int wordCount = content.trim().split("\\s+").length;
        return (int) Math.ceil((double) wordCount / WORDS_PER_MINUTE);
    }
}
