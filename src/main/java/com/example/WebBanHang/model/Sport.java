package com.example.WebBanHang.model;

import java.sql.Date;
import java.time.LocalDateTime;

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
@Table(name = "sports")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sport {
    @Id
    @Column(name = "sport_id") 
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id ;
    @Column(name = "sport_name") 
    private String name ; 
    @Column (name = "description")
    private String description ; 
    private LocalDateTime  createAt ; 

     
} 
