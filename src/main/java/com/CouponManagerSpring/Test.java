package com.CouponManagerSpring;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.CouponManagerSpring.Login.ClientType;
import com.CouponManagerSpring.Login.LoginManager;
import com.CouponManagerSpring.dao.*;
import com.CouponManagerSpring.service.*;

@Scope("singleton")
@Component
public class Test implements CommandLineRunner{

	@Autowired
	LoginManager loginManager;
	@Autowired
	AdminServicesImpl  adminFacde;
	@Autowired
	CompanyServicesImpl  companyFacade;
	@Autowired
	CustomerServicesImpl  customerFacade;

	@Override
	public void run(String... args) throws Exception {
		 @SuppressWarnings("unused")
		    org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
		    java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF); //or whatever level you need

		tester();
		
	}

	public void tester(){
		
	
		adminFacde = (AdminServicesImpl)loginManager.login("admin@admin.com","admin", ClientType.Administrator);
		administratorUserTest(adminFacde);
		
		companyFacade = (CompanyServicesImpl)loginManager.login("Intel@Intel.com","1234", ClientType.Company);
		companyUserTest(companyFacade);
		
		customerFacade = (CustomerServicesImpl)loginManager.login("gazi@gmail.com","1234", ClientType.Customer);
		CustomerUserTest(customerFacade);	
	}
			
		 void administratorUserTest(AdminServicesImpl adminFacade){
			System.out.println("\n\n\n");
			System.out.println("------------------Administrator Test------------------");
			
			//Login
			System.out.println("Administrator Login: " + adminFacade.login("admin@admin.com","admin"));
			
			//Add new Company
			Company Amdocs = new Company("Amdocs","Amdocs@Amdocs.com","1234");
			Company Intel = new Company("Intel","Intel@Intel.com","1234");
			Company Microsoft = new Company("Microsoft","Microsoft@Microsoft.com","1234");
			adminFacade.addCompany(Amdocs);
			adminFacade.addCompany(Intel);
			adminFacade.addCompany(Microsoft);	
			
			
			//to delete
			long millis=System.currentTimeMillis(); 
			java.sql.Date date = new java.sql.Date(millis);
			
			//create coupons
			System.out.println(Amdocs.getEmail() + " " +Amdocs.getPassword() );
			
			Amdocs.setID(companyFacade.getCompanyIdByEmailAndPassword(Amdocs.getEmail(),Amdocs.getPassword()));
			companyFacade.setID(Amdocs.getID());
			Intel.setID(companyFacade.getCompanyIdByEmailAndPassword(Intel.getEmail(),Intel.getPassword()));
			Microsoft.setID(companyFacade.getCompanyIdByEmailAndPassword(Microsoft.getEmail(),Microsoft.getPassword()));
			
			Coupon coupon = new Coupon(Amdocs.getID(),Category.Electricity,"coupon1","test1",date,date,5,3.6,"temp");
			Coupon coupon2 = new Coupon(Amdocs.getID(),Category.Food,"coupon2","test31",date,date,5,3.6,"temp");
			Coupon coupon3 = new Coupon(Amdocs.getID(),Category.Restaurant,"coupon3","test1",date,date,5,3.6,"temp");
			companyFacade.addCoupon(coupon);
			companyFacade.addCoupon(coupon2);
			companyFacade.addCoupon(coupon3);
			
			
			for (int i = 0; i < Amdocs.getCoupons().size(); i++) {
				System.out.println(Amdocs.getCoupons().get(i).toString());
				
			}
			
			//Failure test 
				//exists name
				Company company4 = new Company("Amdocs","a@a.com","1234");	
				adminFacade.addCompany(company4);
				//exists email
				company4.setEmail("Intel@Intel.com");
				company4.setName("test2");
				adminFacade.addCompany(company4);
				
			
			//Update Company
				//Edit Password 
				Microsoft.setPassword("8888");
				adminFacade.updateCompany(Microsoft);
			
				//Edit ID -> fail
				int saveID = Amdocs.getID();
				Amdocs.setID(25);
				adminFacade.updateCompany(Amdocs);
						
				//Edit Company Name -> fail
				Intel.setName("zerto");
				adminFacade.updateCompany(Intel);
						
				//restore original info
				Amdocs.setID(saveID);
				Intel.setName("Intel");
			
			//Delete Company
			adminFacade.deleteCompany(Amdocs.getID());
			
			//Get all Companies
			ArrayList<Company> companies = adminFacade.getAllCompanies();
			for (int i = 0; i < companies.size(); i++) {
				System.out.println(companies.get(i).toString());
			}
			//Get one Company
			Company company5 = adminFacade.getOneCompany(Intel.getID());
			System.out.println(company5.toString());
			
			System.out.println("\n\n\n");
			
			//Add new customers
			Customer customer = new Customer("Abed","shalgam","abed@gmail.com","1234");
			Customer customer2 = new Customer("Gazi","Samoni","gazi@gmail.com","1234");
			adminFacade.addCustomer(customer);
			adminFacade.addCustomer(customer2);
			customer.setId(customerFacade.getCustomerIdByEmailAndPassword(customer.getEmail(), customer.getPassword()));
			customer2.setId(customerFacade.getCustomerIdByEmailAndPassword(customer2.getEmail(), customer2.getPassword()));
			//add customer with exists email	
			Customer customer3 = new Customer("john","kyle","gazi@gmail.com","1234");
			adminFacade.addCustomer(customer3);
			
			//update customer
			customer.setLastName("dustin");
			adminFacade.updateCustomer(customer);
			int saveID1 = customer.getId();
				//Edit customer id -> failed
				customer.setId(35);
				adminFacade.updateCustomer(customer);
				
				//reset data
				customer.setId(saveID1);
			
			//Delete customer
			adminFacade.deleteCustomer(customer.getId());
			
			//Get all customers
			ArrayList<Customer> customers = adminFacade.getAllCustomer();
			for (int i = 0; i < customers.size(); i++) {
				System.out.println(customers.get(i).toString());
			};
			
			//Get one customer
			Customer customer4 = adminFacade.getOneCustomer(customer2.getId());
			System.out.println(customer4.toString());
			
		}
		
		 void companyUserTest(CompanyServicesImpl companyFacade ){	
			System.out.println("\n\n\n");
			System.out.println("------------------Company Test------------------");

			//Add Coupon
			long millis=System.currentTimeMillis(); 
			java.sql.Date date = new java.sql.Date(millis);
			
			Coupon coupon = new Coupon(companyFacade.getCompanyID(),Category.Vacation,"coupon3","Intel from companyUserTest ",date,date,5,3.6,"temp");
			companyFacade.addCoupon(coupon);
				//Same Title -> failed
				Coupon coupon2 = new Coupon(companyFacade.getCompanyID(),Category.Food,"coupon3","Intel from companyUserTest",date,date,3,3.6,"temp");
				companyFacade.addCoupon(coupon2);	
			
			//Update coupon
			coupon.setAmount(10);
			coupon.setDescription("Intel after edit");
			companyFacade.updateCoupon(coupon);
			
			//Delete coupon
			companyFacade.deleteCoupon(coupon.getID());
			
			//Get All Coupons
			ArrayList<Coupon> coupons = companyFacade.getCompanyCoupons();	
			for(Coupon var:coupons)
			{
				System.out.println(var.toString());
			}
			//Get All Coupons by category
			ArrayList<Coupon> coupons2 = companyFacade.getCompanyCoupons(Category.Restaurant);	
			for(Coupon var:coupons2)
			{
				System.out.println(var.toString());
			}
			
			//Get All Coupons by category
			ArrayList<Coupon> coupons3 = companyFacade.getCompanyCoupons(70);	
			for(Coupon var:coupons3)
			{
				System.out.println(var.toString());
			}
			
			//Get Company details
			Company company = companyFacade.getCompanyDetails();
			System.out.println(company.toString());
		}
		
		 void CustomerUserTest(CustomerServicesImpl customerFacade){
			System.out.println("\n\n\n");
			System.out.println("------------------Customer Test------------------");
			
			int IntelID = companyFacade.getCompanyIdByEmailAndPassword("Intel@Intel.com","1234");
			companyFacade.setID(IntelID);
			java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
			java.sql.Date dateEnd = new java.sql.Date(2022-1900,1-1,1);
			@SuppressWarnings("deprecation")
			java.sql.Date dateExpierd = new java.sql.Date(2022-1920,1-1,1);
			
			Coupon coupon = new Coupon(IntelID,Category.Vacation,"Sky Dive","Intel from CustomerUserTest ",date,dateEnd,5,3.6,"temp");
			Coupon coupon2 = new Coupon(IntelID,Category.Food,"1+1","Intel from CustomerUserTest->zero amount ",date,dateEnd,0,3.6,"temp");
			Coupon coupon3 = new Coupon(IntelID,Category.Food,"dessert","Intel from CustomerUserTest->expierd date ",date,dateExpierd,80,3.6,"temp");
			
			companyFacade.addCoupon(coupon);
			companyFacade.addCoupon(coupon2);
			companyFacade.addCoupon(coupon3);
		
			coupon.setID(customerFacade.getOneCoupon(coupon.getCompanyID(), coupon.getTitle()).getID());
			coupon2.setID(customerFacade.getOneCoupon(coupon2.getCompanyID(), coupon2.getTitle()).getID());
			coupon3.setID(customerFacade.getOneCoupon(coupon3.getCompanyID(), coupon3.getTitle()).getID());
			//Purchase Coupon
			customerFacade.purchaseCoupon(coupon);
				//repurchase same coupon ->fails
				customerFacade.purchaseCoupon(coupon);
				//Purchase Coupon with amount 0 -> fails
				customerFacade.purchaseCoupon(coupon2);
				//Purchase Coupon with expired date -> fails
				customerFacade.purchaseCoupon(coupon3);
			
			
			//Get customer coupons
			ArrayList<Coupon> custumerCoupons = customerFacade.getCustomerCoupons();
			for(Coupon var:custumerCoupons)
			{
				System.out.println(var.toString());
			}
			
			System.out.println("");
			
			
			//Get customer coupons by Category
			ArrayList<Coupon> custumerCoupons2 = customerFacade.getCustomerCoupons(Category.Food);
			
			for(Coupon var:custumerCoupons2){
				System.out.println(var.toString());
			}
			System.out.println("");
			//Get customer coupons by max price
			ArrayList<Coupon> custumerCoupons3 = customerFacade.getCustomerCoupons(customerFacade.getId());
			for(Coupon var:custumerCoupons3)
			{
				System.out.println(var.toString());
			}
			//Get customer details
			Customer customer = customerFacade.getCustomerDetails();
			System.out.println(customer.toString());
			
		}

		
}



