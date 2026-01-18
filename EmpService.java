package com.example.service;

import java.util.List;

import com.example.entity.Employee;

public interface EmpService 
{
	    public Employee authenticate(String email);
        public boolean addEmployeeService(Employee emp);
        public List<Employee> getAllEmployeesService();
        public boolean deleteEmployeeService(String email);
        public boolean updateEmployeeService(Employee emp);
}
