package com.example.WebBanHang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.dto.ProductDto;
import jakarta.validation.Valid;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public ResponseEntity<ApiResponse> listProduct() {
        return productService.listProduct();
    }

    // GET /product/summary → card nhỏ trang chủ (chỉ các field cần thiết)
    @GetMapping("summary")
    public ResponseEntity<ApiResponse> listSummary() {
        return productService.listSummary();
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable Integer id) {
        return productService.getProduct(id);
    }
    @GetMapping("search")
    public ResponseEntity<ApiResponse> searchProduct(@RequestParam String keyword) {
        return productService.searchProduct(keyword);
    }
    @GetMapping("category/{categoryId}")
    public ResponseEntity<ApiResponse> listByCategory(@PathVariable Integer categoryId) {
        return productService.listByCategory(categoryId);
    }
    @GetMapping("brand/{brandId}")
    public ResponseEntity<ApiResponse> listByBrand(@PathVariable Integer brandId) {
        return productService.listByBrand(brandId);
    }
    @PostMapping("add")
    public ResponseEntity<ApiResponse> addProduct(@Valid @RequestBody ProductDto dto) {
        return productService.addProduct(dto);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductDto dto) {
        return productService.updateProduct(id, dto);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }
}
