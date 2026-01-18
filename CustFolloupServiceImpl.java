package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.CustFollwup;
import com.example.repository.CustFolloupRpository;

@Service

public class CustFolloupServiceImpl implements CustFolloupService {
	
     @Autowired
     CustFolloupRpository custFolloupRpository;
	 
	@Override
	public boolean	addCustFolloupDateService( CustFollwup custFollwup) {
		boolean Status=false;
        try 
        {
        	CustFollwup	custFollwup_db=custFolloupRpository.findByPhoneno(custFollwup.getPhoneno());
            if(custFollwup_db!=null)
            {
            	custFollwup_db.setFollowupdate(custFollwup.getFollowupdate());
            	custFolloupRpository.save(custFollwup_db);
            }
            else
            {
            	custFolloupRpository.save(custFollwup);
            }
            Status=true;
        }
        catch(Exception e)
        {
            
        	e.printStackTrace();
        	Status=false;
        }
		return Status;
	}
	
	@Override
	public List<CustFollwup> getFollowupForProvidedDate(String date)
	{
		return custFolloupRpository.findByFollowupdate(date);
		
		
	}



}
