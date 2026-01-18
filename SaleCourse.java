package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sale_course")
public class SaleCourse {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="cust_phoneno")
	private String custphoneno;
	@Column(name="emp_emailid")
	private String empemailid;
	@Column(name="purchased_date")
	private String date;
	@Column(name="course_name")
	private String coursename;
	@Column(name="purchased_time")
	private String time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustphoneno() {
		return custphoneno;
	}
	public void setCustphoneno(String custphoneno) {
		this.custphoneno = custphoneno;
	}
	public String getEmpemailid() {
		return empemailid;
	}
	public void setEmpemailid(String empemailid) {
		this.empemailid = empemailid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	
	
	
	

}
