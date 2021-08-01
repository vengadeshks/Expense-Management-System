package controller;

import java.util.*;
import dao.*;
import model.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/categories")
public class categoryController {
	
	categoryDao cat_dao = new categoryDao();
	
	//response object 
		returnJson ret = new returnJson();
		returnJson.returnCategory ret_cat = ret.new returnCategory();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnCategory getCategories() {
		
		List<category> categories = cat_dao.getCategories();
		if(categories.size()==0) {
			ret_cat.setMessage("No Category Found");
			return ret_cat;
		}
		ret_cat.setMessage("success");
		ret_cat.setCategories(categories);
		return ret_cat;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnCategory getCategory(@PathParam("id") int id) {
		
		category c =cat_dao.getCategory(id);
		if(c.getCategoryt_id()==0) {
			ret_cat.setMessage("Category Id not exist");
			return ret_cat;
		}
		List<category> cat = new ArrayList<>();
		cat.add(c);
		ret_cat.setMessage("success");
		ret_cat.setCategories(cat);
		
		return ret_cat;
	}
	
	@POST
	@Path("/addCategory")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnCategory addCategory(category c) {
		
		if(c.getCategory_name()==null) {
			ret_cat.setMessage("Invalid data provided ");
	    	 return  ret_cat;
		}

		int id = cat_dao.addCategory(c);
		c = cat_dao.getCategory(id);
		List<category> cat = new ArrayList<>();
		cat.add(c);
		ret_cat.setMessage("The category has been recorded");
		ret_cat.setCategories(cat);
		
		return ret_cat;
	}
	
	@PUT
	@Path("/updateCategory")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnCategory updateCategory(category c) throws IllegalArgumentException, IllegalAccessException {
		if(c.getCategoryt_id()==0) {
			ret_cat.setMessage("Invalid data provided");
	    	 return  ret_cat;
		}
        category cat_up = (cat_dao.getCategory(c.getCategoryt_id()));
		if(cat_up.getCategoryt_id()==0) {
			ret_cat.setMessage("Category Id not exist");
			return ret_cat;
		}
		cat_dao.updateCategory(c);
	    cat_up = (cat_dao.getCategory(c.getCategoryt_id()));
	    List<category> cat = new ArrayList<>();
		cat.add(cat_up);
		ret_cat.setMessage("The category has been updated");
		ret_cat.setCategories(cat);
		
		return ret_cat;
	}
	
	@DELETE
	@Path("/deleteCategory/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnCategory deleteCategory(@PathParam("id") int id) {
		category cat_del = (cat_dao.getCategory(id));
		if(cat_del.getCategoryt_id()==0) {
			ret_cat.setMessage("Category Id not exist");
			return ret_cat;
		}
		boolean del = cat_dao.deleteCategory(id);
		if(del) {
			ret_cat.setMessage("The Category has been deleted");
		}else {
			ret_cat.setMessage("As Category has expense ,can't delete");
		}
		
		
		return ret_cat;
	}
}
