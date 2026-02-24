package com.example.WebBanHang.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brands")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id 
    @Column(name = "brand_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer  Id; 
    @Column(name = "brand_name" , nullable =  false)
    private String name ;
    @Column(name =  "description")
    private String  description ; 
    

}
