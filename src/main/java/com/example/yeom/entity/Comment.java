package com.example.yeom.entity;


import com.example.yeom.dto.CommentDto;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity{

    @Id // 대표값을 지정! like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3, ... DB가 id를 자동 생성하는 어노테이션
    private Long id;

    @ManyToOne  // 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다!
    @JoinColumn(name = "bbs_id") // "엔티티 객체 자체를 통째로 참조 = FK (Article의 id를 담는것) -> 필드명 +  “_” + 참조하는 테이블의 기본 키(@Id) 컬럼명
    private Bbs bbs;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Bbs bbs) {

        // 예외 처리
        if(dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }
        if(dto.getBbsId() != bbs.getId()){
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        }

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                bbs,
                dto.getNickname(),
                dto.getBody()
        );
    }


    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId()) // url의 id값과 json 입력으로부터 받아온 데이터의 id값이 다른 경우
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        // 객체를 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getBody() != null)
            this.body = dto.getBody();
    }



}
