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
	int coupounId;
	
	@Id
	@Column(name="CustomerId")
	int customerId;
	
	
	public int getCoupounID() {
		return coupounId;
	}

	public void setCoupounID(int coupounID) {
		this.coupounId = coupounID;
	}

	public int getCustomerID() {
		return customerId;
	}

	public void setCustomerID(int customerID) {
		this.customerId = customerID;
	}
	
}
