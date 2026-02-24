package com.example.WebBanHang.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotNull(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;
    @NotNull(message = "Password is required")
    private String password;
}
 