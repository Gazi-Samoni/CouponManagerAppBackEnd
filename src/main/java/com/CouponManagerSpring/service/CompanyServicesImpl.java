package com.CouponManagerSpring.service;
import org.springframework.stereotype.Service;

import com.CouponManagerSpring.dao.*;

@Service
public class CompanyServicesImpl extends ClientService{
	
	public void addCompany(Company company) {
		m_companyRepo.save(company);
		System.out.println("added.");
		/*
		if(!repo.existsByEmailAndPassword(company.getEmail(), company.getPassword()))
		{
			repo.save(company);
			System.out.println("added.");
		}
		*/
	}
	
}
