package com.example.affablebeanui.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {

    private List<Integer> itemList = new ArrayList<>();

    public ProductDto() {
    }
}
