package com.example.demo1.service;

import com.example.demo1.dto.CreatePostRequest;
import com.example.demo1.dto.PostResponse;
import com.example.demo1.dto.UpdatePostRequest;
import com.example.demo1.entity.PostEntity;
import com.example.demo1.exceptions.AccessDeniedException;
import com.example.demo1.exceptions.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PostService {
    PostEntity createPost(CreatePostRequest request);
    Page<PostEntity> getAllPosts(Pageable pageable);
    PostEntity getPost(UUID postId) throws DataNotFoundException;
    PostEntity updatePost(UUID postId, UpdatePostRequest request) throws DataNotFoundException, AccessDeniedException;
    void deletePost(UUID postId) throws DataNotFoundException, AccessDeniedException;

}
