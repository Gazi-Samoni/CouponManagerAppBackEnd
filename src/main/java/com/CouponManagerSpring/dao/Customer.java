package com.CouponManagerSpring.dao;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Customer {
	
	public int getId() {
		return m_id;
	}
	public void setId(int id) {
		this.m_id = id;
	}
	public String getFirstName() {
		return m_firstName;
	}
	public void setFirstName(String firstName) {
		this.m_firstName = firstName;
	}
	public String getLastName() {
		return m_lastName;
	}
	public void setLastName(String lastName) {
		this.m_lastName = lastName;
	}
	public String getEmail() {
		return m_email;
	}
	public void setEmail(String email) {
		this.m_email = email;
	}
	public String getPassword() {
		return m_password;
	}
	public void setPassword(String password) {
		this.m_password = password;
	}
	public ArrayList<Coupon> getCoupons() {
		return m_coupons;
	}
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.m_coupons = coupons;
	}
	
	public Customer() {

	}
	
	public Customer(int id, String firstName, String lastName, String email, String password) {
		this.m_id = id;
		this.m_firstName = firstName;
		this.m_lastName = lastName;
		this.m_email = email;
		this.m_password = password;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + m_id + ", firstName=" + m_firstName + ", lastName=" + m_lastName + ", email=" + m_email + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	int m_id;
	@Column(name="FIRST_NAME")
	String m_firstName;
	@Column(name="LAST_NAME")
	String m_lastName;
	@Column(name="EMAIL")
	String m_email;
	@Column(name="PASSWORD")
	String m_password;
	@Transient
	ArrayList<Coupon> m_coupons;
}
