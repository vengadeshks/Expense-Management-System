package controller;

import java.util.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.*;
import model.*;



@Path("/users")
public class userController {
	
   userDao user_dao = new userDao();
   expenseDao exp_dao = new expenseDao();
   
   //response object 
   
    returnJson ret = new returnJson();
	returnJson.returnUser ret_user = ret.new returnUser();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnUser getUsers() {
		
		List<user> users = user_dao.getUsers();
		if(users.size()==0) {
			ret_user.setMessage("No Users Found");
			return ret_user;
		}
		
		ret_user.setMessage("success");
		ret_user.setUsers(users);
		return ret_user;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnUser getUser(@PathParam("id") int id) {
		
		user u =user_dao.getUser(id);
		if(u.getUser_id()==0) {
			ret_user.setMessage("User ID is not exist");
			return ret_user;
		}
		List<user> user = new ArrayList<>();
		user.add(u);
		ret_user.setMessage("success");
		ret_user.setUsers(user);
		return ret_user;
	}
	
	@POST
	@Path("/addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnUser addUser(user u) {

		if(u.getUser_name()==null || u.getUser_email()==null|| u.getUser_role()==null) {
			ret_user.setMessage("Invalid data provided");
			return ret_user;
		}
		
		int id = user_dao.addUser(u);
		u = user_dao.getUser(id);
		
		List<user> user = new ArrayList<>();
		user.add(u);
		ret_user.setMessage("The User details has been recorded");
		ret_user.setUsers(user);
		return ret_user;
	}
	
	@PUT
	@Path("/updateUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnUser updateUser(user u) throws IllegalArgumentException, IllegalAccessException {
		
		if(u.getUser_id()==0 ) {
			ret_user.setMessage("Invalid data provided");
			return ret_user;
		}

		
        user user_up = (user_dao.getUser(u.getUser_id()));
		if(user_up.getUser_id()==0) {
			ret_user.setMessage("User ID is not exist");
			return ret_user;
		}
		user_dao.updateUser(u);
		user_up = (user_dao.getUser(u.getUser_id()));
		List<user> user = new ArrayList<>();
		user.add(user_up);
		ret_user.setMessage("The User record has been updated");
		ret_user.setUsers(user);
		return ret_user;
	}
	
	@DELETE
	@Path("/deleteUser/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnUser deleteUser(@PathParam("id") int id) {
		user user_del = (user_dao.getUser(id));
		if(user_del.getUser_id()==0) {
			ret_user.setMessage("User ID is not exist");
			return ret_user;
		}
		
		boolean del = user_dao.deleteUser(id);
		if(del) {
			ret_user.setMessage("The User record has been deleted");
		}else {
			ret_user.setMessage("As user has expense ,can't delete");
		}
		
		return ret_user;
	}
	

	@Context private HttpServletRequest request;

	@POST
	@Path("/validate/{email}")
	@Produces(MediaType.TEXT_PLAIN)
	public String validate(@PathParam("email") String email) {
		int user_id = user_dao.getUser(email);
		HttpSession session = request.getSession();
		user user = (user_dao.getUser(user_id));
		
		System.out.println(user.getUser_role());
		session.setAttribute("user_id",user.getUser_id());
		session.setAttribute("user_role",user.getUser_role());
		session.setAttribute("user_name",user.getUser_name());
		System.out.print( user.getUser_role());
		return user.getUser_role();
	}
	@GET
	@Path("/logout")
	public void logout(@PathParam("email") String email) {
		System.out.print("logout");
		HttpSession session = request.getSession();
		session.removeAttribute("user_id");
		session.removeAttribute("user_role");
		session.removeAttribute("user_name");
		session.invalidate();
	}
	
}
