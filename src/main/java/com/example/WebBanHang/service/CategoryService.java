package com.example.WebBanHang.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Category;
import com.example.WebBanHang.repository.CategoryRepository; 
@Service 
public class CategoryService {
    @Autowired
    private CategoryRepository repo;  
    public ResponseEntity<ApiResponse>  
    listCategory() {

       try {
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Lấy danh sách danh mục thành công", repo.findAll())
            )   ; 
       } catch (Exception e) {
        return ResponseEntity.badRequest().body(
            new ApiResponse<>("ERROR", "Lỗi Server" , null ) 
         );  
       }
    }
    public ResponseEntity<ApiResponse> addCategory(Category category) {
        try {
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Thêm danh mục thành công", repo.save(category))
            )   ; 
       } catch (Exception e) {
        return ResponseEntity.badRequest().body(
            new ApiResponse<>("ERROR", "Lỗi Server" , null ) 
         );  
       }
    }  
     
}
