package com.example.yeom.repository;

import com.example.yeom.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Commit
    public void test(){
        userRepository.save(new User(2L, "yeom", "1234", "ye", "yeomhs420@naver.com", "남자", 0));
    }
}