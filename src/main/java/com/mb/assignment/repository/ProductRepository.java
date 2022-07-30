package com.mb.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.assignment.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
