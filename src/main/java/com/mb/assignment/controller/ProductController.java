package com.mb.assignment.controller;
import static com.mb.assignment.constants.UrlMapping.PRODUCT_BASE_URL;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mb.assignment.dto.ProductDto;
import com.mb.assignment.entity.Product;

import com.mb.assignment.service.ProductService;

@RestController
@RequestMapping(PRODUCT_BASE_URL)
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping()
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping()
	public String addProduct(@Valid @RequestBody ProductDto productDto) {
		return productService.addProduct(productDto);
	}

}