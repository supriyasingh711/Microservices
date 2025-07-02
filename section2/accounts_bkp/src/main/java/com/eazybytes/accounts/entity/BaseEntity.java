package com.eazybytes.accounts.entity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@ToString
public class BaseEntity {
    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime createdDate;

    @CreatedBy
    @Column( updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false )
    private Long updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy  ;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedAt(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
