package com.example.service;


import java.util.List;

import com.example.entity.CustFollwup;

public interface CustFolloupService {
	
public boolean	addCustFolloupDateService( CustFollwup custFollwup);
public List<CustFollwup> getFollowupForProvidedDate(String selectedDate);

}
