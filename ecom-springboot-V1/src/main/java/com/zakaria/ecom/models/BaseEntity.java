package com.zakaria.ecom.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {


    @Column(name = "created_at", nullable = false,updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false,updatable = true)
    @UpdateTimestamp
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at", nullable = true)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date deletedAt;
}
