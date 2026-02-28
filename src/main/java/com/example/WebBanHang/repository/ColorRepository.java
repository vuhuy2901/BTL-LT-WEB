package com.example.WebBanHang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanHang.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    public Color findByName(String name);
    public List<Color> findAll();
    public Optional<Color> findById(Integer id);
    public void deleteById(Integer id);
}
