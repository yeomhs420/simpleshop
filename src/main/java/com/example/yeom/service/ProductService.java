package com.example.yeom.service;

import com.example.yeom.dto.ProductDto;
import com.example.yeom.entity.Product;
import com.example.yeom.entity.User;
import com.example.yeom.repository.ProductRepository;
import com.example.yeom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void addProduct(HttpServletRequest request){   // 찜한 물건 장바구니에 추가
        boolean pexist = false;

        Long id = userRepository.findByUserId(request.getParameter("id")).get(0).getId();

        for(Product p : productRepository.findByUserId(id)){
            if(p.getName().equals(request.getParameter("name"))){    // 찜한 상품이 장바구니에 이미 존재할 경우
                p.setAmount(p.getAmount() + 1);
                p.setImage_url(request.getParameter("image_url"));
                p.setName(request.getParameter("name"));
                p.setPrice(p.getPrice() + Integer.parseInt(request.getParameter("price")));

                User user = userRepository.findByUserId(request.getParameter("id")).get(0);
                p.setUser(user);

                productRepository.save(p);  // update
                pexist = true;
                break;
            }
        }


        if(pexist == false){    // 장바구니에 상품이 없을 경우 새로 추가
            System.out.println(request.getParameter("name"));
            Product product = new Product();
            product.setAmount( Integer.parseInt(request.getParameter("amount")));
            product.setImage_url(request.getParameter("image_url"));
            product.setName(request.getParameter("name"));
            product.setPrice(Integer.parseInt(request.getParameter("price")));

            User user = userRepository.findByUserId(request.getParameter("id")).get(0);
            product.setUser(user);

            productRepository.save(product);    // insert
        }

    }

    public List<ProductDto> product_List(){ // 장바구니 목록
        String userID = (String) session.getAttribute("userID");
        Long id = userRepository.findByUserId(userID).get(0).getId();

        List<Product> productList = productRepository.findByUserId(id);

        return productList.stream().map(product -> ProductDto.createProductDto(product))
                .collect(Collectors.toList());
    }

    public int total_price(List<ProductDto> productDtos){
        int price = 0;
        for(ProductDto p : productDtos){
            price += p.getPrice();
        }
        return price;
    }

    @Transactional
    public void deleteProduct(HttpServletRequest request){
        Product product = productRepository.findByName(request.getParameter("name")).get(0);
        productRepository.delete(product);
    }
}
