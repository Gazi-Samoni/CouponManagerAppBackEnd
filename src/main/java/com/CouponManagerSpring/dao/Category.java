package com.CouponManagerSpring.dao;


public enum Category {
	Food,
	Electricity,
	Restaurant,
	Vacation;
	
	public static Category FromInt(int x)
	{
		switch(x)
		{
			case 0:
				return Food;
			case 1:
				return Electricity;
			case 2:
				return Restaurant;
			case 3:
				return Vacation;	
		}
		
		return null;
	}

	public static int ToInt(Category category) {
		switch(category)
		{
			case Food:
				return 0;
			case Electricity:
				return 1;
			case Restaurant:
				return 2;
			case Vacation:
				return 3;	
		}
		
		return -1;
	}
}
