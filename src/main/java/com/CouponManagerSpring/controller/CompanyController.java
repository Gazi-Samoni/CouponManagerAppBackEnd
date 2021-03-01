package com.CouponManagerSpring.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.CouponManagerSpring.dao.Category;
import com.CouponManagerSpring.dao.Company;
import com.CouponManagerSpring.dao.Coupon;
import com.CouponManagerSpring.service.CompanyServicesImpl;

@RestController
@RequestMapping("/company")
public class CompanyController {

	CompanyServicesImpl companyServices;

	@Autowired
	public CompanyController(CompanyServicesImpl companyServices)
	{
		this.companyServices = companyServices;
	}
	
	public boolean login(String email, String password) {
		return false;
		
		
	}
	@PostMapping("/add")
	public  ResponseEntity<?> addCoupon(@RequestBody Coupon coupon){
		companyServices.addCoupon(coupon);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PostMapping("/update")
	public  ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon){
		companyServices.addCoupon(coupon);
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	@DeleteMapping("/delete/{id}")
	public  ResponseEntity<?> deleteCoupon(@PathVariable("id")int couponID){
		companyServices.deleteCoupon(couponID);
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
	@GetMapping("/coupons")
	public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(){
		ArrayList<Coupon> coupons = companyServices.getCompanyCoupons();
		return new ResponseEntity<>(coupons, HttpStatus.OK);
	}
	
	@GetMapping("/coupons/{category}")
	public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(@RequestBody Category category){
		ArrayList<Coupon> coupons = companyServices.getCompanyCoupons(category);
		return new ResponseEntity<>(coupons, HttpStatus.OK);
	}
	
	@GetMapping("/coupons/{price}")
	public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(@PathVariable("price") double maxPrice){
		ArrayList<Coupon> coupons = companyServices.getCompanyCoupons(maxPrice);
		return new ResponseEntity<>(coupons, HttpStatus.OK);
	}

	
	@GetMapping("/details")
	public ResponseEntity<Company> getCompanyDetails (){
		Company company = companyServices.getCompanyDetails();
		return new ResponseEntity<>(company, HttpStatus.OK);
	}
	

}
