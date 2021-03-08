package com.CouponManagerSpring.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.CouponManagerSpring.service.AdminServicesImpl;
import com.CouponManagerSpring.service.ClientService;
import com.CouponManagerSpring.service.CompanyServicesImpl;
import com.CouponManagerSpring.service.CustomerServicesImpl;

@Scope("singleton")
@Component
public class LoginManager {
	@Autowired
	AdminServicesImpl adminAuthority;
	@Autowired
	CompanyServicesImpl companyAuthority;
	@Autowired
	CustomerServicesImpl customerAuthority;
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
					if(adminAuthority.login(email, password))
						client = adminAuthority;
				break;
			
			case Company:

					if(companyAuthority.login(email, password)) {
						client = companyAuthority;
					}
				break;
			
			case Customer:
					if(customerAuthority.login(email, password)) {
						client = customerAuthority;
					}
			
			default:
				break;
		}
		
		return client;
	}

}
