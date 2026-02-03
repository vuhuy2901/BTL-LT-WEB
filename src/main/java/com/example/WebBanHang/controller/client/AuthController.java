package com.example.WebBanHang.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Customer;
import com.example.WebBanHang.service.CustomerService;

@RestController
@RequestMapping("auth")

public class AuthController {
    
    @Autowired
    private CustomerService customerService;
    @PostMapping("register")
    public ResponseEntity<ApiResponse<Object>> register(@RequestBody Customer customer) {
        return customerService.register(customer);
    } 
    @PostMapping("login")
    public ResponseEntity<ApiResponse<Object>> login(@RequestBody Customer customer, HttpSession session) {
        return customerService.login(customer.getEmail(), customer.getPassword() , session ) ; 
    }
}
