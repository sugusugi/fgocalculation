package com.example.fgocalculation.entity;

import java.util.Date;

import lombok.Data;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import javax.persistence.Column;

@MappedSuperclass
@Data
public class AbstractEntity {
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
    
    @PrePersist
    public void onPrePersist() {
        Date date = new Date();
        setCreatedAt(date);
        setUpdatedAt(date);
    }
    
    @PreUpdate
    public void onPreUpdate() {
        setUpdatedAt(new Date());
    }
}
