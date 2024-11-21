package com.example.demo1.generic;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class GenericAuditingEntity<T> {

    @CreatedBy
    @Column(name = "created_by", length = 50, updatable = false)
    protected UUID createdBy;

    @CreatedDate
    @Column(name = "created_date")
    protected Long createdDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    protected Long modifiedDate;

    @LastModifiedBy
    @Column(name = "modified_by", length = 50)
    protected UUID modifiedBy;


}
