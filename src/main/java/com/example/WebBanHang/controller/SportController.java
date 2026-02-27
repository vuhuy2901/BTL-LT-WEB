package com.example.WebBanHang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.example.WebBanHang.dto.ApiResponse;
import com.example.WebBanHang.model.Sport;
import com.example.WebBanHang.service.SportService;

@Controller
@RequestMapping("admin/sport")
public class SportController {

    @Autowired
    private SportService sportService;

    @PostMapping("add")
    @ResponseBody
    public ResponseEntity<ApiResponse> addSport(@RequestBody Sport sport) {
        return sportService.addSport(sport);
    }

    @GetMapping("list")
    @ResponseBody
    public ResponseEntity<ApiResponse> listSport() {
        return sportService.listSport();
    }
}
