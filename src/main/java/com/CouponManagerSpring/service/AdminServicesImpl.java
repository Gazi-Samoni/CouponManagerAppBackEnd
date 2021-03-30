package com.CouponManagerSpring.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.CouponManagerSpring.dao.*;

@Service
public class AdminServicesImpl extends ClientService{
	
	
	public AdminServicesImpl() {}
	
	@Override
	public boolean login(String email, String password) {
		if(email.equals("admin@admin.com") && password.equals("admin"))
			return true;
		else 
			return false;
	}
	
	public Company addCompany(Company company){
		String result;
		if(this.m_companyRepo.existsByEmail(company.getEmail()))
		{
			result = "Company's email already exists";
			System.out.println(result);
		}
		else if(this.m_companyRepo.existsByName(company.getName()))
		{
			result = "Company's name already exists";
			System.out.println(result);
		}
		else 
		{
			result = "Comany added";
			return this.m_companyRepo.save(company);
		}
		return null;
	}
	public Company updateCompany(Company company){
		Company tempCompany = this.m_companyRepo.findById(company.getID());
		Company tempCompany2 = this.m_companyRepo.findByName(company.getName()); 
		String result;
		
		if(tempCompany== null || tempCompany2 == null){
			result = "Invaild input -> Can't edit company's Id / Name";
			System.out.println(result);
		}
		else {
			if(!tempCompany.getName().equals(tempCompany2.getName()) || tempCompany.getID() != tempCompany2.getID()){
				result = "Invaild input: u can't edit company's name/id";
				System.out.println(result);
			}
			else{
				result = "Comany Updated";
				return this.m_companyRepo.save(company);
			}
		}
		return null;
	}
	
	public void deleteCompany(int companyID){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>(this.m_couponRepo.findAllByCompanyId(companyID));
		deleteCouponsHistory(coupons);
		this.m_companyRepo.deleteById(companyID);

	}
	private void deleteCouponsHistory(ArrayList<Coupon> coupons) {
		Coupon coupon;
		
		while(!coupons.isEmpty()){
			coupon = coupons.get(0);
			deleteCouponFromCustomerHistory(coupon.getID());
			this.m_couponRepo.deleteById(coupon.getID());
			coupons.remove(0);
		}	
	}
	private void deleteCouponFromCustomerHistory(int couponID) {
		
		ArrayList<CustomersVsCoupons> customersVsCouponsList = (ArrayList<CustomersVsCoupons>)this.m_customersVScouponsRepo.findAll();

			for(int i =0 ; i< customersVsCouponsList.size() ; i++)
			{
				
				int customerID = customersVsCouponsList.get(i).getCustomerID();
				
				ArrayList<CustomersVsCoupons> customerVsCouponList = (ArrayList<CustomersVsCoupons>)m_customersVScouponsRepo.findByCustomerId(customerID);
				ArrayList<Coupon> customerCoupons = new ArrayList<Coupon>();
				for (int j = 0; j < customerVsCouponList.size(); j++)
				{
					customerCoupons.add(m_couponRepo.findById(customerVsCouponList.get(j).getCoupounID()));
				}
				
				//////
						
				for (int j = 0; j < customerCoupons.size(); j++) {
					
		
					if(customerCoupons.get(j).getID() == couponID ) 
					{
						customerCoupons.remove(j);
					}
				}
			}
			//Delete from customers_vs_coupons table			
			this.m_customersVScouponsRepo.deleteByCouponId(couponID);
	}

	public ArrayList<Company> getAllCompanies(){
		return new ArrayList<Company>(this.m_companyRepo.findAll());
	}
	
	public Company getOneCompany(int companyID){
		return this.m_companyRepo.findById(companyID);
	}
	
	public Customer addCustomer(Customer customer){
		String result= "Customer added";
		if(customer.getFirstName() =="" || customer.getLastName() =="" || customer.getEmail() =="" || customer.getPassword() =="")
		{
			result = "Invalid input";
			System.out.println(result);
		}
		else if(m_customerRepo.existsByEmail(customer.getEmail())){
			result = customer.getEmail() + " already Exists";
			System.out.println(result);
		}
		else{
			return this.m_customerRepo.save(customer);
			
		}
		return null;
	}
	
	public Customer updateCustomer(Customer customer){
		return this.m_customerRepo.save(customer);
	}
	
	public void deleteCustomer(int customerID){
		ArrayList<Coupon> coupons = this.getCustomerCoupons(customerID);
		//delete from customer_vs_coupon table
		if(coupons == null)
		{
			System.out.println("no coupons purchase for : " + customerID);
		}
		else {
			for(Coupon var:coupons)
			{
				this.m_customersVScouponsRepo.deleteByCouponId(var.getID());
			}
		}
		this.m_customerRepo.deleteById(customerID);
	}
	public ArrayList<Customer> getAllCustomer(){
		return (ArrayList<Customer>)this.m_customerRepo.findAll();
	}
	public Customer getOneCustomer(int customerID){
		return this.m_customerRepo.findById(customerID);
	}
	
	private ArrayList<Coupon> getCustomerCoupons(int customerId){
		
		ArrayList<CustomersVsCoupons> customerVsCouponList = (ArrayList<CustomersVsCoupons>)m_customersVScouponsRepo.findByCustomerId(customerId);
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		for (int i = 0; i < customerVsCouponList.size(); i++)
		{
				coupons.add(m_couponRepo.getOne(customerVsCouponList.get(i).getCoupounID()));
		}
		
		return coupons;
	}

}
