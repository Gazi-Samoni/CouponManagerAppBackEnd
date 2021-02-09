package com.CouponManagerSpring.dao;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="COMPANIES")
public class Company {
	
	//Getters & Setters
		
		public int getID() { return m_id;}
		
		public void setID(int m_id) { this.m_id = m_id;}
		
		public String getName() { return m_name;}
		
		public void setName(String m_name) { this.m_name = m_name;}
		
		public String getEmail() { return m_email;}
		
		public void setEmail(String m_email) { this.m_email = m_email;}
		
		public String getPassword() { return m_password;}
		
		public void setPassword(String m_password) { this.m_password = m_password;}
		
		public ArrayList<Coupon> getCoupons() { return m_coupons;}
		
		public void setCoupons(ArrayList<Coupon> m_coupons) { this.m_coupons = m_coupons;}
		
		//Constructors
		public Company() {}
		public Company(int id, String name, String email, String password) {
			this.m_id = id;
			this.m_name = name;
			this.m_email = email;
			this.m_password = password;
		}
		public Company(String name, String email, String password) {
			this.m_id = -1;
			this.m_name = name;
			this.m_email = email;
			this.m_password = password;
		}
		
		@Override
		public String toString() {
			return "Company [id=" + m_id + ", name=" + m_name + ", email=" + m_email  + "]";
		}
		
		//Data Members
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY) // AI
		@Column(name="ID")
		//@OneToMany(mappedBy="COMPANY_ID", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
		int m_id;
		@Column(name="NAME")
		String m_name;
		@Column(name="EMAIL")
		String m_email;
		@Column(name="PASSWORD")
		String m_password;
		@Transient
		ArrayList<Coupon> m_coupons;
		
}


