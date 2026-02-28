package com.example.WebBanHang.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_images") 
@Data 
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer id; 
    @Column(name = "product_id")
    private Integer productId; 
    @Column(name = "color_id")
    private Integer colorId;  
    @Column(name = "image_url")
    private String imageUrl; 
    @Column(name = "is_primary")
    private Boolean isPrimary ; 
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; 
   
    
}
