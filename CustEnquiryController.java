package com.example.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.CustEnquiry;
import com.example.entity.CustFollwup;
import com.example.entity.Employee;
import com.example.model.CustEnquiryModel;
import com.example.service.CustEnquiryService;
import com.example.service.CustFolloupService;
import com.example.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustEnquiryController {
	@Autowired
	ProductService productService;
	@Autowired
	CustEnquiryService custEnquiryService;
	@Autowired
	CustFolloupService custFolloupService;
	@GetMapping("/customerEnquiryPage")
	public String customerEnquiryPage(Model model,
			@RequestParam(name="redirct_success",required = false) String success,
			@RequestParam(name="redirct_error",required = false) String error

			) {
		
	List<String> list_coursenames=	productService.getAllCourseNameService();
	model.addAttribute("model_coursename_list", list_coursenames);
		model.addAttribute("modelCustEnquiryAttr", new CustEnquiryModel());
		model.addAttribute("model_success", success);
		model.addAttribute("model_error", error);
		return "customer-enquiry";
	}
     @PostMapping("/CustEnquiryForm")
     public String CustEnquiryForm(HttpSession session, @ModelAttribute("modelCustEnquiryAttr")CustEnquiryModel custEnquiryModel,
    		 RedirectAttributes  redirectAttributes
    		 
    		 ) {
    	 LocalDate date=LocalDate.now();
    	 String date1=date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    	 LocalTime time=LocalTime.now();
    	 String time1=time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    Employee employee=(Employee) session.getAttribute("session_employee");
    String empEmail="";
             if(employee!=null)
             {
            	 empEmail=employee.getEmail();
             }
          String phoneno= custEnquiryModel.getPhoneno();
          CustEnquiry custEnquiry=custEnquiryModel.getCustEnquiry();
             custEnquiry.setPhoneno(phoneno);
             custEnquiry.setEnquirydate(date1);
             custEnquiry.setEnquirytime(time1);
             custEnquiry.setEmpemail(empEmail);
         CustFollwup custFollwup=custEnquiryModel.getCustFollwup();
         custFollwup.setPhoneno(phoneno);
         
        boolean Status1= custEnquiryService.addCustEnquiryDetailsService(custEnquiry);
      boolean Status2= custFolloupService.addCustFolloupDateService(custFollwup);
        if(Status1 && Status2)
        {
        	redirectAttributes.addAttribute("redirct_success", "Enquiry Details added successfully");
        }
        else
        {
        	redirectAttributes.addAttribute("redirct_error", "Enquiry Details not added due to some error");

        }
    	 return "redirect:/customerEnquiryPage";
    	 
     }
     
     
     
	@GetMapping("/customerFollowupPage")
	public String customerFollowupPage(Model model,
			@RequestParam(name="selectedDate",required=false) String selectedDate ) {
		String date1;
		if(selectedDate!=null)
		{
			List<CustFollwup> list_followups=custFolloupService.getFollowupForProvidedDate(selectedDate);
			model.addAttribute("model_folloups", list_followups);
	
		}
		else
		{
			LocalDate ld= LocalDate.now();
			 date1=ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<CustFollwup> list_followups=custFolloupService.getFollowupForProvidedDate(date1);
		model.addAttribute("model_folloups", list_followups);
		}
		return "customer-followups";
	}
	
	@GetMapping("/checkPhonenumberAvalablity")
	@ResponseBody
	public String checkPhoneNo(@RequestParam("phoneNumber") String  phoneno) {
	boolean Status=custEnquiryService.isPhoneNumberExists(phoneno);
	     if(Status)
	     {
	    	return "Exist"; 
	     }
	     
		return "NotExist";
	}
	
	@GetMapping("/custEnquiryHistoryPage")
	public String opencustEnquiryHistoryPage(@RequestParam("phno")String phoneno,
			Model model){
	List<CustEnquiry> list_custenq=	custEnquiryService.getCustAllEnquiryHistory(phoneno);
	model.addAttribute("model_custenq", list_custenq);
	model.addAttribute("modelCustEnquiryAttr", new CustEnquiryModel());
	List<String> list_coursenames=	productService.getAllCourseNameService();
	model.addAttribute("model_coursename_list", list_coursenames);
		return "cusenq-history";
	}
	

}
