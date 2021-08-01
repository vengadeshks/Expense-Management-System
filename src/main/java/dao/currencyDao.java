package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class currencyDao {


Connection con = connectionDao.getConnection();
	
//	list all the currency
     public List<currency> getCurrency() {
        List<currency> currencies = new ArrayList<>();
		
		try {
		
		String sql = "select * from currency order by currency_id";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			currency each_currency = new currency();
			each_currency.setCurrency_id(rs.getInt(1));
			each_currency.setCurrency_code(rs.getString(2));
			each_currency.setCurrency_symbol(rs.getString(3));
			currencies.add(each_currency);
		}
		}catch(Exception e){
			System.out.println(e);
		}
		return currencies;
		
	}

// add currency
     public int addCurrency(currency c) {
		String sql = "insert into currency(currency_code,currency_symbol) values(?,?)";
		String query = "select MAX(currency_id) from currency";
		int id=0;
        try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,c.getCurrency_code());
			st.setString(2,c.getCurrency_symbol());
			st.executeUpdate();
			
			Statement st1 = con.createStatement();
			ResultSet rs = st1.executeQuery(query);
			rs.next();
			id= rs.getInt(1);
			
			}catch(Exception e){
				System.out.println(e);
			}
		return id;
	}

// list a particular currency
	public currency getCurrency(int id) {
		currency currency = new currency();
		
			try {
			
			String sql = "select * from currency where currency_id="+id;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				currency.setCurrency_id(rs.getInt(1));
				currency.setCurrency_code(rs.getString(2));
				currency.setCurrency_symbol(rs.getString(3));
			}
			
			}catch(Exception e){
				System.out.println(e);
			}
			return currency;
	}
	
//update the details of currency

	public void updateCurrency(currency c) throws IllegalArgumentException, IllegalAccessException {
		int id = c.getCurrency_id();
		 showFields(c,id);	
		
	}
	
	public void showFields(Object o,int id) throws IllegalArgumentException, IllegalAccessException {
		
		 StringBuilder sql = new StringBuilder("update currency set ");
		   
		 try {
		 Class<?> clazz = o.getClass();

		   for(Field field : clazz.getDeclaredFields()) {
	            // get value of the fields
	     
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if(value!=null && !(field.getName().equals("currency_id"))) {
	        	 System.out.println( field.getName());
	        	 sql.append( field.getName());
	        	 sql.append(" = (?)");
			     sql.append(", ");
			      
	         }
	       }
		   sql.deleteCharAt(sql.length()-2);
		   sql.append("where currency_id = (?);");
		   System.out.println(sql.toString());

		   PreparedStatement pr = con.prepareStatement(sql.toString());

		   int i=1;
		   for(Field field : clazz.getDeclaredFields()) {
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if(value!=null && !(field.getName().equals("currency_id"))) {
	        	 System.out.println(value);
	        	 pr.setString(i,value.toString());
	        	 i++;
	         }
	        
	         }
		   pr.setInt(i,id);
		   
		   pr.executeUpdate();
		 }catch(Exception e) {
			 
		 }
		   
}
	
	
	//delete the currency

	public boolean deleteCurrency(int id) {
		String sql = "delete from currency where currency_id=?";
        try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,id);
			st.executeUpdate();
			
			
			}catch(Exception e){
				System.out.println(e);
				return false;
			}
        return true;
	}
	

	public List<currencyAnalytics> getAnalytics(user u) {
		
		 List<currencyAnalytics> currencies = new ArrayList<>();
		
			
			try {
				 String sql ;
				 if(u.getUser_role().equals("Admin")) {
						sql="select c.currency_id,c.currency_code,c.currency_symbol,sum(e.expense_total),count(e.expense_id) from expense e inner join currency c on e.currency_id = c.currency_id\r\n"
								+ " group by e.currency_id HAVING sum(e.expense_total)>0";
						
				 }else {
						
						sql="select c.currency_id,c.currency_code,c.currency_symbol,sum(e.expense_total),count(e.expense_id) from expense e inner join currency c on e.currency_id = c.currency_id\r\n"
								+ "where e.user_id =  "+u.getUser_id()+"  group by e.currency_id HAVING sum(e.expense_total)>0";
				 }
		  
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
	
			     currencyAnalytics cur_an = new currencyAnalytics();
			     
			     cur_an.setCurrency_id(rs.getInt(1));
			     cur_an.setCurrency_code(rs.getString(2));
			     cur_an.setCurrency_symbol(rs.getString(3));
			     cur_an.setExpense_total(rs.getDouble(4)+" "+rs.getString(3));
			     cur_an.setExpense_count(rs.getInt(5));
			     currencies.add(cur_an);
			     }
			    
				
			
			}catch(Exception e){
				System.out.println(e);
			}
			return currencies;
	}

	
	
}
