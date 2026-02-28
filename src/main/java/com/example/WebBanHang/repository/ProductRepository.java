package com.example.WebBanHang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanHang.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findAll();
    public List<Product> findByCategoryId(Integer categoryId);
    public List<Product> findBySportId(Integer sportId);
    public List<Product> findByBrandId(Integer brandId);
    public List<Product> findByIsActiveTrue();
    public List<Product> findByNameContainingIgnoreCase(String keyword);
}
