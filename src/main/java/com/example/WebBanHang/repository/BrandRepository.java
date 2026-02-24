package com.example.WebBanHang.repository;

import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebBanHang.model.Brand;

public interface BrandRepository  extends JpaRepository<Brand , Integer >{
    
    
} 
