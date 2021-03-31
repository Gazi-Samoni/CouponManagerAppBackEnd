package com.CouponManagerSpring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.CouponManagerSpring.dao.*;

@Service
public class CustomerServicesImpl extends ClientService{
	
	private int m_customerID;
	
	public CustomerServicesImpl() {}
	
	public CustomerServicesImpl(int customerID) { this.m_customerID = customerID;}
	
	
	@Override
	public boolean login(String email, String password) {
		boolean isExsist =m_customerRepo.existsByEmailAndPassword(email,password);
		if(isExsist) {
			this.m_customerID = m_customerRepo.findByEmailAndPassword(email, password).getId();	
		}
		return isExsist;
	}
	
	public String purchaseCoupon(Coupon coupon)
	{
		boolean purchased = false;
		String result="purchased";
		ArrayList<Coupon> coupons = getCustomerCoupons();
		
		for (int i = 0; i < coupons.size(); i++) {
		
			if(coupons.get(i).getID() == coupon.getID())
			{
				purchased =true;
				result = "You already purchased this coupon";
				System.out.println(result);
			}
		}
			
		//if not purchased
		if(!purchased)
		{
			if(coupon.getAmount()>0)
			{
				if(isVaildCouponDate(coupon.getEndDate()))
				{
					CustomersVsCoupons tosave = new CustomersVsCoupons( coupon.getID(),this.m_customerID);
					m_customersVScouponsRepo.saveAndFlush(tosave);
					coupon.setAmount(coupon.getAmount()-1);
					m_couponRepo.save(coupon);
					//m_customerRepo.findById(this.m_customerID).getCoupons().add(coupon);
				}
				else
				{
					result = "Coupon Expired date";
					System.out.println(result);
				}
			}
			else
			{
				result = coupon.getTitle() + " sold out :(";
				System.out.println(result);
			}
		}
		return result;
	}
	
	private boolean isVaildCouponDate(Date endDate) {

		long millis=System.currentTimeMillis(); 
		java.sql.Date NowDate = new java.sql.Date(millis);
				
		return endDate.after(NowDate);
	}
	
	public ArrayList<Coupon> getCustomerCoupons(){
		
		ArrayList<CustomersVsCoupons> customerVsCouponList = (ArrayList<CustomersVsCoupons>)m_customersVScouponsRepo.findByCustomerId(this.m_customerID);
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		for (int i = 0; i < customerVsCouponList.size(); i++)
		{
				coupons.add(m_couponRepo.findById(customerVsCouponList.get(i).getCoupounID()));
		}
		
		return coupons;
	}
	
	public ArrayList<Coupon> getCustomerCoupons(Category category){
		ArrayList<Coupon> coupons = getCustomerCoupons();
		
		for (int i = 0; i < coupons.size(); i++) {
			if(coupons.get(i).getCategory() != category)
			{
				coupons.remove(i);
				i--;
			}
		}
		return coupons;
	}
	public ArrayList<Coupon> getCustomerCoupons(double maxPrice){
		ArrayList<Coupon> coupons = getCustomerCoupons();
		for (int i = 0; i < coupons.size(); i++)
		{
			if(coupons.get(i).getPrice() > maxPrice)
			{
				coupons.remove(i);
				i--;
			}
		}
		return coupons;
	}
	public Customer getCustomerDetails(){
		Customer customer= m_customerRepo.findById(this.m_customerID);
		
		Set<Coupon> toSet = new HashSet<Coupon>(this.getCustomerCoupons());
		customer.setCoupons(toSet);
	
		return customer;
			
	}
	
	public int getCustomerIdByEmailAndPassword(String email, String password){
		return m_customerRepo.findByEmailAndPassword(email, password).getId();
	}
	
	public Coupon getOneCoupon(int companyID,String couponTitle) {
		return m_couponRepo.findByCompanyIdAndTitle(companyID, couponTitle);
	}

	public void setId(int customerID) { m_customerID = customerID; }
	
	public int getId() { return m_customerID; }

	public ArrayList<Coupon> getAllCoupons() {
		return (ArrayList<Coupon>) m_couponRepo.findAll();
	}

	public ArrayList<Coupon> getAllCoupons(double maxPrice) {
		ArrayList<Coupon> coupons = this.getAllCoupons();
		
		for(int i = 0; i <coupons.size(); i++)
		{
			if(coupons.get(i).getPrice() > maxPrice)
			{
				coupons.remove(i);
				i--;
			}
		}
		
		return coupons;
	}

	public ArrayList<Coupon> getAllCoupons(Category category) {
		ArrayList<Coupon> coupons = this.getAllCoupons();
		
		for(int i = 0; i <coupons.size(); i++)
		{
			if(coupons.get(i).getCategory() !=  category)
			{
				coupons.remove(i);
				i--;
			}
		}
		
		return coupons;
	}
}
