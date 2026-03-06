package com.example.WebBanHang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebBanHang.model.Cart;

public interface CartRepository  extends JpaRepository<Cart, Integer>  {

    List<Cart> findByUserId(Integer userId);
    
}
