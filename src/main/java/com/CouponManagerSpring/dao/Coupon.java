package com.CouponManagerSpring.dao;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COUPONS")
public class Coupon {
	
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getCompanyID() {
		return companyId;
	}

	public void setCompanyID(int companyID) {
		this.companyId = companyID;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Coupon() {
		// TODO Auto-generated method stub

	}
	
	public Coupon(int id, int companyID, Category category, String title, String descprition, Date startDate, Date endDate, int amount, double price, String image) {
		this.id = id;
		this.companyId = companyID;
		this.category = category;
		this.title = title;
		this.description = descprition;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}
	
	public Coupon( int companyID, Category category, String title, String descprition, Date startDate, Date endDate, int amount, double price, String image) {
		this.companyId = companyID;
		this.category = category;
		this.title = title;
		this.description = descprition;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}
	
	
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyID=" + companyId + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", amount=" + amount + ", price=" + price + ", image=" + image + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	int id;
	@Column(name="ComapnyId")
	
	//@ManyToOne
	int companyId;
	@Enumerated(EnumType.STRING)
	@Column(name="CategoryId")
	Category category;
	@Column(name="TITLE")
	String title;
	@Column(name="Description")
	String description;
	@Column(name="StartDate")
	Date startDate;
	@Column(name="EndDate")
	Date endDate;
	@Column(name="Amount")
	int amount;
	@Column(name="Price")
	double price;
	@Column(name="Image")
	String image;
	
}
