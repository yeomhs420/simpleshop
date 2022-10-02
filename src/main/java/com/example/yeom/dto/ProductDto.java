package com.example.yeom.dto;

import com.example.yeom.entity.Product;
import com.example.yeom.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProductDto {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;
    private String name;
    private int amount;
    private int price;
    private String image_url;

    public static ProductDto createProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getUser().getId(),
                product.getName(),
                product.getAmount(),
                product.getPrice(),
                product.getImage_url()
        );
    }
}
