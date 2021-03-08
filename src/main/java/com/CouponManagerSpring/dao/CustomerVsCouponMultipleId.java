package com.CouponManagerSpring.dao;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomerVsCouponMultipleId implements Serializable{

	int couponId;
	int customerId;
	
	public CustomerVsCouponMultipleId () {}
	public CustomerVsCouponMultipleId (int customerId, int couponId)
	{
		this.couponId = couponId;
		this.customerId = customerId;
		
	}
	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
