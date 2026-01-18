package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Product;
import com.example.entity.SaleCourse;
import com.example.repository.ProductRepository;
import com.example.repository.SaleCourseRepository;

@Service
public class ProductServiceImpl implements ProductService
{         @Autowired
	    ProductRepository productRepository;

          @Autowired
          SaleCourseRepository saleCourseRepository;
	
	         @Override
            public  boolean addProductService(Product product)
            {
	        	 boolean Status=false;
	        	 try 
	        	 {
	        		productRepository.save(product) ;
	        		Status=true;
	        	 }
	        	 catch(Exception e)
	        	 {
	        		 e.printStackTrace();
	        	 }
	        	 
				return Status;
				
			}
	         @Override
	         public List<Product>getAllProductsListService(){
				return productRepository.findAll();
	        	 
	         }
	         @Override
	     	public Product getProductDetailsService(String coursename) {
	     		return productRepository.findByCoursename(coursename);
	     	}
	         @Override
	     	public List<String> getAllCourseNameService(){
	     		return productRepository.findCourseName();
	     	}
	         
	         @Override
	     	public Product getSelectedCourseDetailsService(String coursename)
	     	{
	        	 return productRepository.findByCoursename(coursename);
	     	}
	         @Override
	     	public boolean addCourseDetailsService(SaleCourse saleCourse) {
	        	 boolean Status=false;
	        	 try 
	        	 {
	        		 saleCourseRepository.save(saleCourse);
	        		 Status=false;
	        	 }
	        	 catch(Exception e)
	        	 {
	        		 Status=false;
	        		 e.printStackTrace();
	        	 }
				return Status;
	     		
	     	}






}
