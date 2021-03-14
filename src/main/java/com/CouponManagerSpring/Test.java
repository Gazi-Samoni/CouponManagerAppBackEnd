package com.CouponManagerSpring;

import java.util.ArrayList;
import java.util.Iterator;

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
			/////////////////////////////////////////////
			int id = companyFacade.getCompanyIdByEmailAndPassword(Amdocs.getEmail(),Amdocs.getPassword());
			if (id == 0) {
				System.out.println("Cannot find the company from the given email and password");
				return;
			}
			
			Amdocs.setID(id);

			companyFacade.setID(Amdocs.getID());
			/////////////////////////////////////////////////////
			id = companyFacade.getCompanyIdByEmailAndPassword(Intel.getEmail(),Intel.getPassword());
			if (id == 0) {
				System.out.println("Cannot find the company from the given email and password");
				return;
			}
			
			Intel.setID(id);
			
			////////////////////////////////////////////////////
			id = companyFacade.getCompanyIdByEmailAndPassword(Microsoft.getEmail(),Microsoft.getPassword());
			if (id == 0) {
				System.out.println("Cannot find the company from the given email and password");
				return;
			}
			
			Microsoft.setID(id);
			
			/////////////////////////////////////////////////////
			Coupon coupon = new Coupon(Amdocs.getID(),Category.Electricity,"coupon1","test1",date,date,5,3.6,"https://image.shutterstock.com/z/stock-vector-food-coupon-grunge-rubber-stamp-on-white-vector-illustration-180113531.jpg");
			Coupon coupon2 = new Coupon(Amdocs.getID(),Category.Food,"coupon2","test31",date,date,5,3.6,"https://image.shutterstock.com/z/stock-vector-food-coupon-grunge-rubber-stamp-on-white-vector-illustration-180113531.jpg");
			Coupon coupon3 = new Coupon(Amdocs.getID(),Category.Restaurant,"coupon3","test1",date,date,5,3.6,"https://image.shutterstock.com/z/stock-vector-food-coupon-grunge-rubber-stamp-on-white-vector-illustration-180113531.jpg");
			companyFacade.addCoupon(coupon);
			companyFacade.addCoupon(coupon2);
			companyFacade.addCoupon(coupon3);
			
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
				//Microsoft.setPassword("8888");
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
			
			Coupon coupon = new Coupon(companyFacade.getCompanyID(),Category.Vacation,"coupon3","Intel from companyUserTest ",date,date,5,3.6,"https://image.shutterstock.com/z/stock-vector-food-coupon-grunge-rubber-stamp-on-white-vector-illustration-180113531.jpg");
			companyFacade.addCoupon(coupon);
				//Same Title -> failed
				Coupon coupon2 = new Coupon(companyFacade.getCompanyID(),Category.Food,"coupon3","Intel from companyUserTest",date,date,3,3.6,"https://image.shutterstock.com/z/stock-vector-food-coupon-grunge-rubber-stamp-on-white-vector-illustration-180113531.jpg");
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
			
			Coupon coupon = new Coupon(IntelID,Category.Vacation,"Sky Dive","Intel from CustomerUserTest ",date,dateEnd,5,3.6,"data:image/png;base64,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");
			Coupon coupon2 = new Coupon(IntelID,Category.Food,"1+1","Intel from CustomerUserTest->zero amount ",date,dateEnd,0,3.6,"https://image.shutterstock.com/z/stock-vector-food-coupon-grunge-rubber-stamp-on-white-vector-illustration-180113531.jpg");
			Coupon coupon3 = new Coupon(IntelID,Category.Food,"dessert","Intel from CustomerUserTest->expierd date ",date,dateExpierd,80,3.6,"https://image.shutterstock.com/z/stock-vector-food-coupon-grunge-rubber-stamp-on-white-vector-illustration-180113531.jpg");
			
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



