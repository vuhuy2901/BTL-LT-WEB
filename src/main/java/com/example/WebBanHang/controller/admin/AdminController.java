package com.example.WebBanHang.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Admin;
import com.example.WebBanHang.service.admin.AdminService;

@RestController
@RequestMapping("admin")

public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("login")
    public ResponseEntity<ApiResponse> login(@RequestBody Admin admin, HttpSession session) {
        
        if(admin.getUsername() == null  || admin.getUsername().equals("") || admin.getPassword() == null || admin.getPassword().equals("") ) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", "Username và password không được để trống", null));
        } 
        return adminService.login(admin.getUsername(), admin.getPassword(), session);
    }
}
