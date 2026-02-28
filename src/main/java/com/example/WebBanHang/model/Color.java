package com.example.WebBanHang.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "colors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Color {
    @Id
    @Column(name = "color_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "color_name", nullable = false)
    private String name;
    @Column(name = "color_code" )
    private String code; 

}
