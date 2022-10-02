package com.example.yeom.repository;

import com.example.yeom.entity.Product;
import com.example.yeom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product WHERE user_id = :userId", nativeQuery = true)
    List<Product> findByUserId(Long userId);

    @Query(value = "SELECT * FROM product WHERE name = :name", nativeQuery = true)
    List<Product> findByName(String name);

}
