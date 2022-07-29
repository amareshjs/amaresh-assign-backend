package com.mb.assignment.service;

import java.util.List;
import java.util.Optional;

import com.mb.assignment.entity.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();
	public String addProduct(Product product);
	public Optional<Product> findById(Long Id);

}
