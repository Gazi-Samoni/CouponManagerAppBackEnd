package com.CouponManagerSpring.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CouponManagerSpring.dao.Company;
import com.CouponManagerSpring.dao.Coupon;
import com.CouponManagerSpring.dao.Customer;
import com.CouponManagerSpring.service.AdminServicesImpl;

@RestController
@RequestMapping("/admin")
public class AdminstratorController {
	AdminServicesImpl adminService;
	
	@Autowired
	private AdminstratorController(AdminServicesImpl adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<String> login(@PathVariable("email")String email, @PathVariable("password")String password) {
		if(adminService.login(email, password))
			return new ResponseEntity<>("login succeded",HttpStatus.OK);
		else	
			return new ResponseEntity<>("login faild",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/company/add")
	public ResponseEntity<String> addCompany(@RequestBody Company company){
		String status = adminService.addCompany(company);
		if(status.equals("Comany added"))
			return new ResponseEntity<>(status,HttpStatus.CREATED);
		else	
			return new ResponseEntity<>(status,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/company/update")
	public ResponseEntity<String> updateCompany(@RequestBody Company company){
		String status = adminService.updateCompany(company);
		if(status.equals("Comany Updated"))
			return new ResponseEntity<>(status,HttpStatus.OK);
		else	
			return new ResponseEntity<>(status,HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/company/delete/{companyId}")
	public ResponseEntity<?> deleteCompany(@PathVariable("companyId") int companyId){
		adminService.deleteCompany(companyId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/company/getAll")
	public  ResponseEntity<ArrayList<Company>> getAllCompanies(){
		ArrayList<Company> companies = adminService.getAllCompanies();	
		return new ResponseEntity<>(companies,HttpStatus.OK); 
	}
	
	@GetMapping("/company/get/{companyId}")
	public ResponseEntity<Company> getOneCompany(@PathVariable("companyId") int companyId){
		Company company = adminService.getOneCompany(companyId);
		if(company != null)
			return new ResponseEntity<>(company,HttpStatus.OK); 
		
		else
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/customer/add")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
		String status = adminService.addCustomer(customer);
		if(status.equals("Customer added")) {
			System.out.println("going to add \n" +customer.toString() );
			return new ResponseEntity<>(status,HttpStatus.CREATED);
		}
		else	
			return new ResponseEntity<>(status,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/customer/update")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
		adminService.updateCustomer(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/customer/delete/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("customerId")int customerId){
		adminService.deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/customer/getAll")
	public ResponseEntity<ArrayList<Customer>> getAllCustomer(){
		ArrayList<Customer> customers = adminService.getAllCustomer();
		return new ResponseEntity<>(customers,HttpStatus.OK);
	}
	
	@GetMapping("/customer/get/{customerId}")
	public ResponseEntity<Customer> getOneCustomer(@PathVariable("customerId")int customerId){
		Customer customer = adminService.getOneCustomer(customerId);
		if(customer != null)
			return new ResponseEntity<>(customer,HttpStatus.OK); 
		else
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}

}
