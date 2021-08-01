package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.*;

import model.*;
import dao.*;

@Path("/expenses")
public class expenseController {

	//dao access
	expenseDao exp_dao = new expenseDao();
	userDao user_dao = new userDao();
	
	//response object 
	returnJson ret = new returnJson();
	returnJson.returnExpense ret_exp = ret.new returnExpense();
	returnJson.returnItem ret_lt = ret.new returnItem();

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnExpense getExpenses(@QueryParam("user_id") int user_id) {
		
		//user validation
	     user_id = exp_dao.getUserById(user_id);
	     if(user_id==0) {
	    	 ret_exp.setMessage("Invalid User Id");
	    	 return  ret_exp;
	     }
	     
	     user u =user_dao.getUser(user_id);
	     List<expenseJson> expenses;
		if(u.getUser_role().equals("Admin")) {
			  expenses = exp_dao.getExpensesAdmin();
				 if(expenses.size()==0) {
						ret_exp.setMessage("No Expense Found");
						return ret_exp;
					}
		}else {
			  expenses = exp_dao.getExpenses(user_id);
				 if(expenses.size()==0) {
						ret_exp.setMessage("No Expense Found");
						return ret_exp;
					}
		}
	    
	 
		
		ret_exp.setMessage("Success");
		ret_exp.setExpenses(expenses);
		return ret_exp;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnExpense getExpense(@QueryParam("user_id") int user_id,@PathParam("id") int id) {
		//user validation
		  user_id = exp_dao.getUserById(user_id);
		  if(user_id==0) {
		    	 ret_exp.setMessage("User ID is not Exist");
		    	 return  ret_exp;
		     }
	    user u =user_dao.getUser(user_id);
	    expenseJson e;
	    if(u.getUser_role().equals("Admin")) {
	    	 //expense validation for the user_id
			e =exp_dao.getExpenseAdmin(id);
			
	    }else {
	    	 //expense validation for the user_id
			e =exp_dao.getExpense(id,user_id);
			if(e.getExpense_id()==0) {
				ret_exp.setMessage("Expense ID is not Exist");
				return ret_exp;
			}
	    }
		
		
		//success  message
		
		List<expenseJson> exp = new ArrayList<>();
		
		ret_exp.setMessage("Success");
		exp.add(e);
		ret_exp.setExpenses(exp);
		return ret_exp;
	}
	
	@POST
	@Path("/addExpense")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnExpense addExpense(expense e,@QueryParam("user_id") int user_id) {
		//user validation
		  user_id = exp_dao.getUserById(user_id);
		  if(user_id==0) {
		    	 ret_exp.setMessage("User ID is not Exist");
		    	 return  ret_exp;
		     }
		  
		  //input validation
		if(e.getCurrency_id()==null || e.getExpense_date()==null) {
			ret_exp.setMessage("Invalid data provided for expense");
	    	 return  ret_exp;
		
		}
		
		List<line_item> lt = e.getItem();
		
		for(line_item l : lt) {
			System.out.println(l.getAmount());
			if(l.getCategory_id()==null || l.getAmount()==0.0 ){
				ret_exp.setMessage("Invalid data provided for line_item");
		    	 return  ret_exp;
			}
			
		}
		
        int id = exp_dao.addExpense(e,user_id);
        expenseJson added_exp = exp_dao.getExpense(id,user_id);
        
        //success message
        ret_exp.setMessage("The expense has been recorded.");
		List<expenseJson> exp = new ArrayList<>();
		exp.add(added_exp);
		ret_exp.setExpenses(exp);
		
		return ret_exp;


	}
	@PUT
	@Path("/updateExpense")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnExpense updateExpense(expense e,@QueryParam("user_id") int user_id) throws IllegalArgumentException, IllegalAccessException {
		//user validation
		user_id = exp_dao.getUserById(user_id);
		  if(user_id==0) {
		    	 ret_exp.setMessage("User ID is not Exist");
		    	 return  ret_exp;
		     }
		
		  
		//expense validation user u =user_dao.getUser(user_id);
		   user u =user_dao.getUser(user_id);
		  expenseJson exp_up;
		    if(u.getUser_role().equals("Admin")) {
		    	exp_up = (exp_dao.getExpenseAdmin(e.getExpense_id()));
		    }else {
		    	exp_up = (exp_dao.getExpense(e.getExpense_id(),user_id));
		    }
		
		if(exp_up.getExpense_id()==0) {
			ret_exp.setMessage("Expense ID is not Exist");
			return ret_exp;
		}
		
		//category validation
        List<line_item> lt = e.getItem();
		
		for(line_item l : lt) {
			
			if(!exp_dao.isValidCategory(e.getExpense_id(),l.getExpense_item_id())) {
				ret_exp.setMessage("expense_item_id is not Exist");
				return ret_exp;
			}
		}
		     
		   exp_dao.updateExpense(e,user_id);
		   exp_up = (exp_dao.getExpense(e.getExpense_id(),user_id));
		 
		 
	        //success message
	        ret_exp.setMessage("The expense has been updated.");
			List<expenseJson> exp = new ArrayList<>();
			exp.add(exp_up);
			ret_exp.setExpenses(exp);
			
			return ret_exp;


	}
	
	@DELETE
	@Path("/deleteExpense/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnExpense deleteExpense(@PathParam("id") int id,@QueryParam("user_id") int user_id) {
		
		//user validation
		user_id = exp_dao.getUserById(user_id);
		  if(user_id==0) {
		    	 ret_exp.setMessage("User ID is not Exist");
		    	 return  ret_exp;
		     }
		
		 //expense validation
		  user u =user_dao.getUser(user_id);
		  expenseJson exp_del;
		    if(u.getUser_role().equals("Admin")) {
		    	 exp_del = (exp_dao.getExpenseAdmin(id));
		    }else {
		    	 exp_del = (exp_dao.getExpense(id,user_id));
		    }
		if(exp_del.getExpense_id()==0) {
			ret_exp.setMessage("Expense ID is not Exist");
			return ret_exp;
		}
		exp_dao.deleteExpense(id,user_id);
		
		   //success message
        ret_exp.setMessage("The expense has been deleted.");
		
		return ret_exp;
		
	}


}
