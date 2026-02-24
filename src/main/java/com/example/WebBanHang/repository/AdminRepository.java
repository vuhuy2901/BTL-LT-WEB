package com.example.WebBanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebBanHang.model.Admin;

public interface AdminRepository  extends 
JpaRepository<Admin, Integer > {
    public Admin findByUsername(String username) ; 
     
} 
