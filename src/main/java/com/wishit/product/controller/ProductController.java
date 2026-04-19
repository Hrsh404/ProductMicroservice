package com.wishit.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishit.product.dto.ProductDTO;
import com.wishit.product.entity.Product;
import com.wishit.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired private ProductService prodServ;
	
	@PostMapping("/add_product")
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO prdoDTO){
		//System.out.println(prdoDTO);
		try {
			Product savedProd = prodServ.createProduct(prdoDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedProd);
		} catch(Exception ex) {
			ex.printStackTrace();
			if (ex instanceof IllegalArgumentException) {
	            return ResponseEntity.badRequest()
	                    .body(Map.of("error", ex.getMessage()));
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(Map.of("error", ex.getMessage()));
	        }
		}
	}

	@GetMapping("/")
    public List<Product> getAllProducts() {
        return prodServ.getAllProducts();
    }

	//----Vyas Verma------
	@GetMapping("/id/{id}")
	public Product getProduct(@PathVariable Long id){
		return prodServ.getProduct(id);
	}

	//----Vyas Verma------
	@PutMapping("/updateProduct")
	// public String updateProduct(@RequestBody ProductDTO prdoDTO)
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO prdoDTO)
	{
	    try {
	        String message = prodServ.updateProduct(prdoDTO);
	        return ResponseEntity.ok(Map.of("message", message));
	    } 
	    catch (Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", ex.getMessage()));
	    }
	}


 }


