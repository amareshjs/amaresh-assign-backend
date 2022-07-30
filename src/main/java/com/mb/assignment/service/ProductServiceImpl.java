package com.mb.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mb.assignment.dao.ProductDao;
import com.mb.assignment.dto.ProductDto;
import com.mb.assignment.entity.Product;
import com.mb.assignment.mapper.Mapper;

@Service
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public List<Product> getAllProducts(){
		return productDao.findAll();
	}

	
	@Override
	public String addProduct(ProductDto productDto) {
		Product product=mapper.convert(productDto, Product.class);
		return productDao.addProduct(product);
	}


	@Override
	public Optional<Product> findById(Long Id) {
		return productDao.findProductById(Id);
	}
	
	
	
	
	
	
	
	
}
