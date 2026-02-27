package com.example.WebBanHang.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    /**
     * Có thể là email (dành cho CLIENT) hoặc username (dành cho ADMIN/STAFF)
     */
    @NotBlank(message = "Tài khoản không được để trống")
    private String identifier;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
}