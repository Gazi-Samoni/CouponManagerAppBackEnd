package com.CouponManagerSpring.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Query("select cop from Coupon cop where cop.id in (select cop.id from cop.customers")
	public Coupon findCustomerCoupon(int customerID, int couponID);
	
	public boolean existsByEmailAndPassword(String email, String password);
	public boolean existsByEmail(String email);
	public Customer findByEmailAndPassword(String email, String password);
}
