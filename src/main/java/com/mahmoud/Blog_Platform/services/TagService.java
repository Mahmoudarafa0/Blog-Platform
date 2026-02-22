package com.mahmoud.Blog_Platform.services;

import com.mahmoud.Blog_Platform.entities.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {
    List<Tag> findAllTags();
    List<Tag> createTags(Set<String> tagNames);
    void deleteTag(UUID tagId);
    Tag findTagById(UUID tagId);
    List<Tag> findTagByIds(Set<UUID> tagIds);
}
