package com.example.WebBanHang.dto;

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
    private Double basePrice;
    private Double salePrice;
    private String gender;   
}
