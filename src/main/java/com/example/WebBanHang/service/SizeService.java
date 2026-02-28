package com.example.WebBanHang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Size;
import com.example.WebBanHang.repository.SizeRepository;

@Service
public class SizeService {

    @Autowired
    private SizeRepository repo;

    public ResponseEntity<ApiResponse> listSize() {
        try {
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Lấy danh sách kích cỡ thành công", repo.findAllByOrderByOrderAsc())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    public ResponseEntity<ApiResponse> addSize(Size size) {
        try {
            if (size.getOrder() == null) {
                int maxOrder = repo.findAllByOrderByOrderAsc()
                    .stream()
                    .mapToInt(s -> s.getOrder() != null ? s.getOrder() : 0)
                    .max().orElse(0);
                size.setOrder(maxOrder + 1);
            } else {
                repo.findAllByOrderByOrderAsc().stream()
                    .filter(s -> s.getOrder() != null && s.getOrder() >= size.getOrder())
                    .forEach(s -> {
                        s.setOrder(s.getOrder() + 1);
                        repo.save(s);
                    });
            }
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Thêm kích cỡ thành công", repo.save(size))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    public ResponseEntity<ApiResponse> updateSize(Integer id, Size size) {
        try {
            if (!repo.existsById(id)) {
                return ResponseEntity.badRequest().body(
                    new ApiResponse<>("ERROR", "Kích cỡ không tồn tại", null)
                );
            }
            size.setId(id);
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Cập nhật kích cỡ thành công", repo.save(size))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    public ResponseEntity<ApiResponse> deleteSize(Integer id) {
        try {
            if (!repo.existsById(id)) {
                return ResponseEntity.badRequest().body(
                    new ApiResponse<>("ERROR", "Kích cỡ không tồn tại", null)
                );
            }
            repo.deleteById(id);
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Xóa kích cỡ thành công", null)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    public ResponseEntity<ApiResponse> reorderSize(List<Integer> ids) {
        try {
            for (int i = 0; i < ids.size(); i++) {
                Integer sizeId = ids.get(i);
                repo.findById(sizeId).ifPresent(size -> {
                    size.setOrder(ids.indexOf(sizeId) + 1);
                    repo.save(size);
                });
            }
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Sắp xếp thứ tự kích cỡ thành công",
                    repo.findAllByOrderByOrderAsc())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }
}
