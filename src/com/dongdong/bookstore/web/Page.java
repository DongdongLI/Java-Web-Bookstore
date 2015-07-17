package com.dongdong.bookstore.web;
// this class represents an exact page of the searching results displayed in the browser
import java.util.List;

public class Page<T> {
	// current page number
	private int pageNo;
	// current page List (the books that displayed in this page )
	private List<T> list;
	//  how many records in each page
	private int pageSize=3;
	//how many records in total
	private long totalItemNum;
	
	public Page(int pageNo){
		super();
		this.pageNo=pageNo;
	}
	
	//need some check to make sure the pageNo is valid
	public int getPageNo(){
		if(pageNo<0)
			pageNo=1;
		if(pageNo>getTotalPageNum())
			pageNo=getTotalPageNum();
		
		return pageNo;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalItemNum() {
		return totalItemNum;
	}

	public void setTotalItemNum(long totalItemNum) {
		this.totalItemNum = totalItemNum;
	}
	
	// how many pages in total: the total records divide the num of records in each page
	public int getTotalPageNum(){
		int totalPageNum=(int)totalItemNum/pageSize;
		if(totalItemNum%pageSize!=0)
			return totalPageNum+1;
		return totalPageNum;
	}
	
	public boolean isHasNext(){
		if(getPageNo()<getTotalPageNum())
			return true;
		return false;
	}
	public boolean isHasPrev(){
		if(getPageNo()>1)
			return true;
		return false;
	}
	public int getPrevPage(){
		if(isHasPrev()){
			return getPageNo()-1;
		}
		return getPageNo();
	}
	public int getNextPage(){
		if(isHasNext())
			return getPageNo()+1;
		return getPageNo();
	}
}
