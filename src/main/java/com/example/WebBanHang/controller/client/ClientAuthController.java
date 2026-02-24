package com.example.WebBanHang.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.dto.LoginDto;
import com.example.WebBanHang.dto.UserRegistrationDto;
import com.example.WebBanHang.model.Customer;
import com.example.WebBanHang.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Controller
public class ClientAuthController {


    @Autowired
    private CustomerService customerService;


    @GetMapping("/login")
    public String showLoginPage() {
        return "client/login"; // templates/client/login.html
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "client/register"; // templates/client/register.html
    }


    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> register(
            @RequestBody @Valid UserRegistrationDto registrationDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMsg.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", errorMsg.toString(), null));
        }

        Customer customer = new Customer();
        customer.setName(registrationDto.getName());
        customer.setEmail(registrationDto.getEmail());
        customer.setPhone(registrationDto.getPhone());
        customer.setPassword(registrationDto.getPassword());
        customer.setBirthDate(registrationDto.getBirthDate());
        customer.setGender(registrationDto.getGender());
        customer.setIsActive(true);

        return customerService.register(customer);
    }

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

        return customerService.login(loginDto.getEmail(), loginDto.getPassword(), session);
    }
}
