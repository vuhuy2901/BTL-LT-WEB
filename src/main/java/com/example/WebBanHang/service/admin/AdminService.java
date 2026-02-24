package com.example.WebBanHang.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;


import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Admin;
import com.example.WebBanHang.repository.AdminRepository;

@Service
public class AdminService {
    @Autowired 
    public AdminRepository adminRepository;
    public ResponseEntity<ApiResponse> login (String username, String password, HttpSession session) {
        try {
            System.out.println("Login info: " + username);
            Admin a = adminRepository.findByUsername(username.trim());
            System.out.println(a);
            if(a == null){
                return  ResponseEntity.badRequest().body(
                    new ApiResponse<>(" ERROR", "Không tìm thấy  tài khoản", null)
                ) ; 
            }
            if(!a.getPassword().equals(password)) {
                return ResponseEntity.badRequest().body(
                    new ApiResponse<>("ERROR", "Sai mật khẩu", null)
                ) ;
            }
            
            // Lưu thông tin admin vào session
            session.setAttribute("admin", a);
            
            return ResponseEntity.ok().body(
                new ApiResponse<>("SUCCESS", "Login success", a)
            ) ;
        } catch (Exception e) {
            return ResponseEntity.ok().body(
                new ApiResponse<>("ERROR", "Lỗi Server" , null ) 
             );
        }
    }
}
