package com.example.yeom.entity;

import com.example.yeom.listener.Auditable;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass   // 해당 entity의 컬럼을 상속받는 entity의 컬럼으로 포함 시켜 주겠다!
@EntityListeners(value = AuditingEntityListener.class)  // JPA 에서 기본적으로 제공하는 class
public class BaseEntity{

    @CreatedDate
    @Column(columnDefinition = "datetime(0) default now()", nullable = false, updatable = false)   // data.sql 에서 일일히 날짜 삽입 안해도 됨
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(columnDefinition = "datetime(0) default now()", nullable = false)
    private LocalDateTime updatedAt;
}