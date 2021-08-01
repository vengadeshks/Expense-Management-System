package model;

import java.util.*;

public class expense {

	private Integer expense_id;
	private Integer currency_id;
	private Integer merchant_id;
	private String expense_date; 
	private List<line_item> item;
	public Integer getExpense_id() {
		return expense_id;
	}
	public void setExpense_id(Integer expense_id) {
		this.expense_id = expense_id;
	}
	public Integer getCurrency_id() {
		return currency_id;
	}
	public void setCurrency_id(Integer currency_id) {
		this.currency_id = currency_id;
	}
	public Integer getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getExpense_date() {
		return expense_date;
	}
	public void setExpense_date(String expense_date) {
		this.expense_date = expense_date;
	}
	public List<line_item> getItem() {
		return item;
	}
	public void setItem(List<line_item> item) {
		this.item = item;
	}
	

	
}

