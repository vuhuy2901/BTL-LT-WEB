package com.example.WebBanHang.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Sport;
import com.example.WebBanHang.repository.SportRepository;

@Service
public class SportService {
    
    @Autowired
    private SportRepository repo;

    public ResponseEntity<ApiResponse> addSport(Sport sport) {
        if (sport.getName() == null || sport.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Tên môn thể thao không được để trống", null)
            );
        }
        String name = sport.getName().trim() ;
        if (repo.findByName(name) != null) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>("ERROR", "Tên môn thể thao đã tồn tại", null)
            );
        } 
        sport.setCreateAt(LocalDateTime.now()); 
        repo.save(sport);
        return ResponseEntity.ok().body(
            new ApiResponse<>("SUCCESS", "Thêm môn thể thao thành công", sport)
        );
    } 
    public List<Sport> getAllSports() {
        return repo.findAll();
    }  
    public ResponseEntity<ApiResponse> listSport(){
        return ResponseEntity.ok().body(
            new ApiResponse<>("SUCCESS", "Lấy danh sách môn thể thao thành công", repo.findAll())
        );
    }
}
