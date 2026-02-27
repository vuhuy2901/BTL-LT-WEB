package com.example.WebBanHang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebBanHang.model.Sport;

public interface SportRepository extends JpaRepository<Sport, Integer>  {
    public Sport findByName(String name); 
    public List<Sport> findAll(); 
    public Optional findById(Integer id); 
    public void deleteById(Integer id);  
} 
