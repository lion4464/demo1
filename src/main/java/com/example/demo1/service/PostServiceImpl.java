package com.example.demo1.service;


import com.example.demo1.convertor.PostMapper;
import com.example.demo1.dto.CreatePostRequest;
import com.example.demo1.dto.PostResponse;
import com.example.demo1.dto.UpdatePostRequest;
import com.example.demo1.entity.PostEntity;
import com.example.demo1.exceptions.AccessDeniedException;
import com.example.demo1.exceptions.DataNotFoundException;
import com.example.demo1.entity.UserEntity;
import com.example.demo1.repository.PostRepository;
import com.example.demo1.utils.SecurityContextHolderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final SecurityContextHolderUtils securityContextHolderUtils;
    @Transactional
    public PostEntity createPost(CreatePostRequest request) {
        UserEntity author = getCurrentUser();
        PostEntity post = postMapper.toEntity(request, author);
        post = postRepository.save(post);
        return post;
    }

    @Transactional(readOnly = true)
    public Page<PostEntity> getAllPosts(Pageable pageable) {
        UUID currentUserId = getCurrentUserId();
        return postRepository.findAllByOrderByCreatedDateDesc(pageable);
    }

    @Transactional(readOnly = true)
    public PostEntity getPost(UUID postId) throws DataNotFoundException {
          return getPostById(postId);
    }

    @Transactional
    public PostEntity updatePost(UUID postId, UpdatePostRequest request) throws DataNotFoundException, AccessDeniedException {
        PostEntity post = getPostById(postId);
        checkPostAuthor(post);
        postMapper.updateEntity(post, request);
        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(UUID postId) throws DataNotFoundException, AccessDeniedException {
        PostEntity post = getPostById(postId);
        checkPostAuthor(post);
        postRepository.delete(post);
    }

    private PostEntity getPostById(UUID postId) throws DataNotFoundException {
        return postRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("Post not found with id: " + postId));
    }

    private void checkPostAuthor(PostEntity post) throws AccessDeniedException {
        UUID currentUserId = getCurrentUserId();
        if (!post.getAuthor().getId().equals(currentUserId)) {
            throw new AccessDeniedException("You can only modify your own posts");
        }
    }

    private UserEntity getCurrentUser() {
        return securityContextHolderUtils.getCurrentUser().getUser();
    }

    private UUID getCurrentUserId() {
        return getCurrentUser().getId();
    }
}