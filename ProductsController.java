package com.example.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Employee;
import com.example.entity.Product;
import com.example.entity.SaleCourse;
import com.example.service.ProductService;
import com.example.urls.OthersUrls;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductsController {
	@Autowired
	 ProductService productService;

	@GetMapping("/addProducts")
	public String openAddProductsPage(
			Model model,
			@RequestParam(name = "redirect_attr_success",required = false) String success,
			@RequestParam(name = "redirect_attr_error",required = false) String error
			
			) {
		model.addAttribute("productAttr",new Product());
		model.addAttribute("model_success", success);
		model.addAttribute("model_error", error);
		return "add-products";
	}
	@PostMapping("/addCourseForm")
	public String addCourseDetails(
			@ModelAttribute("productAttr") Product product,
			@RequestParam("courseimage") MultipartFile courseImage,
			@RequestParam("trainersimage") MultipartFile trainerImage,
			RedirectAttributes redirectAttributes
			) {
	boolean Status1=saveImage(courseImage);
	if(!Status1)
	{
		System.out.println("Course image not uploaded due to some error");
	}
	boolean Status2=saveImage(courseImage);
	if(!Status2)
	{
		System.out.println("trainers image not uploaded due to some error");
	}
	
	boolean Status=productService.addProductService(product);
	if(Status)
	{
		redirectAttributes.addAttribute("redirect_attr_success", "Product added successfully");
	}
	else
	{
		redirectAttributes.addAttribute("redirect_attr_error", "Product  not added successfully");
	}
	return "redirect:/addProducts";
	
	}
	
	private boolean saveImage(MultipartFile file) 
	{
		boolean Status=false;
		try 
		{
			String fileName= file.getOriginalFilename();
		Path filepath=Paths.get(OthersUrls.IMAGE_UPLOAD_Folder,fileName);
	    Files.copy(file.getInputStream(),filepath, StandardCopyOption.REPLACE_EXISTING);
	         Status=true;
		}
		catch(Exception e)
		{
			 Status=false;
		  e.printStackTrace();	
		 
		}
		
		return Status;
	}
	@GetMapping("/productsList")
	public String openProductsListPage(Model model) {
	List<Product> list_products=productService.getAllProductsListService();
	model.addAttribute("model_products_list", list_products);
		return "products-list";
	}
	
	@GetMapping("/courseDetails")
	public String openCourseDetailsPage(@RequestParam("courseName") String coursename ,
			Model model
			) {
	Product product=productService.getProductDetailsService(coursename);
	model.addAttribute("model_product", product);
		return "course-deatails";
	}
	
	@GetMapping("/saleCourse")
	
	public String openSaleCoursePage(Model model,
			@RequestParam(name="redirectAttr_success",required = false) String success,
			@RequestParam(name="redirectAttr_error",required = false) String error) {
		List<String> list_coursenames=	productService.getAllCourseNameService();
		model.addAttribute("model_coursename_list", list_coursenames);
		
		model.addAttribute("modelSaleCourseAttr", new SaleCourse());
		
		model.addAttribute("model_success",success);
		model.addAttribute("model_error",error);
		return "sale-course";
		
	}
	@PostMapping("/saleCourseForm")
	public String saleCourseForm(HttpSession session,@ModelAttribute("modelSaleCourseAttr") SaleCourse saleCourse,RedirectAttributes redirectAttributes)
	{
	Employee employee = (Employee)session.getAttribute("session_employee");
	String empemail= employee.getEmail();
	
	LocalDate ld=LocalDate.now();
	String date1=ld.format(DateTimeFormatter.ofPattern("dd/MM/yyy"));
	
	LocalTime lt=LocalTime.now();
	String time1=lt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	
	saleCourse.setEmpemailid(empemail);
	saleCourse.setDate(date1);
	saleCourse.setTime(time1);
	
	boolean Status=productService.addCourseDetailsService(saleCourse);
	if(Status)
	{
		redirectAttributes.addAttribute("redirectAttr_success", "Course sale successfully");
	}
	else
	{
		redirectAttributes.addAttribute("redirectAttr_error", "Course sale not due to some error");

	}
		return "redirect:/saleCourse";
		
	}
	
	
	@GetMapping("/getCoursePrices")
	@ResponseBody
	public Product getCoursePrices(@RequestParam("selectedcourse") String selectedcourse) 
	{
		return productService.getSelectedCourseDetailsService(selectedcourse);

	}
	
	
}
