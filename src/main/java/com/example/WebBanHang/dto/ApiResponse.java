package com.example.WebBanHang.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String status;  // Ví dụ: "SUCCESS", "ERROR"
    private String message; // Ví dụ: "Đăng nhập thành công"
    private T data;         // Dữ liệu kèm theo (Ví dụ: thông tin Customer), có thể null
}