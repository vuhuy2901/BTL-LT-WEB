package com.example.WebBanHang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanHang.model.ProductReview;
import com.example.WebBanHang.repository.ProductReviewRepository;

@Service
public class ProductReviewService{ 
    @Autowired
    private ProductReviewRepository productReviewRepository; 
    
    public List<ProductReview> getAllProductReviews(Integer productId ) {
        return productReviewRepository.findAllByProductId(productId);
    } 
}
