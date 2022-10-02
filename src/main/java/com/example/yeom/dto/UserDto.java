package com.example.yeom.dto;

import com.example.yeom.entity.Comment;
import com.example.yeom.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDto {
    private Long id;
    private String userID;
    private String userPassword;
    private String userName;
    private String userGender;
    private String userEmail;
    private int balance;

    public static UserDto createUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUserID(),
                user.getUserPassword(),
                user.getUsername(),
                user.getUserEmail(),
                user.getUserGender(),
                user.getBalance()
        );
    }

    public User toEntity(){
        return new User(id, userID, userPassword, userName, userEmail, userGender, balance);
    }
}
