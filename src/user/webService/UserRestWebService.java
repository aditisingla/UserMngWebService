package user.webService;

import java.util.ArrayList;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import user.controller.UserWSManager;
import user.dto.User;

import com.google.gson.Gson;

@Path("/UserWebService")
public class UserRestWebService {
	@GET
	@Path("/getUserList")
	@Produces("application/json")
	public String getUserList() {
		String userData = null;
		try {
			ArrayList<User> userList = new ArrayList<User>();
			UserWSManager userManager = new UserWSManager();
			userList = userManager.getUserList();
			Gson gson = new Gson();
			userData = gson.toJson(userList);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
		}
		return userData;
	}
	
	@GET
	@Path("/addUser/{firstName}/{lastName}")
	@Produces("application/json")
	public String addUser(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
		String userData = null;
		User userObj = new User();
		userObj.setFirstName(firstName);
		userObj.setLastName(lastName);
		try {
			UserWSManager userManager = new UserWSManager();
			userManager.addUser(userObj);
			userData = getUserList();
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
		}
		return userData;
	}
}