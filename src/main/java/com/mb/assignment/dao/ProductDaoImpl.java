package com.mb.assignment.dao;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mb.assignment.entity.Product;
import com.mb.assignment.enums.ErrorCode;
import com.mb.assignment.exception.CustomException;
import com.mb.assignment.repository.ProductRepository;


@Repository
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		try {
			return productRepository.findAll();
		}
		catch(Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}

	@Override
	public String addProduct(Product product) {
		try {
			productRepository.save(product);
			return "Product Added..";
		}
		catch(Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	
	@Override
	public Optional<Product> findProductById(Long id) {
		try {
			return productRepository.findById(id);
		}
		catch(Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}

}
