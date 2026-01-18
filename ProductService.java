package com.example.service;

import java.util.List;

import com.example.entity.Product;
import com.example.entity.SaleCourse;


public interface ProductService {
	public  boolean addProductService(Product product);
	public List<Product>getAllProductsListService();
	public Product getProductDetailsService(String coursename);
	public List<String> getAllCourseNameService();
	public Product getSelectedCourseDetailsService(String coursename);
	public boolean addCourseDetailsService(SaleCourse saleCourse);
}
