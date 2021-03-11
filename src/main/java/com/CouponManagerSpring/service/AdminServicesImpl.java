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
	
	public void addCompany(Company company){
		
		if(this.m_companyRepo.existsByEmail(company.getEmail())){
			System.out.println("Company's email already exists");
		}
		else if(this.m_companyRepo.existsByName(company.getName())){
			System.out.println("Company's name already exists");
		}
		else {
			this.m_companyRepo.save(company);
		}
	}
	public void updateCompany(Company company){
		Company tempCompany = this.m_companyRepo.findById(company.getID());
		Company tempCompany2 = this.m_companyRepo.findByName(company.getName()); 

		if(tempCompany== null || tempCompany2 == null){
			System.out.println("Invaild input -> Can't edit company Id");
		}
		else {
			if(!tempCompany.getName().equals(tempCompany2.getName()) || tempCompany.getID() != tempCompany2.getID()){
				System.out.println("Invaild input: u can't edit company's name/id");
			}
			else{
				this.m_companyRepo.save(company);
			}
		}
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
				for (int j = 0; j < customerVsCouponList.size(); j++){
					customerCoupons.add(m_couponRepo.findById(customerVsCouponList.get(j).getCoupounID()));
				}
						
				for (int j = 0; j < customerCoupons.size(); j++) {
					if(customerCoupons.get(j).getID() == couponID ) {
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
	
	public void addCustomer(Customer customer){
		if(m_customerRepo.existsByEmail(customer.getEmail())){
			System.out.println(customer.getEmail() + " already Exists");
		}
		else{
			this.m_customerRepo.save(customer);
		}
	}
	
	public void updateCustomer(Customer customer){
		this.m_customerRepo.save(customer);
	}
	
	public void deleteCustomer(int customerID){
		ArrayList<Coupon> coupons = this.getCustomerCoupons(customerID);
		//delete from customer_vs_coupon table
		if(coupons == null){
			System.out.println("no coupons purchase for : " + customerID);
		}
		else {
			for(Coupon var:coupons){
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
		for (int i = 0; i < customerVsCouponList.size(); i++){
				coupons.add(m_couponRepo.getOne(customerVsCouponList.get(i).getCoupounID()));
		}
		
		return coupons;
	}

}
