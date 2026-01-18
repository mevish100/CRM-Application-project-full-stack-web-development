package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository< Product, Integer> {
	
	Product findByCoursename(String coursename);
	@Query("SELECT coursename FROM Product")
	List<String> findCourseName();
	

}
