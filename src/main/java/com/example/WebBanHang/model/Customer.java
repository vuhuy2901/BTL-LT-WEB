package com.example.WebBanHang.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Setter
@Getter
@NoArgsConstructor  // <--- Cần thiết cho JPA
@AllArgsConstructor // <--- Tiện lợi khi new Customer()
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;
    @Column(name = "full_name", nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 20)
    private String phone;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "date_of_birth")
    private LocalDate birthDate; 
    @Column(columnDefinition = "ENUM('Nam', 'Nữ', 'Khác')")
    private String gender;
    @Column(name = "is_active",nullable = false)
    private Boolean isActive = true;

    @CreatedDate
    @Column(name = "created_at") 
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}