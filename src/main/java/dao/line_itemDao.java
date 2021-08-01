package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.line_item;

public class line_itemDao {
	
	Connection con = connectionDao.getConnection();
	expenseDao exp = new expenseDao();
	//------------------------------------------------------add item --------------------------------------------------------------
	
		public String addItem(line_item lt, int exp_id) {
			try {
			String sql1 = "insert into line_item(expense_id,category_id,description,amount) values(?,?,?,?)";
	    	PreparedStatement st2 = con.prepareStatement(sql1);
	    	st2.setInt(1, exp_id);
		    st2.setInt(2, lt.getCategory_id());
	    	st2.setString(3, lt.getDescription());
	    	st2.setDouble(4, lt.getAmount());
	    	st2.executeUpdate();
	    	exp.updateTotal(exp_id);
	    	} catch (SQLException e) {
	    		System.out.println(e);
	    		return "Failed";
	        }
	    	return "Success";
		}

		public boolean deleteItem(int id,int exp_id) {
			String sql = "delete from line_item where expense_item_id=?";
	        try {
				
				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1,id);
				st.executeUpdate();
				exp.updateTotal(exp_id);
				
				}catch(Exception e){
					System.out.println(e);
					return false;
				}
	        return true;
		}

		public List<line_item> getItems(int exp_id) {
			List<line_item> lt = new ArrayList<>();
			try {
				String sql = "select * from line_item where expense_id ="+exp_id;
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					line_item l = new line_item();
					l.setExpense_id(rs.getInt(1));
					l.setExpense_item_id(rs.getInt(2));
					l.setCategory_id(rs.getInt(3));
					l.setDescription(rs.getString(4));
					l.setAmount(rs.getDouble(5));
					lt.add(l);
				}
			}catch(Exception e) {
				System.out.println(e);
			}
			return lt;
		}
}
