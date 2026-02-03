package com.example.WebBanHang.repository;

import com.example.WebBanHang.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByEmail(String email) ; 
    
}
