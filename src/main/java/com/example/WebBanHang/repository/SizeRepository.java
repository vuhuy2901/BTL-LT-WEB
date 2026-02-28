package com.example.WebBanHang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanHang.model.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    public Size findByName(String name);
    public List<Size> findAll();
    public List<Size> findAllByOrderByOrderAsc();
    public Optional<Size> findById(Integer id);
    public void deleteById(Integer id);
}
