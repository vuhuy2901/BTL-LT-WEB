package com.example.WebBanHang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.ProductVariant;
import com.example.WebBanHang.repository.ProductVariantRepository;

public class ProductVariantService {
        @Autowired
        private ProductVariantRepository productVariantRepository; 
        public ResponseEntity<ApiResponse<List<ProductVariant>> >  getAllProductVariants() {
            return ResponseEntity.ok(new ApiResponse<>( "SUCCESS", "Lấy danh sách variant sản phẩm thành công ", productVariantRepository.findAll())); 
        }  
        
}
