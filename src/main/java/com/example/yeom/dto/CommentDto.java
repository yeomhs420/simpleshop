package com.example.yeom.dto;

import com.example.yeom.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class) // 스네이크 케이스로 property 들을 일괄적으로 매핑시켜줌
//@JsonInclude(JsonInclude.Include.NON_NULL)  // property 가 NULL 인 값은 포함하지 않겠다
public class CommentDto {
    private Long id;
    @JsonProperty("bbs_id") // json 에서 article_id 라는 이름으로 데이터가 날라옴 (자동으로 매핑)
    private Long bbsId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getBbs().getId(),
                comment.getNickname(),
                comment.getBody()
        );  // comment 엔티티를 commentDto로 변환하는 메서드
    }
}
