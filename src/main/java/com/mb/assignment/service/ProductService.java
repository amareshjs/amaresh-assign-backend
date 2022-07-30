package com.mb.assignment.service;

import java.util.List;
import java.util.Optional;

import com.mb.assignment.dto.ProductDto;
import com.mb.assignment.entity.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();
	public String addProduct(ProductDto productDto);
	public Optional<Product> findById(Long Id);

}
