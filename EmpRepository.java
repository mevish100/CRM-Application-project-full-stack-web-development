package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Employee;


@Transactional
public interface EmpRepository extends JpaRepository<Employee, Integer> {
	Employee  findByEmail(String email);
	void deleteByEmail(String email);

}
