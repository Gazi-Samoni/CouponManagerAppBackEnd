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
		
		public int getID() { return id;}
		
		public void setID(int m_id) { this.id = m_id;}
		
		public String getName() { return name;}
		
		public void setName(String m_name) { this.name = m_name;}
		
		public String getEmail() { return email;}
		
		public void setEmail(String m_email) { this.email = m_email;}
		
		public String getPassword() { return password;}
		
		public void setPassword(String m_password) { this.password = m_password;}
		
		public ArrayList<Coupon> getCoupons() { return coupons;}
		
		public void setCoupons(ArrayList<Coupon> m_coupons) { this.coupons = m_coupons;}
		
		//Constructors
		public Company() {}
		public Company(int id, String name, String email, String password) {
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
		}
		public Company(String name, String email, String password) {
			this.id = -1;
			this.name = name;
			this.email = email;
			this.password = password;
		}
		
		@Override
		public String toString() {
			return "Company [id=" + id + ", name=" + name + ", email=" + email  + "]";
		}
		
		//Data Members
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY) // AI
		@Column(name="Id")
		//@OneToMany(mappedBy="companyId", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
		int id;
		@Column(name="Name")
		String name;
		@Column(name="Email")
		String email;
		@Column(name="Password")
		String password;
		@Transient
		@OneToMany(mappedBy ="companyId",fetch = FetchType.EAGER, cascade = CascadeType.ALL )
		ArrayList<Coupon> coupons;
		
}


