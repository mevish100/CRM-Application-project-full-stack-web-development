package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.CustEnquiry;
import java.util.List;


public interface CustEnquiryRepository extends JpaRepository<CustEnquiry,Integer>
{
         boolean existsByPhoneno(String phoneno);
         List<CustEnquiry> findAllByPhoneno(String phoneno);
}
