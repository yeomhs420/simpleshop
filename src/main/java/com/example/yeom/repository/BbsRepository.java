package com.example.yeom.repository;

import com.example.yeom.entity.Bbs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BbsRepository extends JpaRepository<Bbs, Long> {
    @Override
    List<Bbs> findAll();

    @Query(value = "SELECT * FROM Bbs WHERE id = :bbsid", nativeQuery = true)
    List<Bbs> findByBbsId(Long bbsid);
}
