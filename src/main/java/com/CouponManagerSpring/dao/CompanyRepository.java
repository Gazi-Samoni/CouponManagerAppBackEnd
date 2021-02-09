package com.CouponManagerSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	/*
	//public boolean isCompanyExists(String email, String password);
	//boolean existsByEmailAndPassword(String email, String password);
	//public void addCompany(Company company);
	//we will use save
	//public void updateCompany(Company company);
	// we will use save for upadte	
	/*public void deleteCompany(int companyID);
	public ArrayList<Company> getAllCompanies();
	public Company getOneCompany(int companyID);
	public ArrayList<Coupon> getAllCouponsByCompanyID(int companyID);
	public boolean isEmailExists(String email);
	public boolean isNameExists(String name);
	public Company getOneCompanyByName(String name);
	public int getCompanyIdByEmailAndPassword(String email, String password);
	*/
}
