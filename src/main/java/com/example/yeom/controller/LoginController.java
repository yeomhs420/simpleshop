package com.example.yeom.controller;

import com.example.yeom.dto.UserDto;
import com.example.yeom.repository.UserRepository;
import com.example.yeom.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public String Login(){
        return "login/login";
    }

    @GetMapping("/join")
    public String Join(Model model){

        return "login/join";
    }


    @RequestMapping("/joinAction")
    public String JoinAction(UserDto userDto, Model model, RedirectAttributes redirectAttributes){

        if(userService.create(userDto, model) == true) {
            return "login/join";
        }
        else {
            session.setAttribute("userID", userDto.getUserID());

            return "redirect:/shop";
        }

    }

    @RequestMapping("/loginAction") // Parameter를 입력받는 경우, @RequestMapping or @PostMapping 사용
    public String LoginAction(UserDto userDto, Model model){ // @RequestParam("userID") String id, @RequestParam("userPassword") String pw

        if(userService.isUser(userDto.getUserID(), userDto.getUserPassword(), model) == true) {
            session.setAttribute("userID", userDto.getUserID());
            return "redirect:/shop";
        }

        return "login/login";
    }

    @RequestMapping("/logoutAction")
    public String LoginAction(){
        session.invalidate();

        return "redirect:/shop";
    }
}
