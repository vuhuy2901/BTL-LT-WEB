package com.example.WebBanHang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.ProductImage;
import com.example.WebBanHang.repository.ProductImageRepository;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    public ResponseEntity<ApiResponse<ProductImage>> addProductImage(ProductImage productImage) {
        try {
            ProductImage savedImage = productImageRepository.save(productImage);
            return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Thêm ảnh sản phẩm thành công", savedImage));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("ERROR", "Lỗi khi thêm ảnh sản phẩm: " + e.getMessage(), null));
        }
    }
     public ResponseEntity<ApiResponse<List<ProductImage>>> addProductImageList(List<ProductImage> productImages) {
        try {
            // saveAll() sẽ lưu toàn bộ danh sách xuống database trong 1 lần (tối ưu hiệu suất)
            List<ProductImage> savedImages = productImageRepository.saveAll(productImages);
            return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Thêm danh sách ảnh thành công", savedImages));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("ERROR", "Lỗi khi thêm danh sách ảnh: " + e.getMessage(), null));
        }
    }
}
