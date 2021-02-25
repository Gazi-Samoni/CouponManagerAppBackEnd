package com.CouponManagerSpring.Login;

import com.CouponManagerSpring.service.AdminServicesImpl;
import com.CouponManagerSpring.service.ClientService;
import com.CouponManagerSpring.service.CompanyServicesImpl;
import com.CouponManagerSpring.service.CustomerServicesImpl;

public class LoginManager {
	/*
	private static LoginManager instance = null;
	
	
	
	public static LoginManager getInstance() {
		if(instance == null)
		{
			System.out.println("Requiring LoginManager...");
			instance = new LoginManager();
			System.out.println("LoginManager established.");
		}
		
		return instance;
	}
	 */
	
	private LoginManager() {
		
	}
	
	public ClientService login(String email, String password, ClientType clientType)
	{
		ClientService client = null;
		
		switch(clientType)
		{
			case Administrator:
					AdminServicesImpl adminAuthority = new AdminServicesImpl();
					if(adminAuthority.login(email, password))
						client = adminAuthority;
				break;
			
			case Company:
					CompanyServicesImpl companyAuthority = new CompanyServicesImpl();

					if(companyAuthority.login(email, password)) {
						int companyID = companyAuthority.getCompanyRepo().findByEmailAndName(email,password).getID();
						companyAuthority.setID(companyID);
						client = companyAuthority;
					}
				break;
			
			case Customer:
					CustomerServicesImpl customerAuthority = new CustomerServicesImpl();
					if(customerAuthority.login(email, password)) {
						int customerID = customerAuthority.getCustomerRepo().findByEmailAndPassword(email,password).getId();
						customerAuthority.setID(customerID);
						client = customerAuthority;
					}
			
			default:
				break;
		}
		
		return client;
	}

}
