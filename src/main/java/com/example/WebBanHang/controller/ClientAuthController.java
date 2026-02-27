package com.example.WebBanHang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.dto.LoginDto;
import com.example.WebBanHang.dto.UserRegistrationDto;
import com.example.WebBanHang.model.User;
import com.example.WebBanHang.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ClientAuthController {

    @Autowired
    private UserService userService;

    // ========== PAGES ==========

    @GetMapping("/login")
    public String showLoginPage() {
        return "client/login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "client/register";
    }

    // ========== API ==========

    /**
     * Đăng nhập chung cho tất cả role.
     * identifier = email (CLIENT) hoặc username (ADMIN/STAFF)
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> login(
            @RequestBody @Valid LoginDto loginDto,
            HttpSession session,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMsg.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", errorMsg.toString(), null));
        }

        return userService.login(loginDto.getIdentifier(), loginDto.getPassword(), session);
    }

    /**
     * Đăng ký tài khoản mới (chỉ dành cho CLIENT)
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> register(
            @RequestBody @Valid UserRegistrationDto dto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMsg.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", errorMsg.toString(), null));
        }

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setGender(dto.getGender());
        user.setIsActive(true);
        user.setRole("CLIENT");

        return userService.register(user);
    }

    /**
     * Đăng xuất
     */
    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> logout(HttpSession session) {
        return userService.logout(session);
    }

    /**
     * Lấy thông tin user đang đăng nhập
     */
    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> getProfile(HttpSession session) {
        return userService.getProfile(session);
    }
}
