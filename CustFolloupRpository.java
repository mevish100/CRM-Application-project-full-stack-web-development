package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.CustFollwup;
import java.util.List;


public interface CustFolloupRpository extends JpaRepository<CustFollwup, Integer> {
	
	CustFollwup findByPhoneno(String phoneno);
	List<CustFollwup> findByFollowupdate(String followupdate);

}
