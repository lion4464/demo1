package com.example.demo1.entity;

import com.example.demo1.generic.GenericAuditingEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "posts")
@NoArgsConstructor
public class PostEntity extends GenericAuditingEntity<UUID> {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @Column(name = "author_id", insertable = false,updatable = false)
    private UUID autherId;

    @Builder
    public PostEntity(String title, String content, UserEntity author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}