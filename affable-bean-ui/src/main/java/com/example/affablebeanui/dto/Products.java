package com.example.affablebeanui.dto;

import lombok.Data;

import java.util.List;



@Data
public class Products {
    private List<Product> products;

    public Products(){

    }
    public Products(List<Product> products){
        this.products=products;
    }


}
