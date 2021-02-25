package com.CouponManagerSpring.service;
import org.springframework.beans.factory.annotation.Autowired;

import com.CouponManagerSpring.dao.*;


public abstract class ClientService {
	@Autowired
	CustomerRepository m_customerRepo;
	@Autowired
	CompanyRepository m_companyRepo;
	@Autowired
	CouponRepository m_couponRepo;
	@Autowired
	CustomersVsCouponsRepository m_customersVScouponsRepo;
	
	
	public CustomerRepository getCustomerRepo() {
		return m_customerRepo;
	}

	public CustomersVsCouponsRepository getCustomersVsCouponsRepo() {
		return m_customersVScouponsRepo;
	}

	public CompanyRepository getCompanyRepo() {
		return m_companyRepo;
	}


	public CouponRepository getCouponRepo() {
		return m_couponRepo;
	}
	
	
	public abstract boolean login(String email, String password);
	
}
