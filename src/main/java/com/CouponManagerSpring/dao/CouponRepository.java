package com.CouponManagerSpring.dao;


import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{
	
	public ArrayList<Coupon> findAllByCompanyId(int m_companyID);
	public Coupon findByCompanyIdAndTitle(int companyID,String couponTitle);
	
	/*
	public void addCoupon(Coupon coupon);
	public void updateCoupon(Coupon coupon);
	public void deleteCoupon(int couponID);
	public ArrayList<Coupon> getAllCoupons();
	public Coupon getOneCoupon(int couponID);
	public void addCopounPurchase(int customerID, int couponID);
	public void deleteCopounPurchase(int customerID, int couponID);
	public ResultSet getCustomerVsCouponTableByCouponID(int couponID);
	public void deleteCouponFromCustomerVsCouponTableByID(int couponID);
	public boolean isCouponExist(int id);
	public Coupon getOneCoupon(int companyID,String couponTitle);
	 */
	
}
