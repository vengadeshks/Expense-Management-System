package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import model.*;

import dao.*;

@Path("/lineItem")
public class line_itemController {
	line_itemDao lt_dao = new line_itemDao();
	returnJson ret = new returnJson();
	returnJson.returnItem ret_lt = ret.new returnItem();
	
	//------------------------------------------------------Line_item --------------------------------------------------------
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnItem getItems(@QueryParam("expense_id") int exp_id) {
		List<line_item> items = new ArrayList<>();
		items =lt_dao.getItems(exp_id);
		if(items.size()==0) {
			ret_lt.setMessage("Item not exist");
	    	return  ret_lt;
		}
		
	
		ret_lt.setMessage("success");
		ret_lt.setItem(items);

		return ret_lt;
	}
	
	//add new line item
	@POST
	@Path("/addItem")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnItem addItem(line_item lt ,@QueryParam("expense_id") int exp_id){
		System.out.print("called add new item");
		if(lt.getCategory_id()==null || lt.getAmount()==0.0){
			ret_lt.setMessage("Invalid data provided for line_item");
	    	return  ret_lt;
		}
		String message = lt_dao.addItem(lt,exp_id);   
		ret_lt.setMessage(message);
		
		return ret_lt;
	}
	
	@DELETE
	@Path("/deleteItem/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnItem deleteItem(@PathParam("id") int id,@QueryParam("expense_id") int exp_id) {

		boolean del = lt_dao.deleteItem(id,exp_id);
		if(del) {
			ret_lt.setMessage("The item has been deleted");
		}else {
			ret_lt.setMessage("As item has expense ,can't delete");
		}
		
		
		
		return ret_lt;
	}
}
