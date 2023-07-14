package com.wts.webclientdemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Integer id;
    private String title;
    private String description;
    private Float price;
    private Float discountPercentage;
    private Float rating;
    private Integer stock;
    private String brand;
    private String category;
    private String thumbnail;
    private String[] images;

}
