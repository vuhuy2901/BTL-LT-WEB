package com.example.WebBanHang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Color;
import com.example.WebBanHang.service.ColorService;

@RestController
@RequestMapping("color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("list")
    public ResponseEntity<ApiResponse> listColor() {
        return colorService.listColor();
    }

    @PostMapping("add")
    public ResponseEntity<ApiResponse> addColor(@RequestBody Color color) {
        return colorService.addColor(color);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse> updateColor(@PathVariable Integer id, @RequestBody Color color) {
        return colorService.updateColor(id, color);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse> deleteColor(@PathVariable Integer id) {
        return colorService.deleteColor(id);
    }
}
