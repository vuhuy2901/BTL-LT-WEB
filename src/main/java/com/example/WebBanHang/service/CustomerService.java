package com.example.WebBanHang.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.WebBanHang.model.Customer;
import com.example.WebBanHang.repository.CustomerRepository;

public class CustomerService {
    @Autowired 
    public CustomerRepository customerRepository;
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    
}
