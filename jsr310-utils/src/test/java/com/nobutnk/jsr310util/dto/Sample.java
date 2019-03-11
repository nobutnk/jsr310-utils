package com.nobutnk.jsr310util.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Sample {

    private String name;
    
    private Integer price;
    
    private Boolean isPositive;
    
    private List<Item> items;
    
}
