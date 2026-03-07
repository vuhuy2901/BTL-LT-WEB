package com.example.WebBanHang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebBanHang.model.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {

    List<ProductReview> findAllByProductId(Integer productId);
    
}
