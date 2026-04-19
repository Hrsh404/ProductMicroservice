package com.wishit.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishit.product.dto.ProductDTO;
import com.wishit.product.entity.Product;
import com.wishit.product.entity.ProductImage;
import com.wishit.product.repository.ProductImageRepository;
import com.wishit.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired ProductRepository productRepo; 
	@Autowired ProductImageRepository imageRepo;

	public Product createProduct(ProductDTO prodDTO){
		Product product = new Product();
        product.setName(prodDTO.getName());
        product.setDescription(prodDTO.getDescription());
        product.setPrice(prodDTO.getPrice());
        product.setStockQuantity(prodDTO.getStockQuantity());

        if(prodDTO.getImages().stream()!=null){
			List<ProductImage> images  = prodDTO.getImages().stream().map(imgIn ->{
        	ProductImage img = new ProductImage();
        	img.setImageUrl(imgIn.getUrl());
        	img.setProduct(product);
        	return img;
        }).toList();
            
        product.setImages(images);
		}
        // List<ProductImage> images  = prodDTO.getImages().stream().map(imgIn ->{
        // 	ProductImage img = new ProductImage();
        // 	img.setImageUrl(imgIn.getUrl());
        // 	img.setProduct(product);
        // 	return img;
        // }).toList();
            
        // product.setImages(images);
        return productRepo.save(product);
	}

	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	public Product getProduct(Long id){
		Optional<Product> productOpt = productRepo.findById(id);
		if(productOpt.isPresent())
		{
			return productOpt.get();
		}

		return null;
	}

    //----Vyas Verma------
	public String updateProduct(ProductDTO prd)
	{
		Optional<Product> existingProduct = productRepo.findById(prd.getId());
		if(!existingProduct.isPresent()){
			//throw new NoSuchProductExistsException("No Such Product Exists");
			return "No Such Product Exists";
		}
		else{
			existingProduct.get().setName(prd.getName());
			existingProduct.get().setDescription(prd.getDescription());
			existingProduct.get().setPrice(prd.getPrice());
			existingProduct.get().setStockQuantity(prd.getStockQuantity());
			//existingProduct.get().setImages(prd.getImages());
			productRepo.save(existingProduct.get());
			return "Record Updated Successfully";
		}

	}
}


// abstract class Exception extends RuntimeException{

// 	private String message;
// 	public Exception(){}
// 	public NoSuchProductExistsException(String msg)
// 	{
// 		super(msg);
// 		this.message=msg;
// 	}

// }