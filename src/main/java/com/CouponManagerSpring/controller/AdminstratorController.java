package com.CouponManagerSpring.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CouponManagerSpring.dao.Company;
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
	public ResponseEntity<Boolean> login(@PathVariable("email")String email, @PathVariable("password")String password) {
		if(adminService.login(email, password))
			return new ResponseEntity<>(true,HttpStatus.OK);
		
		else	
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
			
	}
	
	@PostMapping("/company/add")
	public ResponseEntity<Company> addCompany(@RequestBody Company company){
		Company NewCompany = adminService.addCompany(company);
		if(NewCompany != null)
			return new ResponseEntity<>(NewCompany,HttpStatus.CREATED);
		else	
			return new ResponseEntity<>(NewCompany,HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/company/update")
	public ResponseEntity<Company> updateCompany(@RequestBody Company company){
		Company NewCompany = adminService.updateCompany(company);
		if(NewCompany != null)
			return new ResponseEntity<>(NewCompany,HttpStatus.OK);
		else	
			return new ResponseEntity<>(NewCompany,HttpStatus.BAD_REQUEST);
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
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		Customer newCustomer = adminService.addCustomer(customer);
		if(newCustomer != null) {
			System.out.println("going to add \n" +customer.toString() );
			return new ResponseEntity<>(newCustomer,HttpStatus.CREATED);
		}
		else	
			return new ResponseEntity<>(newCustomer,HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/customer/update")
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
