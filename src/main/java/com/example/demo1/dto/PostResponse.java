package com.example.demo1.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostResponse {
    private UUID id;
    private String title;
    private String content;
    private String authorUsername;
    private UUID authorId;
    private Long createdDate;
    private Long modifiedDate;
    private boolean canEdit;
}