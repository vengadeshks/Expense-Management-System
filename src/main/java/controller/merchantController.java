package controller;

import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import dao.*;
import model.*;


@Path("/merchants")
public class merchantController {
merchantDao mer_dao = new merchantDao();

         //response object 
		returnJson ret = new returnJson();
		returnJson.returnMerchant ret_mer = ret.new returnMerchant();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnMerchant getMerchants() {
		
		List<merchant> merchants = mer_dao.getMerchants();
		if(merchants.size()==0) {
			ret_mer.setMessage("No Merchants Found");
			return ret_mer;
		}
		ret_mer.setMessage("success");
		ret_mer.setMerchants(merchants);
		return ret_mer;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public returnJson.returnMerchant getMerchant(@PathParam("id") int id) {
		
		merchant m =mer_dao.getMerchant(id);
		if(m.getMerchant_id()==0) {
			ret_mer.setMessage("Merchant Id not exist");
			return ret_mer;
		}
		List<merchant> mer = new ArrayList<>();
		mer.add(m);
		ret_mer.setMessage("success");
		ret_mer.setMerchants(mer);
		
		return ret_mer;
		}
	
	@POST
	@Path("/addMerchant")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnMerchant addMerchant(merchant m) {

		
		if( m.getMerchant_name() == null ) {
			ret_mer.setMessage("Invalid data provided ");
	    	 return  ret_mer;
		}
		int id = mer_dao.addMerchant(m);
		m = mer_dao.getMerchant(id);
		List<merchant> mer = new ArrayList<>();
		mer.add(m);
		ret_mer.setMessage("The merchant has been recorded");
		ret_mer.setMerchants(mer);
		
		return ret_mer;
	}
	
	@PUT
	@Path("/updateMerchant")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnMerchant updateMerchant(merchant m) throws IllegalArgumentException, IllegalAccessException {
		if(m.getMerchant_id()==0) {
			ret_mer.setMessage("Invalid data provided ");
	    	 return  ret_mer;
		}
		
		merchant mer_up = (mer_dao.getMerchant(m.getMerchant_id()));
		if(mer_up.getMerchant_id()==0) {
			ret_mer.setMessage("Merchant Id not exist");
			return ret_mer;
		}
		mer_dao.updateMerchant(m);
		mer_up = (mer_dao.getMerchant(m.getMerchant_id()));
		List<merchant> mer = new ArrayList<>();
		mer.add(mer_up);
		ret_mer.setMessage("The merchant has been updated");
		ret_mer.setMerchants(mer);
		
		return ret_mer;
	}
	
	@DELETE
	@Path("/deleteMerchant/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public returnJson.returnMerchant deleteMerchant(@PathParam("id") int id) {
		merchant mer_del = (mer_dao.getMerchant(id));
		if(mer_del.getMerchant_id()==0) {
			ret_mer.setMessage("Merchant Id not exist");
	    	return  ret_mer;
		}
		boolean del = mer_dao.deleteMerchant(id);
		if(del) {
			ret_mer.setMessage("The expense has been deleted");
		}else {
			ret_mer.setMessage("As merchant has expense ,can't delete");
		}
		
		
		return ret_mer;
	}

}
