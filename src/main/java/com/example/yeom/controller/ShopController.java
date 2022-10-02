package com.example.yeom.controller;

import com.example.yeom.dto.ProductDto;
import com.example.yeom.dto.UserDto;
import com.example.yeom.repository.UserRepository;
import com.example.yeom.service.ProductService;
import com.example.yeom.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@Slf4j
public class ShopController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private HttpSession session;


    @GetMapping("/shop")
    public String SimpleShop(Model model, HttpServletRequest request){

        model.addAttribute("userID", session.getAttribute("userID"));

        model.addAttribute("msg", request.getParameter("msg"));

        return "simpleshop";
    }

    @GetMapping("/mypage")
    public String MyPage(Model model){

        UserDto userDto = userService.getUser();

        model.addAttribute("userID", session.getAttribute("userID"));
        model.addAttribute("userDto", userDto);

        return "mypage";
    }

    @GetMapping("/insertProduct")
    public String InsertProduct(RedirectAttributes attributes, HttpServletRequest request){
        productService.addProduct(request);

        attributes.addAttribute("msg", "장바구니에 추가되었습니다.");

        return "redirect:/shop";

    }


    @GetMapping("/charge")
    public String Charge(Model model){

        model.addAttribute("userID", session.getAttribute("userID"));

        return "charge";
    }

    @RequestMapping("/chargemoney") // 금액 충전
    public String ChargeMoney(Model model, HttpServletRequest request){

        userService.chargeMoney(request.getParameter("id"), Integer.parseInt(request.getParameter("money")));

        return "redirect:mypage";
    }

    @GetMapping("/buyOk")
    public String BuyOk(Model model, HttpServletRequest request){
        userService.buy_product(model, Integer.parseInt(request.getParameter("amount")));

        return "simpleshop";
    }

    @GetMapping("/deleteProduct")   // 장바구니 목록 삭제
    public String DeleteProduct(HttpServletRequest request){
        productService.deleteProduct(request);

        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String Cart2(Model model){
        List<ProductDto> productDtoList = productService.product_List();

        int price = productService.total_price(productDtoList);
        int cnt = productDtoList.size();

        model.addAttribute("userID", session.getAttribute("userID"));
        model.addAttribute("productList", productDtoList);
        model.addAttribute("total_price", price);
        model.addAttribute("product_cnt", cnt);

        return "cart";
    }
}
