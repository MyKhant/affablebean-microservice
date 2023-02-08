package com.example.affablebeanui.service;

import com.example.affablebeanui.dto.Product;
import com.example.affablebeanui.dto.Products;
import com.example.affablebeanui.exception.ProductNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductClientService {

    private RestTemplate template = new RestTemplate();

    private List<Product> products;

    @PostConstruct
    public void init(){
        ResponseEntity<Products> response = template.getForEntity("http://localhost:8080/backend/products", Products.class);
        if (response.getStatusCode().is2xxSuccessful()){
            this.products = response.getBody().getProducts();
            System.out.println(products);
        }
        else {
            throw new RuntimeException("Error!");
        }
    }

    public Product findProductById(int id){
        return products.stream()
                .filter(p -> p.getId()==id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, id + " Not Found"));
    }

    public List<Product> findProductByCategoryName(String categoryName){
        return products.stream()
                .filter(p -> p.getCategoryName().equals(categoryName))
                .collect(Collectors.toList());
    }

    public List<Product> findAllProducts(){
        return this.products;
    }
}
