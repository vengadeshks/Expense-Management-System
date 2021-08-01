package dao;

import java.lang.reflect.Field;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;


public class expenseDao {
	Connection con = connectionDao.getConnection();
	

//	---------------------------------------To check user id is exist or not---------------------------------------------------
	public int getUserById(int id) {
		String sql = "select * from users where user_id="+id;
		int user_id=0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				user_id = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return user_id;
		
	}
//--------------------------------------------------check category--------------------------------------------------
	public boolean isValidCategory(int exp_id,int item_id) {
		String sql = "select expense_id from line_item where expense_item_id="+item_id;
		
		ResultSet rs;
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				if(rs.getInt(1)==exp_id) {
					return true;
				}else {
					return false;
				}
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return false;
		
	}
	
//	---------------------------------------------------list expenses--------------------------------------------------------------------
	public expenseJson getExpense(int id,int user_id) {
		 expenseJson expense = new expenseJson();
			
			try {
			
				String sql = " select e.expense_id,cu.currency_id,cu.currency_code,cu.currency_symbol,m.merchant_id,m.merchant_name,"
						+ "e.expense_date,e.expense_total,u.user_name,u.user_role from expense e left join merchant m on e.merchant_id=m.merchant_id "
						+ "inner join currency cu on e.currency_id = cu.currency_id inner join users u on u.user_id= e.user_id where e.expense_id = "+id+" and e.user_id = "+user_id;
				
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			
			
			if(rs.next()) {
				
				expense.setExpense_id(rs.getInt(1));
				expense.setCurrency_id(rs.getInt(2));
				expense.setCurrency_code(rs.getString(3));	
				expense.setCurrency_symbol(rs.getString(4));	
		     	expense.setMerchant_id(rs.getInt(5));
				expense.setMerchant_name(rs.getString(6));
				expense.setExpense_date(rs.getString(7));
				expense.setExpense_total(rs.getString(8)+" "+rs.getString(4));
				expense.setUser_name(rs.getString(9));
				expense.setUser_role(rs.getString(10));
				
				
				String sql2 = " select lt.expense_item_id,lt.description,lt.amount,"
						+ " ct.category_id,ct.category_name from line_item lt inner join"
						+ " category ct on lt.category_id = ct.category_id where expense_id="+rs.getInt(1);
				
				
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery(sql2);
				
				 List<line_itemJson> items = new ArrayList<>();
				
				while(rs2.next()) {
					line_itemJson item = new line_itemJson();
					item.setExpense_item_id(rs2.getInt(1));
					item.setDescription(rs2.getString(2));
					item.setAmount(rs2.getDouble(3)+" "+rs.getString(4));
					item.setCategory_id(rs2.getInt(4));
					item.setCategory_name(rs2.getString(5));
					items.add(item);
				}

				expense.setItem(items);

			
			}
			}catch(Exception e){
				System.out.println(e);
			}
			
		return expense;
		
	}
//	                              ---------------------------------------------------------

	public List<expenseJson> getExpenses(int user_id) {
		 List<expenseJson> expenses = new ArrayList<>();
			
			try {
			
				String sql = " select e.expense_id,cu.currency_id,cu.currency_code,cu.currency_symbol,m.merchant_id,m.merchant_name,"
						+ "e.expense_date,e.expense_total,u.user_name,u.user_role from expense e left join merchant m on e.merchant_id=m.merchant_id "
						+ "inner join currency cu on e.currency_id = cu.currency_id inner join users u on u.user_id= e.user_id where e.user_id = "+user_id+" order by e.expense_id";
					
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				
				
				while(rs.next()) {
		
				expenseJson expense = new expenseJson();
				expense.setExpense_id(rs.getInt(1));
				expense.setCurrency_id(rs.getInt(2));
				expense.setCurrency_code(rs.getString(3));	
				expense.setCurrency_symbol(rs.getString(4));	
		     	expense.setMerchant_id(rs.getInt(5));
				expense.setMerchant_name(rs.getString(6));
				expense.setExpense_date(rs.getString(7));
				expense.setExpense_total(rs.getString(8)+" "+rs.getString(4));
				expense.setUser_name(rs.getString(9));
				expense.setUser_role(rs.getString(10));
				
				
				String sql2 = " select lt.expense_item_id,lt.description,lt.amount,\r\n"
						+ " ct.category_id,ct.category_name from line_item lt inner join\r\n"
						+ " category ct on lt.category_id = ct.category_id where expense_id="+rs.getInt(1)+" order by lt.expense_item_id";
				
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery(sql2);
				
				 List<line_itemJson> items = new ArrayList<>();
				
				while(rs2.next()) {
					line_itemJson item = new line_itemJson();
					item.setExpense_item_id(rs2.getInt(1));
					item.setDescription(rs2.getString(2));
					item.setAmount(rs2.getDouble(3)+" "+rs.getString(4));
					item.setCategory_id(rs2.getInt(4));
					item.setCategory_name(rs2.getString(5));
					items.add(item);
				}
				
				expense.setItem(items);
				expenses.add(expense);
			
			
			}
			}catch(Exception e){
				System.out.println(e);
			}
			
		return expenses;
	}
//------------------------------------------------add-------------------------------------------------------------
	
	public int addExpense(expense e,int user_id) {
		System.out.print("add expense called ");
		String sql = "insert into expense(user_id,currency_id,merchant_id,expense_date) values(?,?,?,?)";
		String query = "select MAX(expense_id) from expense";
		int max_id=1;
		double total=0.0;
        try {
        	Statement st1 = con.createStatement();
			ResultSet rs = st1.executeQuery(query);
			if(rs.next()) {
			max_id= rs.getInt(1)+1;
			System.out.print("Not First");
			}
			
			PreparedStatement st = con.prepareStatement(sql);
		    st.setInt(1, user_id);
			st.setInt(2,e.getCurrency_id());
			st.setInt(3,e.getMerchant_id());
			st.setString(4,e.getExpense_date());
			
			st.executeUpdate();
			System.out.print("updated");
			
			for(line_item lt : e.getItem()) {
				String sql1 = "insert into line_item(expense_id,category_id,description,amount) values(?,?,?,?)";
	        	PreparedStatement st2 = con.prepareStatement(sql1);
	        	st2.setInt(1, max_id);
	        	st2.setInt(2, lt.getCategory_id());
	        	st2.setString(3, lt.getDescription());
	        	st2.setDouble(4, lt.getAmount());
	        	total+=lt.getAmount();
	        	st2.executeUpdate();
			}
			
			String sql3 = "update expense set expense_total=? where expense_id=?";
			PreparedStatement st3 = con.prepareStatement(sql3);
            st3.setDouble(1,total);
            st3.setInt(2,max_id);
            st3.executeUpdate();
		
			
			}catch(Exception exp){
				System.out.println(exp);
			}
		return max_id;
	}
//-----------------------------------------------delete------------------------------------------------------------

	public void deleteExpense(int id,int user_id) {
		String sql = "delete from expense where expense_id=?";
//		String sql1 = "delete from line_item where expense_id=?";
        try {
			//delete from expense
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,id);
		//	st.setInt(2,user_id);
			//delete from expense line_item 
//			PreparedStatement st1 = con.prepareStatement(sql1);
//			st1.setInt(1,id);
			
			st.executeUpdate();
//			st1.executeUpdate();
			
			
			}catch(Exception e){
				System.out.println(e);
			}
	}

//	-----------------------------------------------------update-------------------------------------------------------

	public void updateExpense(expense e,int user_id) throws IllegalArgumentException, IllegalAccessException {
	 
		     int id = e.getExpense_id();
		     System.out.println("expenseId"+ e.getExpense_id());
			 updateExpenses(e,id,user_id);
			 System.out.println("--------------------expense end----------------------");
			 for(line_item lt : e.getItem()) {
			 updateLine_items(lt,lt.getExpense_item_id(),id,user_id);
			 System.out.println("--------------------new item-----------------------");
			 }
		    updateTotal(id);
		   
	}
	
	public void updateTotal(int id) {
		String sql = "select sum(amount) from line_item where expense_id="+id;
		Statement st;
		double sum=0.0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				sum =rs.getDouble(1);
			}
			String sql3 = "update expense set expense_total=? where expense_id=?";
			PreparedStatement st3 = con.prepareStatement(sql3);
            st3.setDouble(1,sum);
            st3.setInt(2,id);
            st3.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void updateLine_items(Object o,int id,int exp_id,int user_id) throws IllegalArgumentException, IllegalAccessException {
		 StringBuilder sql = new StringBuilder("update line_item set ");
		   
		 try {
		 Class<?> clazz = o.getClass();

		   for(Field field : clazz.getDeclaredFields()) {
	            // get value of the fields
	     
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if( value!=null && !(field.getName().equals("expense_item_id")) &&  !(field.getName().equals("expense_id"))) {
//	        	 System.out.println( " if" +field.getName());
	        	 sql.append(field.getName());
	        	 sql.append(" = (?)");
			     sql.append(", ");
	       }
	         System.out.println(field.getName());    System.out.println(value);
	        
		   }
		   
		   
		   sql.deleteCharAt(sql.length()-2);
		   sql.append("where expense_item_id = (?) and expense_id = (?);");
		   System.out.println("query "+sql.toString());

		   PreparedStatement pr = con.prepareStatement(sql.toString());
		   
		   

		   int i=1;
		   for(Field field : clazz.getDeclaredFields()) {
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if(value!=null && (field.getName().equals("description")) ) {
	        	 System.out.println("if description "+ value);
	        	 pr.setString(i,value.toString());
	        	 i++;
	         }else if(value!=null && (field.getName().equals("amount")) ) {
	        	 System.out.println("if amount "+ value);
	        	 pr.setDouble(i,(double) value);
	        	 i++;
	         }
	         else if(value!=null  && !(field.getName().equals("expense_id"))&& !(field.getName().equals("expense_item_id"))) {
	        	 System.out.println( "else if others " +value);
	        	 pr.setInt(i,(int)value);
	        	 i++;
	         }
	        
	         }
		   

		   pr.setInt(i++,id);
		   pr.setInt(i++,exp_id);
		   System.out.println(exp_id);  
	
		   
		   pr.executeUpdate();
		 }catch(Exception e) {
			 System.out.print(e);
		 }
		
	}

	public void updateExpenses(Object o,int id,int user_id) throws IllegalArgumentException, IllegalAccessException {
		
		 StringBuilder sql = new StringBuilder("update expense set ");
		 boolean flag = false;
		   
		 try {
		 Class<?> clazz = o.getClass();

		   for(Field field : clazz.getDeclaredFields()) {
	            // get value of the fields
	     
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if( value!=null && !(field.getName().equals("expense_id"))&& !(field.getName().equals("item"))) {
//	        	 System.out.println( " if" +field.getName());
	        	 sql.append( field.getName());
	        	 sql.append(" = (?)");
			     sql.append(", ");
			     flag = true;
	       }
	         System.out.println(field.getName());    System.out.println(value);
	        
		   }
		   
		   if(flag) {
			   
			   
		   sql.deleteCharAt(sql.length()-2);
		   sql.append("where expense_id = (?);");
		   System.out.println(sql.toString());
		   
		  

		   PreparedStatement pr = con.prepareStatement(sql.toString());
		   
		   

		   int i=1;
		   for(Field field : clazz.getDeclaredFields()) {
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if(value!=null && !(field.getName().equals("expense_id")) && !(field.getName().equals("merchant_id")) && !(field.getName().equals("currency_id"))&& !(field.getName().equals("item"))) {
	        	 System.out.println(value);
	        	 pr.setString(i,value.toString());
	        	 i++;
	         }else if(value!=null  && !(field.getName().equals("expense_id"))&& !(field.getName().equals("item"))) {
	        	 System.out.println( "else if" +value);
	        	 pr.setInt(i,(int)value);
	        	 i++;
	         }
	        
	         }
		   
		   
		   pr.setInt(i++,id);
		  // pr.setInt(i,user_id);
		   
		   pr.executeUpdate();
		   
		   }
		 }catch(Exception e) {
			 
		 }
		
}
	
//	---------------------------------------admin ----------------------------------------------------------
	public expenseJson getExpenseAdmin(int id) {
		 expenseJson expense = new expenseJson();
			
			try {
			
				String sql = " select e.expense_id,cu.currency_id,cu.currency_code,cu.currency_symbol,m.merchant_id,m.merchant_name,"
						+ "e.expense_date,e.expense_total,u.user_name,u.user_role from expense e left join merchant m on e.merchant_id=m.merchant_id "
						+ "inner join currency cu on e.currency_id = cu.currency_id inner join users u on u.user_id= e.user_id where e.expense_id = "+id;
				
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			
			
			if(rs.next()) {
				
				expense.setExpense_id(rs.getInt(1));
				expense.setCurrency_id(rs.getInt(2));
				expense.setCurrency_code(rs.getString(3));	
				expense.setCurrency_symbol(rs.getString(4));	
		     	expense.setMerchant_id(rs.getInt(5));
				expense.setMerchant_name(rs.getString(6));
				expense.setExpense_date(rs.getString(7));
				expense.setExpense_total(rs.getString(8)+" "+rs.getString(4));
				expense.setUser_name(rs.getString(9));
				expense.setUser_role(rs.getString(10));
				
				
				String sql2 = " select lt.expense_item_id,lt.description,lt.amount,"
						+ " ct.category_id,ct.category_name from line_item lt inner join"
						+ " category ct on lt.category_id = ct.category_id where expense_id="+rs.getInt(1);
				
				
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery(sql2);
				
				 List<line_itemJson> items = new ArrayList<>();
				
				while(rs2.next()) {
					line_itemJson item = new line_itemJson();
					item.setExpense_item_id(rs2.getInt(1));
					item.setDescription(rs2.getString(2));
					item.setAmount(rs2.getString(3)+" "+rs.getString(4));
					item.setCategory_id(rs2.getInt(4));
					item.setCategory_name(rs2.getString(5));
					items.add(item);
				}

				expense.setItem(items);

			
			}
			}catch(Exception e){
				System.out.println(e);
			}
			
		return expense;
	}
	public List<expenseJson> getExpensesAdmin() {
		 List<expenseJson> expenses = new ArrayList<>();
			
			try {
			
				String sql = " select e.expense_id,cu.currency_id,cu.currency_code,cu.currency_symbol,m.merchant_id,m.merchant_name,"
						+ "e.expense_date,e.expense_total,u.user_name,u.user_role from expense e left join merchant m on e.merchant_id=m.merchant_id "
						+ "inner join currency cu on e.currency_id = cu.currency_id inner join users u on u.user_id= e.user_id order by e.expense_id";
					
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				
				
				while(rs.next()) {
		
				expenseJson expense = new expenseJson();
				expense.setExpense_id(rs.getInt(1));
				expense.setCurrency_id(rs.getInt(2));
				expense.setCurrency_code(rs.getString(3));	
				expense.setCurrency_symbol(rs.getString(4));	
		     	expense.setMerchant_id(rs.getInt(5));
				expense.setMerchant_name(rs.getString(6));
				expense.setExpense_date(rs.getString(7));
				expense.setExpense_total(rs.getString(8)+" "+rs.getString(4));
				expense.setUser_name(rs.getString(9));
				expense.setUser_role(rs.getString(10));
				
				String sql2 = " select lt.expense_item_id,lt.description,lt.amount,\r\n"
						+ " ct.category_id,ct.category_name from line_item lt inner join\r\n"
						+ " category ct on lt.category_id = ct.category_id where expense_id="+rs.getInt(1)+" order by lt.expense_item_id";
				
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery(sql2);
				
				 List<line_itemJson> items = new ArrayList<>();
				
				while(rs2.next()) {
					line_itemJson item = new line_itemJson();
					item.setExpense_item_id(rs2.getInt(1));
					item.setDescription(rs2.getString(2));
					item.setAmount(rs2.getString(3)+" "+rs.getString(4));
					item.setCategory_id(rs2.getInt(4));
					item.setCategory_name(rs2.getString(5));
					items.add(item);
				}
				
				expense.setItem(items);
				expenses.add(expense);
			
			
			}
			}catch(Exception e){
				System.out.println(e);
			}
			
		return expenses;

	}

	
}
