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
@Table(name = "sizes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Size {

    @Id
    @Column(name = "size_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "size_name", nullable = false)
    private String name;

    @Column(name = "size_order")
    private Integer order;
}
