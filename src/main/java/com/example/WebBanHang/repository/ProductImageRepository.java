package com.example.WebBanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebBanHang.model.ProductImage;

public interface  ProductImageRepository extends JpaRepository<ProductImage, Integer>  {
       
    
}
