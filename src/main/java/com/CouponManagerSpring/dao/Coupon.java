package com.CouponManagerSpring.dao;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coupon {
	public int getID() {
		return m_id;
	}

	public void setID(int id) {
		this.m_id = id;
	}

	public int getCompanyID() {
		return m_companyID;
	}

	public void setCompanyID(int companyID) {
		this.m_companyID = companyID;
	}

	public Category getCategory() {
		return m_category;
	}

	public void setCategory(Category category) {
		this.m_category = category;
	}

	public String getTitle() {
		return m_title;
	}

	public void setTitle(String title) {
		this.m_title = title;
	}

	public String getDescription() {
		return m_description;
	}

	public void setDescription(String description) {
		this.m_description = description;
	}

	public Date getStartDate() {
		return m_startDate;
	}

	public void setStartDate(Date startDate) {
		this.m_startDate = startDate;
	}

	public Date getEndDate() {
		return m_endDate;
	}

	public void setEndDate(Date endDate) {
		this.m_endDate = endDate;
	}

	public int getAmount() {
		return m_amount;
	}

	public void setAmount(int amount) {
		this.m_amount = amount;
	}

	public double getPrice() {
		return m_price;
	}

	public void setPrice(double price) {
		this.m_price = price;
	}

	public String getImage() {
		return m_image;
	}

	public void setImage(String image) {
		this.m_image = image;
	}

	public Coupon() {
		// TODO Auto-generated method stub

	}
	
	public Coupon(int id, int companyID, Category category, String title, String descprition, Date startDate, Date endDate, int amount, double price, String image) {
		this.m_id = id;
		this.m_companyID = companyID;
		this.m_category = category;
		this.m_title = title;
		this.m_description = descprition;
		this.m_startDate = startDate;
		this.m_endDate = endDate;
		this.m_amount = amount;
		this.m_price = price;
		this.m_image = image;
	}
	
	
	@Override
	public String toString() {
		return "Coupon [id=" + m_id + ", companyID=" + m_companyID + ", category=" + m_category + ", title=" + m_title
				+ ", description=" + m_description + ", amount=" + m_amount + ", price=" + m_price + ", image=" + m_image + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	int m_id;
	@Column(name="COMPANY_ID")
	int m_companyID;
	@Enumerated(EnumType.STRING)
	@Column(name="CATEGORY_ID")
	Category m_category;
	@Column(name="TITLE")
	String m_title;
	@Column(name="DESCRIPTION")
	String m_description;
	@Column(name="START_DATE")
	Date m_startDate;
	@Column(name="END_DATE")
	Date m_endDate;
	@Column(name="AMOUNT")
	int m_amount;
	@Column(name="PRICE")
	double m_price;
	@Column(name="IMAGE")
	String m_image;
}
