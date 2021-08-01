package controller;

import java.util.ArrayList;
import java.util.List;

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

import dao.*;
import model.*;


@Path("/currencies")
public class currencyController {

	
	currencyDao cur_dao = new currencyDao();
	//response object 
	returnJson ret = new returnJson();
	returnJson.returnCurrency ret_cur = ret.new returnCurrency();
	returnJson.returnAnalytics ret_an = ret.new returnAnalytics();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnCurrency getCurrency() {
		
		List<currency> currencies = cur_dao.getCurrency();
		if(currencies.size()==0) {
			ret_cur.setMessage("No Category Found");
			return ret_cur;
		}
		ret_cur.setMessage("success");
		ret_cur.setCurrencies(currencies);
		return ret_cur;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnCurrency getcurrency(@PathParam("id") int id) {
		
		currency c =cur_dao.getCurrency(id);
		if(c.getCurrency_id()==0) {
			ret_cur.setMessage("Currency Id not exist");
			return ret_cur;
		}
		List<currency> cur = new ArrayList<>();
		cur.add(c);
		ret_cur.setMessage("success");
		ret_cur.setCurrencies(cur);
		return ret_cur;
	}
	
	@POST
	@Path("/addCurrency")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnCurrency addCurrency(currency c) {
		if(c.getCurrency_code()==null || c.getCurrency_symbol()==null) {
			ret_cur.setMessage("Invalid data provided ");
			return ret_cur;
		}

		int id = cur_dao.addCurrency(c);
		c = cur_dao.getCurrency(id);
		List<currency> cur = new ArrayList<>();
		cur.add(c);
		ret_cur.setMessage("The currency has been recorded");
		ret_cur.setCurrencies(cur);
		return ret_cur;
	}
	
	@PUT
	@Path("/updateCurrency")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnCurrency updateCurrency(currency c) throws IllegalArgumentException, IllegalAccessException {
		
		if(c.getCurrency_id()==0) {
			ret_cur.setMessage("Invalid data provided ");
			return ret_cur;
		}
		
        currency cur_up = (cur_dao.getCurrency(c.getCurrency_id()));
		if(cur_up.getCurrency_id()==0) {
			ret_cur.setMessage("Currency Id not exist");
			return ret_cur;
		}
		cur_dao.updateCurrency(c);
	    cur_up = (cur_dao.getCurrency(c.getCurrency_id()));
	    List<currency> cur = new ArrayList<>();
		cur.add(cur_up);
		ret_cur.setMessage("The currency has been updated");
		ret_cur.setCurrencies(cur);
		return ret_cur;
	}
	
	@DELETE
	@Path("/deleteCurrency/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnCurrency deleteCurrency(@PathParam("id") int id) {
		currency cur_del = (cur_dao.getCurrency(id));
		if(cur_del.getCurrency_id()==0) {
			ret_cur.setMessage("Currency Id not exist");
			return ret_cur;
		}
		boolean del = cur_dao.deleteCurrency(id);
		if(del) {
			ret_cur.setMessage("The currency has been deleted");
		}else {
			ret_cur.setMessage("As currency has expense ,can't delete");
		}
		
		
		
		return ret_cur;
	}
	
	userDao user_dao = new userDao();
	
	@GET
	@Path("/getAnalytics")
	public  returnJson.returnAnalytics getAnalytics(@QueryParam("user_id") int user_id) {
		user user = (user_dao.getUser(user_id));
		if(user.getUser_id()==0) {
			ret_an.setMessage("User ID is not exist");
			return ret_an;
		}
		
		List<currencyAnalytics> cur = cur_dao.getAnalytics(user);
		ret_an.setMessage("success");
		ret_an.setCurrencies(cur);
		return ret_an;
		
	}
	
}
