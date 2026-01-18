package com.example.service;

import java.util.List;

import com.example.entity.CustEnquiry;

public interface CustEnquiryService {
	
	public boolean addCustEnquiryDetailsService(CustEnquiry custEnquiry);
	public boolean isPhoneNumberExists(String phoneNumber);
	public List<CustEnquiry> getCustAllEnquiryHistory(String phoneno);

}
