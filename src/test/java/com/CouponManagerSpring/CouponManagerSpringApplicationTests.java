package com.CouponManagerSpring;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.CouponManagerSpring.dao.Company;
import com.CouponManagerSpring.service.ClientService;

@SpringBootTest
class CouponManagerSpringApplicationTests {
	@Autowired
	ClientService service;
	@Test
	void contextLoads() {
		Company company = new Company();
		
		
		company.setName("amdocs");
		company.setEmail("amdocs@amdocs.com");
		company.setPassword("123");
		
		service.addCompany(company);
	}

}



