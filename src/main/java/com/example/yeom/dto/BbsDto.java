package com.example.yeom.dto;

import com.example.yeom.entity.Bbs;
import com.example.yeom.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BbsDto {
    private Long id;

    private String Title;

    @JsonProperty("user_id")
    private Long userId;

    private String Content;


}
