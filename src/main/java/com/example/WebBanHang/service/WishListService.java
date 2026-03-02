package com.example.WebBanHang.service;

import java.time.LocalDateTime;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanHang.model.WishList;
import com.example.WebBanHang.repository.WishListRepository;
@Service
public class WishListService {
    
    @Autowired
    private WishListRepository wishListRepository; 

    public void addWishList(Integer userId, Integer productId) {
        WishList wishList = new  WishList(userId, productId, LocalDateTime.now());
        wishListRepository.save(wishList); 
    }

    @org.springframework.transaction.annotation.Transactional
    public void removeWishList(Integer userId, Integer productId) {
        wishListRepository.deleteByUserIdAndProductId(userId, productId);
    }

    public java.util.List<Integer> getWishListProductIds(Integer userId) {
        return wishListRepository.findProductIdsByUserId(userId);
    }

    public @Nullable Object listWishList(Integer userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listWishList'");
    }
}
