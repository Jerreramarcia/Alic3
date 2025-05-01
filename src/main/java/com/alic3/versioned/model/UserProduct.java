package com.alic3.versioned.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)//for CreatedDate --> Config
public class UserProduct {

    @Id
    @GeneratedValue
    private int id;
    private long userId;
    private long productId;
    private int quantity;
    private String info;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;


    public UserProduct(Long userId, Long productId, Integer quantity, String info) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.info = info;
    }
}
