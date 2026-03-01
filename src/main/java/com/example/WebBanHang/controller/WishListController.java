package com.example.WebBanHang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.WishList;
import com.example.WebBanHang.service.WishListService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wishlist")
public class WishListController {
    @Autowired
    private WishListService wishListService; 
    @PostMapping("add")
    @ResponseBody
    public ResponseEntity<ApiResponse<WishList>> addWishList(@RequestBody java.util.Map<String, Integer> payload) {
        wishListService.addWishList(payload.get("userId"), payload.get("productId"));
        return ResponseEntity.ok().body(new ApiResponse<>("SUCCESS", "Thêm vào danh sách yêu thích thành công", null));
    }

    @PostMapping("remove")
    @ResponseBody
    public ResponseEntity<ApiResponse<Void>> removeWishList(@RequestBody java.util.Map<String, Integer> payload) {
        wishListService.removeWishList(payload.get("userId"), payload.get("productId"));
        return ResponseEntity.ok().body(new ApiResponse<>("SUCCESS", "Đã xóa khỏi danh sách yêu thích", null));
    }
}
