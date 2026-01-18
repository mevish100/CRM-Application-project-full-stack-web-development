package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.CustEnquiry;

import com.example.repository.CustEnquiryRepository;

@Service
public class CustEnquiryServiceImpl implements CustEnquiryService
{
     @Autowired
	CustEnquiryRepository custEnquiryRepository;
	@Override
	public boolean addCustEnquiryDetailsService(CustEnquiry custEnquiry) 
	
	{
		boolean Status=false;
		try
		{
			custEnquiryRepository.save(custEnquiry);
			Status=true;
		}
		catch(Exception e)
		{
			Status=false;
			e.printStackTrace();
		}
		return Status;
		
	}
	@Override
	public boolean isPhoneNumberExists(String phoneNumber) {
		return custEnquiryRepository.existsByPhoneno(phoneNumber);
	}
	
	@Override
	public List<CustEnquiry> getCustAllEnquiryHistory(String phoneno){
		return custEnquiryRepository.findAllByPhoneno(phoneno);
		
	}
	
	}





