package dao;

import java.sql.Connection;
import model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;

public class categoryDao {

Connection con = connectionDao.getConnection();

	
//	list all the category
     public List<category> getCategories() {
        List<category> categories = new ArrayList<>();
		
		try {
		
		String sql = "select * from category order by category_id";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			category each_category = new category();
			each_category.setCategoryt_id(rs.getInt(1));
			each_category.setCategory_name(rs.getString(2));
			each_category.setCategory_description(rs.getString(3));
			categories.add(each_category);
		}
		}catch(Exception e){
			System.out.println(e);
		}
		return categories;
		
	}

// add category
     public int addCategory(category c) {
		String sql = "insert into category(category_name,category_description) values(?,?)";
		String query = "select MAX(category_id) from category";
		int id=0;
        try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,c.getCategory_name());
			st.setString(2,c.getCategory_description());
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

// list a particular category
	public category getCategory(int id) {
		category category = new category();
		
			try {
			
			String sql = "select * from category where category_id="+id;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				category.setCategoryt_id(rs.getInt(1));
				category.setCategory_name(rs.getString(2));
				category.setCategory_description(rs.getString(3));
			}
			
			}catch(Exception e){
				System.out.println(e);
			}
			return category;
	}
	
//update the details of merchant

	public void updateCategory(category c) throws IllegalArgumentException, IllegalAccessException {
		 int id = c.getCategoryt_id();
		 showFields(c,id);
		   
	     // Get the all field objects of User class
		
	}
	public void showFields(Object o,int id) throws IllegalArgumentException, IllegalAccessException {
		
		 StringBuilder sql = new StringBuilder("update category set ");
		   
		 try {
		 Class<?> clazz = o.getClass();

		   for(Field field : clazz.getDeclaredFields()) {
	            // get value of the fields
	     
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if(value!=null && !(field.getName().equals("categoryt_id")) ) {
	        	 System.out.println( field.getName());
	        	 sql.append( field.getName());
	        	 sql.append(" = (?)");
			     sql.append(", ");
			      
	         }
	       }
		   sql.deleteCharAt(sql.length()-2);
		   sql.append("where category_id = (?);");
		   System.out.println(sql.toString());

		   PreparedStatement pr = con.prepareStatement(sql.toString());

		   int i=1;
		   for(Field field : clazz.getDeclaredFields()) {
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if(value!=null && !(field.getName().equals("categoryt_id"))) {
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
		   
		  
	//delete the merchant

	public boolean deleteCategory(int id) {
		String sql = "delete from category where category_id=?";
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


}
