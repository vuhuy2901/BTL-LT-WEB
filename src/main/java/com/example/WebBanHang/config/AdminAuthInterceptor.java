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
        // TODO: Bật lại trước khi deploy thực tế
        // User currentUser = (User) request.getSession().getAttribute("currentUser");
        // if (currentUser == null ||
        //     (!"ADMIN".equals(currentUser.getRole()) && !"STAFF".equals(currentUser.getRole()))) {
        //     response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //     response.setContentType("application/json");
        //     response.setCharacterEncoding("UTF-8");
        //     response.getWriter().write(
        //         "{\"status\":\"ERROR\",\"message\":\"Bạn chưa đăng nhập hoặc không có quyền truy cập!\",\"data\":null}"
        //     );
        //     return false;
        // }
        return true; // Tạm tắt xác minh để dev
    }
}
