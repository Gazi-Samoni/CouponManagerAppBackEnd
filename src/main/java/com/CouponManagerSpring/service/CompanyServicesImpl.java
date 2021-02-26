package com.CouponManagerSpring.service;

import java.util.ArrayList;


import org.springframework.stereotype.Service;

import com.CouponManagerSpring.dao.*;

@Service
public class CompanyServicesImpl extends ClientService{
	
	private int m_companyID;

	public CompanyServicesImpl() {} 
	
	public CompanyServicesImpl(int companyID)
	{
		m_companyID = companyID;
	}
	
	@Override
	public boolean login(String email, String password) {
		
		return m_companyRepo.existsByEmailAndPassword(email, password);
	}
	public void addCoupon(Coupon coupon){
		if(coupon.getCompanyID() == m_companyID){
			if(isCouponTitleExists(coupon)==false){
				m_couponRepo.save(coupon);
			}
			else{
				System.out.println(coupon.getTitle() + "`s title already exists");
			}
		}
		else{
			System.out.println(coupon.getTitle() + " This coupon doesn`t belong to our company");
		}
	}
	private boolean isCouponTitleExists(Coupon coupon){
		ArrayList<Coupon> coupons = getCompanyCoupons();
		boolean isExists= false;
		for(Coupon var:coupons)
		{
			if(var.getTitle().equals(coupon.getTitle()))
			{
				isExists=true;
			}
		}
		return isExists;
		
	}
	public void updateCoupon(Coupon coupon){
		
		if(coupon.getCompanyID() != this.m_companyID){
			System.out.println("Can't change Company id");
		}
		else if(!m_couponRepo.existsById(coupon.getID())){
			System.out.println("invalid coupon ID");
		}
		else{
			m_couponRepo.save(coupon);
			System.out.println("coupon updated.");
		}
	}
	
	public void deleteCoupon(int couponID){
		
		ArrayList<CustomersVsCoupons> customerVsCouponTable = (ArrayList<CustomersVsCoupons>)m_customersVScouponsRepo.findByCouponId(couponID);
		
		
		for(int i = 0; i < customerVsCouponTable.size(); i++)
		{
			int currCouponID = customerVsCouponTable.get(i).getCoupounID();
			if(currCouponID == couponID)
			{
				m_customersVScouponsRepo.deleteByCouponId(currCouponID);
			}
		}
	
		ArrayList<Coupon> coupons = m_companyRepo.getOne(m_companyID).getCoupons();
		
		for(Coupon var: coupons)
		{
			if(var.getID() == couponID)
			{
				coupons.remove(var);
				System.out.println("coupon : "+ couponID +" removed");
			}
		}
		
		m_couponRepo.deleteById(couponID);
	}
	
	
	public ArrayList<Coupon> getCompanyCoupons(){
		return m_couponRepo.findAllByCompanyId(this.m_companyID);
	}
	
	
	public ArrayList<Coupon> getCompanyCoupons(Category category){
		ArrayList<Coupon> tempCoupons = getCompanyCoupons();
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		for(Coupon var:tempCoupons)
		{
			if(var.getCategory() == category)
			{
				coupons.add(var);
			}
		}
		return coupons;
	}
	public ArrayList<Coupon> getCompanyCoupons(double maxPrice){
		ArrayList<Coupon> tempCoupons = getCompanyCoupons();
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		for(Coupon var:tempCoupons)
		{
			if(var.getPrice() <= maxPrice)
			{
				coupons.add(var);
			}
		}
		return coupons;
	}
	public Company getCompanyDetails(){
		return m_companyRepo.getOne(this.m_companyID);
	}	

	public void setID(int companyID) {
		m_companyID = companyID;
	}
	public int getCompanyID() {
		return m_companyID;
	}
	public int getCompanyIdByEmailAndPassword(String email, String password) {
		return m_companyRepo.findByEmailAndName(email, password).getID();
	}
}
