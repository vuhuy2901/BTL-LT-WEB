package com.example.WebBanHang.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Column(name = "added_at")
    private LocalDateTime addedAt ;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; 


     
}
