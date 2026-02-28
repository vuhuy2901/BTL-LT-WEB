package com.example.WebBanHang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Color;
import com.example.WebBanHang.repository.ColorRepository;

@Service
public class ColorService {

    @Autowired
    private ColorRepository repo;

    public ResponseEntity<ApiResponse> listColor() {
        try {
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Lấy danh sách màu sắc thành công", repo.findAll())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    public ResponseEntity<ApiResponse> addColor(Color color) {
        try {
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Thêm màu sắc thành công", repo.save(color))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    public ResponseEntity<ApiResponse> updateColor(Integer id, Color color) {
        try {
            if (!repo.existsById(id)) {
                return ResponseEntity.badRequest().body(
                    new ApiResponse<>("ERROR", "Màu sắc không tồn tại", null)
                );
            }
            color.setId(id);
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Cập nhật màu sắc thành công", repo.save(color))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    public ResponseEntity<ApiResponse> deleteColor(Integer id) {
        try {
            if (!repo.existsById(id)) {
                return ResponseEntity.badRequest().body(
                    new ApiResponse<>("ERROR", "Màu sắc không tồn tại", null)
                );
            }
            repo.deleteById(id);
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Xóa màu sắc thành công", null)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }
}
