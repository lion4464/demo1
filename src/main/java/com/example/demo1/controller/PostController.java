package com.example.demo1.controller;

import com.example.demo1.convertor.PostMapper;
import com.example.demo1.dto.CreatePostRequest;
import com.example.demo1.dto.PostResponse;
import com.example.demo1.dto.UpdatePostRequest;
import com.example.demo1.entity.PostEntity;
import com.example.demo1.exceptions.AccessDeniedException;
import com.example.demo1.exceptions.DataNotFoundException;
import com.example.demo1.generic.ApiResponse;
import com.example.demo1.service.PostService;
import com.example.demo1.utils.SecurityContextHolderUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;
    private final SecurityContextHolderUtils securityContextHolderUtils;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_AUTHER')")
    public ResponseEntity<ApiResponse<PostResponse>> createPost(
            @Valid @RequestBody CreatePostRequest request) {
        PostEntity post = postService.createPost(request);
        return ApiResponse.ok(
                postMapper.toResponse(post,securityContextHolderUtils.getCurrentUser().getUserId()),
                "Post created successfully"
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<PostResponse>>> getAllPosts(
            @PageableDefault(size = 10, sort = "createdDate") Pageable pageable) {
        return ApiResponse.ok(postMapper.toDtoPage(postService.getAllPosts(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable UUID id) throws DataNotFoundException {
        return ApiResponse.ok(postMapper.toResponse(postService.getPost(id),securityContextHolderUtils.getCurrentUser().getUserId()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_AUTHER')")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequest request) throws DataNotFoundException, AccessDeniedException {
        return ApiResponse.ok(
                postMapper.toResponse(postService.updatePost(id, request),securityContextHolderUtils.getCurrentUser().getUserId()),
                "Post updated successfully"
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_AUTHER')")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable UUID id) throws DataNotFoundException, AccessDeniedException {
        postService.deletePost(id);
        return ApiResponse.ok(null, "Post deleted successfully");
    }
}