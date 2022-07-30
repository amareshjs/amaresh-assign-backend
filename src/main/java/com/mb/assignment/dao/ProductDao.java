package com.mb.assignment.dao;

import java.util.List;
import java.util.Optional;

import com.mb.assignment.entity.Product;

public interface ProductDao{
	
	public List<Product> findAll();
	public String addProduct(Product product);
	public Optional<Product> findProductById(Long id);
}
