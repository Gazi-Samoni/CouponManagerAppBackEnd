package com.CouponManagerSpring.dao;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomerVsCouponMultipleId implements Serializable{
	int coupounId;
	int customerId;
	
	public CustomerVsCouponMultipleId (int customerId, int couponId)
	{
		this.coupounId = couponId;
		this.customerId = customerId;
		
	}
}
