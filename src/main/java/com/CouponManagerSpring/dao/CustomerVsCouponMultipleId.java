package com.CouponManagerSpring.dao;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomerVsCouponMultipleId implements Serializable{
	int couponId;
	int customerId;
	
	public CustomerVsCouponMultipleId (int customerId, int couponId)
	{
		this.couponId = couponId;
		this.customerId = customerId;
		
	}
}
