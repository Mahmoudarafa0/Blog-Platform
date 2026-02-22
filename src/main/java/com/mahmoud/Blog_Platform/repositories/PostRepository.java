package com.mahmoud.Blog_Platform.repositories;

import com.mahmoud.Blog_Platform.entities.Category;
import com.mahmoud.Blog_Platform.entities.Post;
import com.mahmoud.Blog_Platform.entities.Tag;
import com.mahmoud.Blog_Platform.entities.User;
import com.mahmoud.Blog_Platform.entities.enums.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);
    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);
    List<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag);
    List<Post> findAllByStatus(PostStatus status);
    List<Post> findAllByAuthorAndStatus(User user, PostStatus status);
}
