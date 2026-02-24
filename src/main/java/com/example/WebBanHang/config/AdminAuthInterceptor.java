package com.example.WebBanHang.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AdminAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        
       ;    
        if (session.getAttribute("admin") == null) {
            
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\": \"ERROR\", \"message\": \"Bạn chưa đăng nhập Admin hoặc phiên đăng nhập đã hết hạn!\", \"data\": null}");
            
            return false; // Chặn request không cho đi tiếp vào Controller
        }
        
        return true; // Cho phép đi tiếp
    }
}
