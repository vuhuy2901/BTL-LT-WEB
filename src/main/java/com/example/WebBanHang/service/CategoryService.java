package com.example.WebBanHang.service;
import java.util.List;

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

    public List<Category> getAllCategories() {
        return repo.findAll();
    }
    public ResponseEntity<ApiResponse> addCategory(Category category) {
        try {
            String name = category.getName().trim() ;
            if (name.isEmpty()) {
                return ResponseEntity.badRequest().body(
                    new ApiResponse<>("ERROR", "Tên danh mục không được để trống", null)
                ) ;
            } 
            if (repo.findByName(name) != null) {
                return ResponseEntity.badRequest().body(
                    new ApiResponse<>("ERROR", "Tên danh mục đã tồn tại", null)
                ) ;
            }  
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
