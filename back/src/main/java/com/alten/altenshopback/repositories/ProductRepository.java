package com.alten.altenshopback.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alten.altenshopback.models.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {
	
	@Query("select product from Product product where product.code=:code")
	public Optional<Product> findByCode(String code); 

}
