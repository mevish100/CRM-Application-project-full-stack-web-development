package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmpRepository;
@Service
public class EmpServiceImpl  implements EmpService{

	@Autowired
	EmpRepository empRepository;
	@Override
	public boolean addEmployeeService(Employee emp) {
		boolean Status=false;
		try {
			
		empRepository.save(emp);
		Status=true;
		
		}
		catch(Exception e) {
			Status=false;
		e.printStackTrace();
			
		}
		return Status;
	}
	@Override
    public List<Employee> getAllEmployeesService(){
    	
		return empRepository.findAll();
    	
    }
	@Override
    public Employee authenticate(String email) {
		Employee emp=   empRepository.findByEmail(email);
    	return emp;
    }
	@Override
	public boolean deleteEmployeeService(String email)
	{
		boolean Status=false;
		try 
		{
			empRepository.deleteByEmail(email);
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
	public boolean updateEmployeeService(Employee emp)
	{
		boolean Status=false;
	    try 
	    {
	    	Employee existingEmp=empRepository.findByEmail(emp.getEmail());
	    	if(existingEmp!=null)
	    	{
	    		existingEmp.setName(emp.getName());
	    		existingEmp.setPassword(emp.getPassword());
	    		existingEmp.setPhoneno(emp.getPhoneno());
	    		empRepository.save(existingEmp);
	    		Status=true;
	    	}
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	Status=false;
	    }
	
		return Status;
	}

}
