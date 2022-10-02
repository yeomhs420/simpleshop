package com.example.yeom.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity{
    @Id // 대표값을 지정! like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3, ... id 값을 null 로 하면 DB가 id를 자동 생성하는 어노테이션
    private Long id;

    private String userID;

    @Column(name = "user_password")
    private String userPassword;

    private String username;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_gender")
    private String userGender;

    private int balance;

}
