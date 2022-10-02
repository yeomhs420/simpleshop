package com.example.yeom.entity;

import com.example.yeom.repository.BbsRepository;
import com.example.yeom.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BbsTest {
    @Autowired
    BbsRepository bbsRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void test(){
        User user = new User();
        user.setUserID("ha");
        user.setBalance(100);
        user.setUserEmail("ye@nae");
        user.setUserPassword("1234");
        user.setUsername("haha");
        user.setUserGender("남자");
        System.out.println(userRepository.save(user));
        System.out.println(userRepository.findById(1L));
    }
}