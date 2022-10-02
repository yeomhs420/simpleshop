package com.example.yeom.repository;

import com.example.yeom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM User WHERE userid = :userid", nativeQuery = true)
    List<User> findByUserId(String userid);
}

