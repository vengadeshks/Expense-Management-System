package model;

public class line_item {
	private int expense_id;
	private int expense_item_id;
	private Integer category_id;
	private String description;
	private double amount;
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public int getExpense_id() {
		return expense_id;
	}
	public void setExpense_id(int expense_id) {
		this.expense_id = expense_id;
	}
	public int getExpense_item_id() {
		return expense_item_id;
	}
	public void setExpense_item_id(int expense_item_id) {
		this.expense_item_id = expense_item_id;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}