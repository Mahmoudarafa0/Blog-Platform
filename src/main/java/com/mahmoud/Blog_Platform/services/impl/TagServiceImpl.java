package com.mahmoud.Blog_Platform.services.impl;

import com.mahmoud.Blog_Platform.entities.Tag;
import com.mahmoud.Blog_Platform.repositories.TagRepository;
import com.mahmoud.Blog_Platform.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAllWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        List<Tag> existingTags = tagRepository.findByNameIn(tagNames);
        Set<String> existingTagNames = existingTags.stream().map(Tag::getName).collect(Collectors.toSet());
        List<Tag> newTags = tagNames.stream().filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build()).toList();

        if (!newTags.isEmpty()) {
            tagRepository.saveAll(newTags);
        }
        return new ArrayList<>(existingTags);
    }

    @Transactional
    @Override
    public void deleteTag(UUID tagId) {
        tagRepository.findById(tagId).ifPresent(tag -> {
            if (!tag.getPosts().isEmpty()) {
                throw new IllegalStateException("Can't delete tag which contains posts");
            }
            tagRepository.deleteById(tagId);
        });
    }

    @Override
    public Tag findTagById(UUID tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new EntityNotFoundException("tag with id " + tagId + " not found"));
    }

    @Override
    public List<Tag> findTagByIds(Set<UUID> tagIds) {
        List<Tag> foundTags = tagRepository.findAllById(tagIds);
        if (foundTags.size() != tagIds.size()) {
            throw new EntityNotFoundException("not all specified tags found");
        }
        return foundTags;
    }
}
