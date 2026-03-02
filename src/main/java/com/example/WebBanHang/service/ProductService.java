package com.example.WebBanHang.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.dto.ProductDto;
import com.example.WebBanHang.dto.ProductSummaryDto;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.repository.ProductRepository;
import com.example.WebBanHang.service.WishListService;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Page<Product> getPaginatedProducts(Pageable pageable) {
        return repo.findByIsActiveTrue(pageable);
    }

    @Autowired
    private WishListService wishListService;

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
   
    public ResponseEntity<ApiResponse> listSummary(Integer userId) {
        try {
            // Lấy danh sách ID đã thích của user (nếu có)
            List<Integer> wishListProductIds = (userId != null) ? 
                wishListService.getWishListProductIds(userId) : List.of();

            List<ProductSummaryDto> summaries = repo.findByIsActiveTrue()
                .stream()
                .map(p -> {
                    Integer discount = null;
                    if (p.getSalePrice() != null && p.getBasePrice() > 0
                        && p.getSaleEnd() != null && p.getSaleEnd().isAfter(LocalDateTime.now()) 
                        && p.getSaleStart() != null && p.getSaleStart().isBefore(LocalDateTime.now())) {
                        discount = (int) Math.round((p.getBasePrice() - p.getSalePrice()) * 100.0 / p.getBasePrice());
                    }
                    Boolean isWished = wishListProductIds.contains(p.getId());
                    return new ProductSummaryDto(
                        p.getId(),
                        p.getName(),
                        p.getThumbnailUrl(),
                        p.getBasePrice(),
                        discount != null ? p.getSalePrice() : null,
                        p.getGender() != null ? p.getGender().name() : null,
                        p.getSaleStart(),
                        p.getSaleEnd(),
                        discount,
                        isWished
                    );
                })
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

    public Page<ProductSummaryDto> listSummaryPaginated(Integer userId, Pageable pageable) {
        List<Integer> wishListProductIds = (userId != null) ? 
            wishListService.getWishListProductIds(userId) : List.of();

        Page<Product> productPage = repo.findByIsActiveTrue(pageable);
        
        return productPage.map(p -> {
            Integer discount = null;
            if (p.getSalePrice() != null && p.getBasePrice() > 0
                && p.getSaleEnd() != null && p.getSaleEnd().isAfter(LocalDateTime.now())  
                && p.getSaleStart() != null && p.getSaleStart().isBefore(LocalDateTime.now())) {
                discount = (int) Math.round((p.getBasePrice() - p.getSalePrice()) * 100.0 / p.getBasePrice());
            }
            Boolean isWished = wishListProductIds.contains(p.getId());
            return new ProductSummaryDto(
                p.getId(),
                p.getName(),
                p.getThumbnailUrl(),
                p.getBasePrice(),
                discount != null ? p.getSalePrice() : null,
                p.getGender() != null ? p.getGender().name() : null,
                p.getSaleStart(),
                p.getSaleEnd(),
                discount,
                isWished
            );
        });
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
    public Product getProduct(Integer id) {
        try {
            return repo.findById(id)
                .map(p -> p)
                .orElse(null);
        } catch (Exception e) {
            return null  ;
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
    public Page<ProductSummaryDto> filterProducts(
        Integer userId,
        List<Integer> categoryIds,
        List<Integer> sportIds,
        List<Integer> brandIds,
        String sortBy,
        Long minPrice,
        Long maxPrice,
        Pageable pageable) {

    Specification<Product> spec = (root, query, cb) -> cb.conjunction();

    // SỬA Ở ĐÂY: Thay vì get("category").get("id"), chỉ cần get("categoryId")
    if (categoryIds != null && !categoryIds.isEmpty())
        spec = spec.and((root, q, cb) -> root.get("categoryId").in(categoryIds));

    if (sportIds != null && !sportIds.isEmpty())
        spec = spec.and((root, q, cb) -> root.get("sportId").in(sportIds));

    if (brandIds != null && !brandIds.isEmpty())
        spec = spec.and((root, q, cb) -> root.get("brandId").in(brandIds));

    if (minPrice != null)
        spec = spec.and((root, q, cb) -> cb.greaterThanOrEqualTo(root.get("basePrice"), minPrice));

    if (maxPrice != null)
        spec = spec.and((root, q, cb) -> cb.lessThanOrEqualTo(root.get("basePrice"), maxPrice));

    // sort
    Sort sort = switch (sortBy != null ? sortBy : "newest") {
        case "price_asc"  -> Sort.by("basePrice").ascending();
        case "price_desc" -> Sort.by("basePrice").descending();
        case "name_asc"   -> Sort.by("name").ascending();
        default           -> Sort.by("createdAt").descending();
    };

    Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    Page<Product> productPage = repo.findAll(spec, sortedPageable);

    List<Integer> wishListProductIds = (userId != null) ? 
        wishListService.getWishListProductIds(userId) : List.of();

    return productPage.map(p -> mapToDto(p, wishListProductIds));
}

    private ProductSummaryDto mapToDto(Product p, List<Integer> wishListProductIds) {
        Integer discount = null;
        if (p.getSalePrice() != null && p.getBasePrice() > 0
            && p.getSaleEnd() != null && p.getSaleEnd().isAfter(LocalDateTime.now())  && p.getSaleStart() != null && p.getSaleStart().isBefore(LocalDateTime.now()) )  {
            discount = (int) Math.round((p.getBasePrice() - p.getSalePrice()) * 100.0 / p.getBasePrice());
        }
        Boolean isWished = wishListProductIds.contains(p.getId());
        return new ProductSummaryDto(
            p.getId(),
            p.getName(),
            p.getThumbnailUrl(),
            p.getBasePrice(),
            discount != null ? p.getSalePrice() : null,
            p.getGender() != null ? p.getGender().name() : null,
            p.getSaleStart(),
            p.getSaleEnd(),
            discount,
            isWished
        );
    }
}
