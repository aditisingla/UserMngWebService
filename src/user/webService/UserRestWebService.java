package user.webService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import user.controller.UserWSManager;
import user.dto.User;

import com.google.gson.Gson;

@Path("/UserWebService")
public class UserRestWebService {
	static Logger logger = Logger.getLogger(UserRestWebService.class);
	
	@GET
	@Path("/getUserList")
	@Produces("application/json")
	public String getUserList() {
		String userData = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Gson gson = new Gson();
		try {
			ArrayList<User> userList = new ArrayList<User>();
			UserWSManager userManager = new UserWSManager();
			userList = userManager.getUserList();
			responseMap.put("data", userList);
		} catch (Exception e) {
			logger.error("Error occured while getting user list: "+e.getMessage(), e);
			responseMap.put("errorMessage", e.getMessage());
		} finally {
			userData = gson.toJson(responseMap);
		}
		return userData;
	}
	
	@GET
	@Path("/addUser/{firstName}/{lastName}")
	@Produces("application/json")
	public String addUser(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
		String userData = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Gson gson = new Gson();
		User userObj = new User();
		userObj.setFirstName(firstName);
		userObj.setLastName(lastName);
		try {
			UserWSManager userManager = new UserWSManager();
			userManager.addUser(userObj);
			responseMap.put("result", "success");
		} catch (Exception e) {
			logger.error("Error occured while adding user: ", e);
			responseMap.put("errorMessage", e.getMessage());
		} finally {
			userData = gson.toJson(responseMap);
		}
		return userData;
	}
}