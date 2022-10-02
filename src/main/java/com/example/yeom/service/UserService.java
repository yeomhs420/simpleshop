package com.example.yeom.service;

import com.example.yeom.dto.UserDto;
import com.example.yeom.entity.User;
import com.example.yeom.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;


    @Transactional
    public boolean create(UserDto dto, Model model) {   // 회원가입

        if(dto.getUserID() == "" || dto.getUserPassword() == "" || dto.getUserName() == "" ||
                dto.getUserGender() == "" || dto.getUserEmail() == "") {
            model.addAttribute("msg", "입력이 안된 사항이 있습니다.");
            return true;
        }

        else if(userRepository.findByUserId(dto.getUserID()).isEmpty()){
            userRepository.save(dto.toEntity());
            return false;
        }

        else{
            model.addAttribute("msg", "이미 존재하는 ID입니다.");
            return true;
        }
    }

    public boolean isUser(String id, String pw, Model model){   // 로그인 본인 확인 절차

        if(userRepository.findByUserId(id).isEmpty() == true){
            model.addAttribute("msg", "존재하지 않는 ID입니다.");
            return false;
        }

        else{
            if(pw.equals(userRepository.findByUserId(id).get(0).getUserPassword())){
                return true;
            }
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            return false;
        }
    }

    public UserDto getUser(){
        String userID = (String) session.getAttribute("userID");
        User user = userRepository.findByUserId(userID).get(0);
        UserDto userDto = UserDto.createUserDto(user);

        return userDto;
    }

    @Transactional
    public void chargeMoney(String userID, int money){  // 금액 충전
        int balance = userRepository.findByUserId(userID).get(0).getBalance();

        balance += money;

        User user = userRepository.findByUserId(userID).get(0);

        user.setBalance(balance);

        userRepository.save(user);
    }

    @Transactional
    public void buy_product(Model model, int money){

        User user = userRepository.findByUserId((String)session.getAttribute("userID")).get(0);
        int balance = userRepository.findByUserId((String)session.getAttribute("userID")).get(0).getBalance();

        if(balance >= money){
            user.setBalance(balance - money);
            userRepository.save(user);
            model.addAttribute("msg", "구매가 완료되었습니다.");
        }
        else {
            model.addAttribute("msg", "금액이 부족합니다.");
        }
    }
}
