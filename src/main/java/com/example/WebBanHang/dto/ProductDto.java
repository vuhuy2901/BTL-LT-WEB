package com.example.WebBanHang.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull(message = "Danh mục không được để trống")
    private Integer categoryId;
    @NotNull(message = "Môn thể thao không được để trống")
    private Integer sportId;
    @NotNull(message = "Thương hiệu không được để trống")
    private Integer brandId;
    private String description;
    @Pattern(regexp = "Nam|Nu|Unisex", message = "Giới tính phải là Nam, Nu hoặc Unisex")
    private String gender;

    @NotNull(message = "Giá gốc không được để trống")
    @Min(value = 0, message = "Giá gốc phải >= 0")
    private Double basePrice;

    @Min(value = 0, message = "Giá sale phải >= 0")
    private Double salePrice;

    private LocalDateTime saleStart;
    private LocalDateTime saleEnd;
    private String thumbnailUrl;
    private Boolean isActive;
}
