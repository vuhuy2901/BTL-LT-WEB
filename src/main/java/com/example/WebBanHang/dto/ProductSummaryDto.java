package com.example.WebBanHang.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSummaryDto {
    private Integer id;
    private String name;
    private String thumbnailUrl;
    private Long basePrice;
    private Long salePrice;
    private String gender;   
    private LocalDateTime saleStart;
     private LocalDateTime saleEnd;
    private Integer discountPercentage; 
    private Boolean isWished;
}
