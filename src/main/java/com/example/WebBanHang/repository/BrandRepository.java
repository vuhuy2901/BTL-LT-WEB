package com.example.WebBanHang.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanHang.model.Brand;
@Repository
public interface BrandRepository  extends JpaRepository<Brand , Integer >{
    public Brand findByName(String name); 
    public List<Brand> findAll(); 
    public Optional findById(Integer id); 
    public void deleteById(Integer id);  
    
} 
