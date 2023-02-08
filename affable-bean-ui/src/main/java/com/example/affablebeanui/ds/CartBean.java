package com.example.affablebeanui.ds;

import com.example.affablebeanui.dto.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Getter
public class CartBean {

    private Set<Product> cart = new HashSet<>();

    public void addToCart(Product product){
        cart.add(product);
    }

    public void removeFromCart(Product product){
        cart.remove(product);
    }

    public void clearCart(){
        cart.clear();
    }
}
