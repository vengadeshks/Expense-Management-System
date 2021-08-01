package dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class connectionDao {
	static Connection con=null;
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3308/expensedb";
		String user = "root";
		String password = "root";

		try 
		{
		 	 Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection(url, user, password);
		} 
		catch (Exception e) 
		{
             System.out.println(e);
		}
		return con;
	}

}
