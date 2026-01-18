package com.example.model;

import com.example.entity.CustEnquiry;
import com.example.entity.CustFollwup;

public class CustEnquiryModel {
	
	private String phoneno;
	CustEnquiry custEnquiry;
	CustFollwup custFollwup;
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public CustEnquiry getCustEnquiry() {
		return custEnquiry;
	}
	public void setCustEnquiry(CustEnquiry custEnquiry) {
		this.custEnquiry = custEnquiry;
	}
	public CustFollwup getCustFollwup() {
		return custFollwup;
	}
	public void setCustFollwup(CustFollwup custFollwup) {
		this.custFollwup = custFollwup;
	}
	
	

}
