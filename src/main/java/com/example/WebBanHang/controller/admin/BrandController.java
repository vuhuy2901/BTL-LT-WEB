package com.example.WebBanHang.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Brand;
import com.example.WebBanHang.service.admin.BrandService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("admin/brand")
public class BrandController {
   @Autowired
   private BrandService brandService ; 
   @PostMapping("add")
    
   public ResponseEntity<ApiResponse>  addBrand (@RequestBody Brand  brand ) {
       try { 
        return brandService.addBrand(brand) ;
       } catch (Exception e) {
        return ResponseEntity.ok().body(
            new ApiResponse<>("ERROR", "Lỗi Server" , null ) 
         );
        
       }
    }
    
}
