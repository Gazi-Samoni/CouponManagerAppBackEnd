package com.CouponManagerSpring.dao;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{
	
	public Set<Coupon> findAllByCompanyId(int m_companyID);
	public Coupon findByCompanyIdAndTitle(int companyID,String couponTitle);
	public Coupon findById(int id);
	
}
