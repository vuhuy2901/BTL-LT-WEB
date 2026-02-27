package com.example.WebBanHang.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    /** Trang chủ "/" — kiểm tra session */
    @RequestMapping("/")
    public String home(HttpSession session) {
        if (session.getAttribute("customer") != null) {
            return "client/index"; // templates/client/index.html
        }
        return "redirect:/login";
    }
}
