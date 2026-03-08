package com.example.WebBanHang.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime; 

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Cart {
    
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "variant_id")
    private Integer variantId; 

    @Column(name = "quantity")
    private Integer quantity; 

    @CreationTimestamp 
    @Column(name = "added_at", updatable = false)
    private LocalDateTime addedAt;

    @UpdateTimestamp 
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; 
}