package com.CouponManagerSpring.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CouponManagerSpring.dao.Category;
import com.CouponManagerSpring.dao.Coupon;
import com.CouponManagerSpring.dao.Customer;
import com.CouponManagerSpring.service.CustomerServicesImpl;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	CustomerServicesImpl customerServices;
	
	@Autowired
	public CustomerController(CustomerServicesImpl customerServices)
	{
		this.customerServices = customerServices;
	}
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<Boolean> login(@PathVariable("email")String email, @PathVariable("password")String password) {
		
		if(customerServices.login(email, password))
			return new ResponseEntity<>(true,HttpStatus.OK);
		else	
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/purchaseCoupon")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon){
		String status = customerServices.purchaseCoupon(coupon);
		if(status.equals("purchased"))
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
	}
	
	@GetMapping("/coupons")
	public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons(){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>(customerServices.getCustomerCoupons());
		return new ResponseEntity<>(coupons, HttpStatus.OK);
	}
	
	@GetMapping("/coupons/category/{category}")
	public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons(@PathVariable("category") Category category){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>(customerServices.getCustomerCoupons(category));
		return new ResponseEntity<>(coupons, HttpStatus.OK);
	}
	
	@GetMapping("/coupons/price/{price}")
	public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons(@PathVariable("price")double maxPrice){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>(customerServices.getCustomerCoupons(maxPrice));
		return new ResponseEntity<>(coupons, HttpStatus.OK);
	}
	
	@GetMapping("/details")
	public ResponseEntity<Customer> getCustomerDetails (){
		Customer customer = customerServices.getCustomerDetails();
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/coupon/{companyId}/{title}")
	public ResponseEntity<Coupon> getOneCoupon(@PathVariable("companyId") int companyId,@PathVariable("title")String couponTitle) {
		Coupon coupon = customerServices.getOneCoupon(companyId, couponTitle);
		if(coupon!= null)
			return new ResponseEntity<>(coupon, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/coupon/getAll")
	public ResponseEntity<ArrayList<Coupon>> getAllCoupons() {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>(customerServices.getAllCoupons());
		if(coupons!= null)
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/coupon/getAll/price/{price}")
	public ResponseEntity<ArrayList<Coupon>> getAllCouponsByMaxPrice(@PathVariable("price")double maxPrice){
		ArrayList<Coupon> coupons = new ArrayList<Coupon>(customerServices.getAllCoupons(maxPrice));
		return new ResponseEntity<>(coupons, HttpStatus.OK);
	}
	

	
}
