package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class userDao {

	Connection con = connectionDao.getConnection();
	
	
	public List<user> getUsers() {
		 List<user> users = new ArrayList<>();
			
			try {
			
			String sql = "select * from users order by user_id";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				user each_user = new user();
				each_user.setUser_id(rs.getInt(1));
				each_user.setUser_name(rs.getString(2));
				each_user.setUser_email(rs.getString(3));
				each_user.setUser_role(rs.getString(4));
				users.add(each_user);
			}
			}catch(Exception e){
				System.out.println(e);
			}
			return users;
	}


// add category
     public int addUser(user u) {
		String sql = "insert into users(user_name,user_email,user_role) values(?,?,?)";
		String query = "select MAX(user_id) from users";
		int id=0;
        try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,u.getUser_name());
			st.setString(2,u.getUser_email());
			st.setString(3,u.getUser_role());
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
	public user getUser(int id) {
		user u = new user();
		
			try {
			
			String sql = "select * from users where user_id="+id;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				u.setUser_id(rs.getInt(1));
				u.setUser_name(rs.getString(2));
				u.setUser_email(rs.getString(3));
				u.setUser_role(rs.getString(4));
			}
			
			}catch(Exception e){
				System.out.println(e);
			}
			return u;
	}
	
//update the details of merchant

	public void updateUser(user u) throws IllegalArgumentException, IllegalAccessException {
		 int id = u.getUser_id();
		 showFields(u,id);
		   
	     // Get the all field objects of User class
		
	}
	public void showFields(Object o,int id) throws IllegalArgumentException, IllegalAccessException {
		
		 StringBuilder sql = new StringBuilder("update users set ");
		   
		 try {
		 Class<?> clazz = o.getClass();

		   for(Field field : clazz.getDeclaredFields()) {
	            // get value of the fields
	     
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if(value!=null && !(field.getName().equals("user_id")) ) {
	        	 System.out.println( field.getName());
	        	 sql.append( field.getName());
	        	 sql.append(" = (?)");
			     sql.append(", ");
			      
	         }
	       }
		   sql.deleteCharAt(sql.length()-2);
		   sql.append("where user_id = (?);");
		   System.out.println(sql.toString());

		   PreparedStatement pr = con.prepareStatement(sql.toString());

		   int i=1;
		   for(Field field : clazz.getDeclaredFields()) {
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if(value!=null && !(field.getName().equals("user_id"))) {
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

	public boolean deleteUser(int id) {
		String sql = "delete from users where user_id=?";
        try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,id);
			st.executeUpdate();
			
			
			}catch(Exception e){
				return false;
			}
        return true;
	}


	public int getUser(String email) {
		
		String sql = "select * from users where user_email='"+email+"';";
		
        try {
        	
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
            	return rs.getInt(1);
            }
			
			
			}catch(Exception e){
				System.out.println("false");
				return 0;
			}
        
        return 0;
	}
	

}
