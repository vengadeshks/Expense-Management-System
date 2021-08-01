package model;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlType(propOrder = {
		"expense_id",
		"currency_id",
		"currency_code",
		"merchant_id",
		"merchant_name",
	    "expense_date",
	    "expense_total",
	    "item"
	})
public class expenseJson {

	private String user_name;
	private String user_role;
	
	private int expense_id;
	private int currency_id;
	private String currency_code;
	private String currency_symbol;
	private int merchant_id;
	private String merchant_name;
	private String expense_date; 
	private String expense_total;
	private List<line_itemJson> item;
	

	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public String getCurrency_symbol() {
		return currency_symbol;
	}
	public void setCurrency_symbol(String currency_symbol) {
		this.currency_symbol = currency_symbol;
	}

	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String string) {
		this.merchant_name = string;
	}
	public String getExpense_date() {
		return expense_date;
	}
	public void setExpense_date(String expense_date) {
		this.expense_date = expense_date;
	}
	public String getExpense_total() {
		return expense_total;
	}
	public void setExpense_total(String expense_total) {
		this.expense_total = expense_total;
	}

	
	public int getExpense_id() {
		return expense_id;
	}
	public void setExpense_id(int expense_id) {
		this.expense_id = expense_id;
	}
	public int getCurrency_id() {
		return currency_id;
	}
	public void setCurrency_id(int currency_id) {
		this.currency_id = currency_id;
	}
	public int getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(int merchant_id) {
		this.merchant_id = merchant_id;
	}

	public List<line_itemJson> getItem() {
		return item;
	}
	public void setItem(List<line_itemJson> item) {
		this.item = item;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String string) {
		this.user_role = string;
	}
	
}
