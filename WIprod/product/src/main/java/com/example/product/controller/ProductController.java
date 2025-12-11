package com.example.product.controller;

import com.example.product.entity.Product;
import com.example.product.service.ProductService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET endpoint for users to view products
    @GetMapping()
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
}
