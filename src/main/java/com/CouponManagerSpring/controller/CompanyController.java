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
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<Boolean> login(@PathVariable("email")String email, @PathVariable("password")String password) {
		
		if(companyServices.login(email, password)){
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
		else	
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/coupon/add")
	public  ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupon){
		Coupon NewCoupon = companyServices.addCoupon(coupon);
		if(NewCoupon != null)
			return new ResponseEntity<>(NewCoupon,HttpStatus.CREATED);
		else
			return new ResponseEntity<>(NewCoupon,HttpStatus.IM_USED);
	}
	
	@PutMapping("/coupon/update")
	public  ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon coupon){
		Coupon NewCoupon = companyServices.updateCoupon(coupon);
		if(NewCoupon != null)
			return new ResponseEntity<>(NewCoupon,HttpStatus.OK);	
		else
			return new ResponseEntity<>(NewCoupon,HttpStatus.BAD_REQUEST);	
	}

	@DeleteMapping("/coupon/delete/{id}")
	public  ResponseEntity<?> deleteCoupon(@PathVariable("id")int couponID){
		String status =companyServices.deleteCoupon(couponID);
		if(status.contains("removed"))
			return new ResponseEntity<>(HttpStatus.OK);	
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
	}
	
	@GetMapping("/coupons")
	public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>(companyServices.getCompanyCoupons());
		return new ResponseEntity<>(coupons, HttpStatus.OK);
	}

	@GetMapping("/coupons/category/{category}")
	public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(@PathVariable("category") Category category){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>(companyServices.getCompanyCoupons(category));
		return new ResponseEntity<>(coupons, HttpStatus.OK);
	}

	@GetMapping("/coupons/price/{price}")
	public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(@PathVariable("price")double maxPrice){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>(companyServices.getCompanyCoupons(maxPrice));
		return new ResponseEntity<>(coupons, HttpStatus.OK);
	}
	@GetMapping("/details")
	public ResponseEntity<Company> getCompanyDetails (){
		Company company = companyServices.getCompanyDetails();
		return new ResponseEntity<>(company, HttpStatus.OK);
	}
	

}
