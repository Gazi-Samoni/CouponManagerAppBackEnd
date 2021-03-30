package com.CouponManagerSpring.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.CouponManagerSpring.dao.*;

@Service
public class CompanyServicesImpl extends ClientService{
	
	private int m_companyID;

	public CompanyServicesImpl() {} 
	
	public CompanyServicesImpl(int companyID){
		m_companyID = companyID;
	}
	
	@Override
	public boolean login(String email, String password) {
	
		Company company = m_companyRepo.findByEmailAndPassword(email, password);
		if(company != null) {
			this.m_companyID = company.getID();	
			return true;
		}
		return false;
	}
	public Coupon addCoupon(Coupon coupon){
		String result; 
		if(coupon.getCompanyID() == m_companyID){
			if(isCouponTitleExists(coupon)==false){
				
				System.out.println("Coupon added :)");
				result ="Coupon added";
				return m_couponRepo.save(coupon);
			}
			else{
				result = coupon.getTitle() + "`s title already exists";
				System.out.println(result);
			}
		}
		else{
			result = coupon.getTitle() + " This coupon doesn`t belong to our company";
			System.out.println(result);
		}
		return null;
	}
	
	private boolean isCouponTitleExists(Coupon coupon){
		ArrayList<Coupon> coupons = new ArrayList<>(getCompanyCoupons());
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
	public Coupon updateCoupon(Coupon coupon){
		String result; 
		if(coupon.getCompanyID() != this.m_companyID){
			result = "Can't edit Company id";
			System.out.println(result);
		}
		else if(!m_couponRepo.existsById(coupon.getID())){
			result ="invalid coupon ID";
			System.out.println(result);
		}
		else{
			result = "coupon updated";
			System.out.println(result);
			return m_couponRepo.save(coupon);
		}
		return null;
	}
	
	public String deleteCoupon(int couponID){
		String result="";
		ArrayList<CustomersVsCoupons> customerVsCouponTable = (ArrayList<CustomersVsCoupons>)m_customersVScouponsRepo.findByCouponId(couponID);
		
		
		for(int i = 0; i < customerVsCouponTable.size(); i++)
		{
			int currCouponID = customerVsCouponTable.get(i).getCoupounID();
			if(currCouponID == couponID)
			{
				m_customersVScouponsRepo.deleteByCouponId(currCouponID);
			}
		}
	
		ArrayList<Coupon> coupons = this.getCompanyCoupons();
		
		for(int i= 0; i< coupons.size();i++)
		{
			//need to add validation
			if(coupons.get(i).getCompanyID() == this.getCompanyID())
				if(coupons.get(i).getID() == couponID)
				{
					coupons.remove(i);
					result = "coupon : "+ couponID +" removed";
					System.out.println(result);
				}
		}
		
		m_couponRepo.deleteById(couponID);
		return result;
	}
	
	
	public ArrayList<Coupon> getCompanyCoupons(){
		return new ArrayList<Coupon>(m_couponRepo.findAllByCompanyId(this.m_companyID));
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
		return m_companyRepo.findById(this.m_companyID);
	}	

	public void setID(int companyID) {
		m_companyID = companyID;
	}
	public int getCompanyID() {
		return m_companyID;
	}
	public int getCompanyIdByEmailAndPassword(String email, String password) {
		try {
			return m_companyRepo.findByEmailAndPassword(email, password).getID();
		}
		catch(Exception e)
		{
				int currentLine = e.getStackTrace()[0].getLineNumber();
				String className = this.getClass().getName();
				System.out.println(" EXCEPTION !!!! - Class : " + className + " Line: " + currentLine + " Reason:  Cannot find the company with the given email and password");
				return 0;
		}
		
	}
}
