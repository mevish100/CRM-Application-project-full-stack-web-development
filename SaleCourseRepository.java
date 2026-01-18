package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.SaleCourse;

public interface SaleCourseRepository extends JpaRepository<SaleCourse, Integer> {
	

}
