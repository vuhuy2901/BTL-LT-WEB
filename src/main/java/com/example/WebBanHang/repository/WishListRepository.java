package com.example.WebBanHang.repository;

import com.example.WebBanHang.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface WishListRepository  extends JpaRepository<WishList, Integer>  {
    void deleteByUserIdAndProductId(Integer userId, Integer productId);

    @Query("SELECT w.productId FROM WishList w WHERE w.userId = :userId")
    List<Integer> findProductIdsByUserId(@Param("userId") Integer userId);
    List<WishList> findByUserId(Integer userId);
    void deleteByUserId(Integer userId);
}
