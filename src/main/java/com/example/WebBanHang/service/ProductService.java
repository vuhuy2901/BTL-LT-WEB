package com.example.WebBanHang.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.dto.ProductDto;
import com.example.WebBanHang.dto.ProductSummaryDto;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;


    public ResponseEntity<ApiResponse> listProduct() {
        try {
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Lấy danh sách sản phẩm thành công", repo.findByIsActiveTrue())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

   
    public ResponseEntity<ApiResponse> listSummary() {
        try {
            List<ProductSummaryDto> summaries = repo.findByIsActiveTrue()
                .stream()
                .map(p -> new ProductSummaryDto(
                    p.getId(),
                    p.getName(),
                    p.getThumbnailUrl(),
                    p.getBasePrice(),
                    p.getSalePrice(),
                    p.getGender() != null ? p.getGender().name() : null
                   
                ))
                .collect(Collectors.toList());
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Lấy danh sách sản phẩm thành công", summaries)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    public ResponseEntity<ApiResponse> searchProduct(String keyword) {
        try {
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Kết quả tìm kiếm", repo.findByNameContainingIgnoreCase(keyword))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    // Lấy chi tiết 1 sản phẩm theo id
    public ResponseEntity<ApiResponse<Product>> getProduct(Integer id) {
        try {
            return repo.findById(id)
                .map(p -> ResponseEntity.ok().body(
                    new ApiResponse<>("SUCCESS", "Lấy sản phẩm thành công", p)
                ))
                .orElse(ResponseEntity.badRequest().body(
                    new ApiResponse<>("ERROR", "Sản phẩm không tồn tại", null)
                ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    // Thêm sản phẩm mới từ ProductDto
    public ResponseEntity<ApiResponse> addProduct(ProductDto dto) {
        try {
            Product product = new Product();
            product.setName(dto.getName());
            product.setCategoryId(dto.getCategoryId());
            product.setSportId(dto.getSportId());
            product.setBrandId(dto.getBrandId());
            product.setDescription(dto.getDescription());
            product.setBasePrice(dto.getBasePrice());
            product.setSalePrice(dto.getSalePrice());
            product.setSaleStart(dto.getSaleStart());
            product.setSaleEnd(dto.getSaleEnd());
            product.setThumbnailUrl(dto.getThumbnailUrl());
            product.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
            if (dto.getGender() != null) {
                product.setGender(Product.Gender.valueOf(dto.getGender()));
            }
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Thêm sản phẩm thành công", repo.save(product))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server: " + e.getMessage(), null)
            );
        }
    }

    // Cập nhật sản phẩm
    public ResponseEntity<ApiResponse<Product>> updateProduct(Integer id, ProductDto dto) {
        try {
            return repo.findById(id).map(product -> {
                if (dto.getName() != null)           product.setName(dto.getName());
                if (dto.getCategoryId() != null)     product.setCategoryId(dto.getCategoryId());
                if (dto.getSportId() != null)        product.setSportId(dto.getSportId());
                if (dto.getBrandId() != null)        product.setBrandId(dto.getBrandId());
                if (dto.getDescription() != null)    product.setDescription(dto.getDescription());
                if (dto.getBasePrice() != null)      product.setBasePrice(dto.getBasePrice());
                if (dto.getSalePrice() != null)       product.setSalePrice(dto.getSalePrice());
                if (dto.getSaleStart() != null)      product.setSaleStart(dto.getSaleStart());
                if (dto.getSaleEnd() != null)        product.setSaleEnd(dto.getSaleEnd());
                if (dto.getThumbnailUrl() != null)   product.setThumbnailUrl(dto.getThumbnailUrl());
                if (dto.getIsActive() != null)       product.setIsActive(dto.getIsActive());
                if (dto.getGender() != null)         product.setGender(Product.Gender.valueOf(dto.getGender()));
                return ResponseEntity.ok().body(
                    new ApiResponse<>("SUCCESS", "Cập nhật sản phẩm thành công", repo.save(product))
                );
            }).orElse(ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Sản phẩm không tồn tại", null)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server: " + e.getMessage(), null)
            );
        }
    }

    public ResponseEntity<ApiResponse> deleteProduct(Integer id) {
        try {
            if (!repo.existsById(id)) {
                return ResponseEntity.badRequest().body(
                    new ApiResponse<>("ERROR", "Sản phẩm không tồn tại", null)
                );
            }
            repo.deleteById(id);
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Xóa sản phẩm thành công", null)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    // Lọc theo category
    public ResponseEntity<ApiResponse> listByCategory(Integer categoryId) {
        try {
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Lấy sản phẩm theo danh mục thành công", repo.findByCategoryId(categoryId))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }

    // Lọc theo brand
    public ResponseEntity<ApiResponse> listByBrand(Integer brandId) {
        try {
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Lấy sản phẩm theo thương hiệu thành công", repo.findByBrandId(brandId))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Lỗi Server", null)
            );
        }
    }
}
