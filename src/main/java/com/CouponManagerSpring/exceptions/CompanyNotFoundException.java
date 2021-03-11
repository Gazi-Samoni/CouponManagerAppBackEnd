package com.CouponManagerSpring.exceptions;

@SuppressWarnings("serial")
public class CompanyNotFoundException extends RuntimeException {

	public CompanyNotFoundException(String message)
	{
		super(message);
	}
}
