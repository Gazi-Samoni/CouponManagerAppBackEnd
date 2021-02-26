package com.CouponManagerSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.CouponManagerSpring.service.CompanyServicesImpl;

@RestController
public class CompanyController {

	@Autowired
	CompanyServicesImpl companyServices;

	
	//waiting for session 14
}
