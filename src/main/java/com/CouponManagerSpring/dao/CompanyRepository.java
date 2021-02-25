package com.CouponManagerSpring.dao;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	public boolean existsByEmailAndPassword(String email, String password);
	public boolean existsByEmail(String email);
	public boolean existsByName(String name);
	public Company findByName(String name);
	public Company findByEmailAndName(String email, String name);
}
