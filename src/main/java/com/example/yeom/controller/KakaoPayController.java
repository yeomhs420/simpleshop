package com.example.yeom.controller;

import com.example.yeom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class KakaoPayController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @GetMapping("/kakaoPay")
    public String Payment(Model model){
        model.addAttribute("userID", session.getAttribute("userID"));
        return "payment";
    }

    @ResponseBody
    @GetMapping("/charge/point")
    public ResponseEntity<String> Charge(@RequestParam("amount") String amount){

        userService.chargeMoney((String)session.getAttribute("userID"), Integer.parseInt(amount));

        return ResponseEntity.ok("success");
    }
}
