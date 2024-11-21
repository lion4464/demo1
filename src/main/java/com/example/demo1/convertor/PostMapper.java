package com.example.demo1.convertor;

import com.example.demo1.dto.CreatePostRequest;
import com.example.demo1.dto.PostPageable;
import com.example.demo1.dto.PostResponse;
import com.example.demo1.dto.UpdatePostRequest;
import com.example.demo1.entity.PostEntity;
import com.example.demo1.entity.UserEntity;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "authorUsername", source = "author.username")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "canEdit", expression = "java(post.getAuthor().getId().equals(currentUserId))")
    PostResponse toResponse(PostEntity post, @Context UUID currentUserId);

    default Page<PostResponse> toDtoPage(Page<PostEntity> entityPage) {
        List<PostResponse> dtos = toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos, entityPage.getPageable(), entityPage.getTotalElements());
    }

    default PostEntity toEntity(CreatePostRequest request, UserEntity author) {
        return PostEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(author)
                .build();
    }
    @Mapping(target = "authorUsername", source = "author.username")
    @Mapping(target = "authorId", source = "author.id")
    List<PostResponse> toDtoList(List<PostEntity> entityList);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    void updateEntity(@MappingTarget PostEntity post, UpdatePostRequest request);
}