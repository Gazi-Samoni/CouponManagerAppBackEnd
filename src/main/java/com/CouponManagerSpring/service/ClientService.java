package com.CouponManagerSpring.service;
import org.springframework.beans.factory.annotation.Autowired;

import com.CouponManagerSpring.dao.*;


public abstract class ClientService {
	@Autowired
	CompanyRepository m_companyRepo;
	
	public abstract void addCompany(Company company);
	
}
