package com.example.yeom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id // 대표값을 지정! like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3, ... DB가 id를 자동 생성하는 어노테이션
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // FK
    private User user;

    private String name;

    private int amount;

    private int price;

    private String image_url;
}
