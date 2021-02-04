package com.CouponManagerSpring.service;
import org.springframework.beans.factory.annotation.Autowired;
import com.CouponManagerSpring.dao.*;

public class CompanyServicesImpl implements CompanyServices{
	
	@Autowired
	CompanyRepository repo;
	public void addCompany(Company company) {
		if(!repo.existsByEmailAndPassword(company.getEmail(), company.getPassword()))
		{
			repo.save(company);
			System.out.println("added.");
		}
	}
}
