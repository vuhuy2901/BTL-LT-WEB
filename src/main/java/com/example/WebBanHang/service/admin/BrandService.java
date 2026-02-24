package com.example.WebBanHang.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Brand;
import com.example.WebBanHang.repository.BrandRepository;

@Service
public class BrandService {
    @Autowired
    private BrandRepository repo;

    public ResponseEntity<ApiResponse> addBrand(Brand brand) {
        if (brand.getName() == null) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Tên thương hiệu không được để trống", null)
            ) ;
        }
        repo.save(brand) ; 
        return ResponseEntity.ok().body(
            new ApiResponse<>("SUCCESS", "Thêm thương hiệu thành công", brand)
        )   ; 
         
    }
}
