package com.dongdong.bookstore.web;
// help the search with criteria of the price
public class CriteriaBook {
	private double minPrice=0;
	private double maxPrice=Integer.MAX_VALUE;
	
	private int pageNo;// what is the current pageNum

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public CriteriaBook(double minPrice, double maxPrice, int pageNo) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.pageNo = pageNo;
	}

}
