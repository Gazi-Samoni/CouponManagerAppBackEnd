package com.CouponManagerSpring.dao;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(CustomerVsCouponMultipleId.class)
@Table(name="CUSTOMERS_VS_COUPONS")
public class CustomersVsCoupons {
	// we should check if we have to make a category class to put it also in the database..
	@Id
	@Column(name="CouponId")
	int couponId;
	
	@Id
	@Column(name="CustomerId")
	int customerId;
	
	public CustomersVsCoupons() {}
	public CustomersVsCoupons(int couponId, int customerId) {
		this.couponId = couponId;
		this.customerId = customerId;
	}
	
	
	public int getCoupounID() {
		return couponId;
	}

	public void setCoupounID(int coupounID) {
		this.couponId = coupounID;
	}

	public int getCustomerID() {
		return customerId;
	}

	public void setCustomerID(int customerID) {
		this.customerId = customerID;
	}
	
	
	
}
