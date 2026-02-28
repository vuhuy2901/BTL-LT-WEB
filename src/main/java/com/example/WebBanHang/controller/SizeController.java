package com.example.WebBanHang.controller;

import java.util.List;

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
import com.example.WebBanHang.model.Size;
import com.example.WebBanHang.service.SizeService;

@RestController
@RequestMapping("size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping("list")
    public ResponseEntity<ApiResponse> listSize() {
        return sizeService.listSize();
    }

    @PostMapping("add")
    public ResponseEntity<ApiResponse> addSize(@RequestBody Size size) {
        return sizeService.addSize(size);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse> updateSize(@PathVariable Integer id, @RequestBody Size size) {
        return sizeService.updateSize(id, size);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse> deleteSize(@PathVariable Integer id) {
        return sizeService.deleteSize(id);
    }

    @PutMapping("reorder")
    public ResponseEntity<ApiResponse> reorderSize(@RequestBody List<Integer> ids) {
        return sizeService.reorderSize(ids);
    }
}
