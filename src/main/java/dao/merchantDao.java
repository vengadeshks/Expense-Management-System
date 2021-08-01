package dao;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.*;

public class merchantDao {
	
	Connection con = connectionDao.getConnection();

	
//	list all the merchants
	public List<merchant> getMerchants() {
        List<merchant> merchants = new ArrayList<>();
		
		try {
		
		String sql = "select * from merchant order by merchant_id";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			merchant each_merchant = new merchant();
			each_merchant.setMerchant_id(rs.getInt(1));
			each_merchant.setMerchant_name(rs.getString(2));
			each_merchant.setMerchant_description(rs.getString(3));
			merchants.add(each_merchant);
		}
		}catch(Exception e){
			System.out.println(e);
		}
		return merchants;
		
	}

// add merchant
	public int addMerchant(merchant m) {
		String sql = "insert into merchant(merchant_name,merchant_description) values(?,?)";
		String query = "select MAX(merchant_id) from merchant";
		int id=0;
        try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,m.getMerchant_name());
			st.setString(2,m.getMerchant_description());
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

// list a particular merchant
	public merchant getMerchant(int id) {
		merchant merchant = new merchant();
		
			try {
			
			String sql = "select * from merchant where merchant_id="+id;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				merchant.setMerchant_id(rs.getInt(1));
				merchant.setMerchant_name(rs.getString(2));
				merchant.setMerchant_description(rs.getString(3));
			}
			
			}catch(Exception e){
				System.out.println(e);
			}
			return merchant;
	}
	
//update the details of merchant

	public void updateMerchant(merchant m) throws IllegalArgumentException, IllegalAccessException {
		int id = m.getMerchant_id();
		 showFields(m,id);	

	}
	
	public void showFields(Object o,int id) throws IllegalArgumentException, IllegalAccessException {
		
		 StringBuilder sql = new StringBuilder("update merchant set ");
		   
		 try {
		 Class<?> clazz = o.getClass();

		   for(Field field : clazz.getDeclaredFields()) {
	            // get value of the fields
	     
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if(value!=null && !(field.getName().equals("merchant_id"))) {
	        	 System.out.println( field.getName());
	        	 sql.append( field.getName());
	        	 sql.append(" = (?)");
			     sql.append(", ");
			      
	         }
	       }
		   sql.deleteCharAt(sql.length()-2);
		   sql.append("where merchant_id = (?);");
		   System.out.println(sql.toString());

		   PreparedStatement pr = con.prepareStatement(sql.toString());

		   int i=1;
		   for(Field field : clazz.getDeclaredFields()) {
			   field.setAccessible(true);
			   Object value = field.get(o);

	         if(value!=null && !(field.getName().equals("merchant_id"))) {
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

	public boolean deleteMerchant(int id) {
		String sql = "delete from merchant where merchant_id=?";
        try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,id);
			st.executeUpdate();
			
			
			}catch(Exception e){
				return false;
			}
        return true;
	}
		
	

}
