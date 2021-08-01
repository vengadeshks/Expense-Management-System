package model;

import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



public class returnJson {
	
	//reponse for expense
	@XmlRootElement
	@XmlType(propOrder = {
			"message",
			"expenses"
		})
	public class returnExpense{
		private String message;
		private List<expenseJson> expenses;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public List<expenseJson> getExpenses() {
			return expenses;
		}
		public void setExpenses(List<expenseJson> expenses) {
			this.expenses = expenses;
		}
		
		
	}
	
	
	//reponse for category
	@XmlRootElement
	@XmlType(propOrder = {
			"message",
			"categories"
		})
	public class returnCategory{
		private String message;
		private List<category> categories;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public List<category> getCategories() {
			return categories;
		}
		public void setCategories(List<category> categories) {
			this.categories = categories;
		}
		
	}
	
	//reponse for users
	@XmlRootElement
	@XmlType(propOrder = {
			"message",
			"users"
		})
	public class returnUser{
		private String message;
		private List<user> users;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public List<user> getUsers() {
			return users;
		}
		public void setUsers(List<user> users) {
			this.users = users;
		}
		
	}
	
	//reponse for the merchant
	@XmlRootElement
	@XmlType(propOrder = {
			"message",
			"merchants"
		})
	public class returnMerchant{
		private String message;
		private List<merchant> merchants;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public List<merchant> getMerchants() {
			return merchants;
		}
		public void setMerchants(List<merchant> merchants) {
			this.merchants = merchants;
		}
		
	}
	
	//reponse for the currency
	@XmlRootElement
	@XmlType(propOrder = {
			"message",
			"currencies"
		})
	public class returnCurrency{
		private String message;
		private List<currency> currencies;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public List<currency> getCurrencies() {
			return currencies;
		}
		public void setCurrencies(List<currency> currencies) {
			this.currencies = currencies;
		}
		
	}
	public class returnAnalytics{
		private String message;
		private List<currencyAnalytics> currencies;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public List<currencyAnalytics> getCurrencies() {
			return currencies;
		}
		public void setCurrencies(List<currencyAnalytics> currencies) {
			this.currencies = currencies;
		}
		
	}
	
	//line item
	public class returnItem{
		private String message;
		private List<line_item> item;
		
		public List<line_item> getItem() {
			return item;
		}
		public void setItem(List<line_item> item) {
			this.item = item;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		
	}
	
	
	
}
