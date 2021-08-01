package model;


public class line_itemJson {
	private int expense_item_id;
	private int category_id;
	private String Category_name;
	private String description;
	private String amount;
	
	public String getCategory_name() {
		return Category_name;
	}
	public void setCategory_name(String category_name) {
		Category_name = category_name;
	}
	public int getCategory_id() {
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
	public String getAmount() {
		return amount;
	}
	public int getExpense_item_id() {
		return expense_item_id;
	}
	public void setExpense_item_id(int expense_item_id) {
		this.expense_item_id = expense_item_id;
	}
	public void setAmount(String amt) {
		this.amount = amt;
	}

}

