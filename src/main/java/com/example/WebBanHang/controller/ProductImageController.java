package com.example.WebBanHang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.ProductImage;
import com.example.WebBanHang.service.ProductImageService;

@Controller
@RequestMapping("product-image")
public class ProductImageController {
    
    @Autowired
    private ProductImageService productImageService;
    
    @PostMapping("add")
    @ResponseBody
    public ResponseEntity<ApiResponse<List<ProductImage>>> addProductImage(@RequestBody List<ProductImage> productImage) {
        return productImageService.addProductImageList(productImage);
    }

}
