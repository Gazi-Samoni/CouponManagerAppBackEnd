package com.CouponManagerSpring.dao;


import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public boolean existsByEmailAndPassword(String email, String password);
	public boolean existsByEmail(String email);
	public Customer findByEmailAndPassword(String email, String password);
	public Customer findById(int id);
}
