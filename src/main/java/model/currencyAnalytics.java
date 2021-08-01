package model;

public class currencyAnalytics {

	private int currency_id;
	private String currency_code;
	private String currency_symbol;
	private int expense_count;
	private String expense_total;
	public int getCurrency_id() {
		return currency_id;
	}
	public void setCurrency_id(int currency_id) {
		this.currency_id = currency_id;
	}
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
	public int getExpense_count() {
		return expense_count;
	}
	public void setExpense_count(int expense_count) {
		this.expense_count = expense_count;
	}
	public String getExpense_total() {
		return expense_total;
	}
	public void setExpense_total(String expense_total) {
		this.expense_total = expense_total;
	}
}
