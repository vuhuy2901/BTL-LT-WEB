package com.example.WebBanHang.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wishlists") 
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "product_id")
    private Integer productId; 
    @Column(name = "add_at")
    private LocalDateTime addAt;
     public WishList(Integer userId, Integer productId, LocalDateTime createdAt) {
        this.userId = userId;
        this.productId = productId;
        this.addAt = createdAt;
    }
}
