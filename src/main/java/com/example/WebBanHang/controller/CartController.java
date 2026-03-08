package com.example.WebBanHang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.dto.CartItemDto;
import com.example.WebBanHang.model.Cart;
import com.example.WebBanHang.model.User;
import com.example.WebBanHang.service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("cart") 
public class CartController {
    @Autowired
    private CartService cartService; 

    @Autowired
    private com.example.WebBanHang.service.CategoryService categoryService;

    @Autowired
    private com.example.WebBanHang.service.BrandService brandService;

    @GetMapping("")
    public String cart(HttpSession session,  Model model ){
         User currentUser  = (User) session.getAttribute("currentUser") ;
        if(currentUser == null) {
            return "redirect:/login";
        }
        Integer userId = currentUser.getId(); 
        
        // Đổ dữ liệu cho Header
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("brands", brandService.getAllBrands());
        
        model.addAttribute("cart", cartService.listCart(userId)); 
        return "client/cart"; 
    }
    @PostMapping("add")
    @ResponseBody
    public ResponseEntity<?> addCart(HttpSession session, Model model, @RequestBody Cart cart ){
         User currentUser  = (User) session.getAttribute("currentUser") ;
        if(currentUser == null) {
            return ResponseEntity.status(401).body(new ApiResponse<>("ERROR", "Vui lòng đăng nhập", null));
        }

        Integer userId = currentUser.getId();
        Integer productId = cart.getProductId();
        Integer variantId = cart.getVariantId();
        Integer quantity = cart.getQuantity();

        if (productId == null || quantity == null || quantity <= 0) {
            return ResponseEntity.status(400).body(new ApiResponse<>("ERROR", "Số lượng không hợp lệ", null));
        }

        return cartService.addCart(cart); 
    }

    @DeleteMapping("remove/{id}")
    @ResponseBody
    public ResponseEntity<?> removeCart(HttpSession session, @PathVariable Integer id) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return ResponseEntity.status(401).body(new ApiResponse<>("ERROR", "Vui lòng đăng nhập", null));
        }
        cartService.removeCart(id);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Đã xóa sản phẩm khỏi giỏ hàng", null));
    }

    @PutMapping("update")
    @ResponseBody
    public ResponseEntity<?> updateCart(HttpSession session, @RequestBody Cart cart) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return ResponseEntity.status(401).body(new ApiResponse<>("ERROR", "Vui lòng đăng nhập", null));
        }
        if (cart.getQuantity() == null || cart.getQuantity() <= 0) {
            return ResponseEntity.status(400).body(new ApiResponse<>("ERROR", "Số lượng phải lớn hơn 0", null));
        }
        cartService.updateCart(cart);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Đã cập nhật giỏ hàng", null));
    }

    @GetMapping("data")
    @ResponseBody
    public ResponseEntity<?> getCartData(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        
        if (currentUser == null) {
            return ResponseEntity.status(401).body(
                new ApiResponse<>("ERROR", "Vui lòng đăng nhập để xem giỏ hàng", null)
            );
        }
        
        Integer userId = currentUser.getId();
        List<CartItemDto> cartData = cartService.listCart(userId);

        return ResponseEntity.ok(
            new ApiResponse<>("SUCCESS", "Lấy dữ liệu giỏ hàng thành công", cartData)
        );
    }
}
