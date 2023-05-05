package com.example.yeom.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Bbs extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String Title;

    @ManyToOne
    @JoinColumn(name = "user_id") // FK
    private User user;

    private String Content;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bbs_id")    // 양방향 매핑 (지연 로딩 문제)
    @ToString.Exclude   // toString() 결과에서 제외
    private List<Comment> comments = new ArrayList<>();


}
