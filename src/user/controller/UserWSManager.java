package user.controller;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import user.dao.UserDAO;
import user.dto.User;
import user.util.DatabaseConnection;

public class UserWSManager {
	static Logger logger = Logger.getLogger(UserWSManager.class);
	Connection conn = null;
	UserDAO userDAO = new UserDAO();

	public User addUser(User contact) {
		User userObject = null;
		try {
			conn = DatabaseConnection.getConnection();
			userObject = userDAO.addUser(conn, contact);
		} catch (Exception e) {
			logger.error("Error occured while adding user: ", e);
			throw new RuntimeException("Error occured while adding user:"+e.getMessage(), e);
		} finally {
			DatabaseConnection.closeConnection(conn);
		}
		return userObject;
	}

	public ArrayList<User> getUserList() {
		ArrayList<User> userList = new ArrayList<User>();
		try {
			conn = DatabaseConnection.getConnection();
			if (conn != null) {
				userList = userDAO.getUserList(conn);
			} 
		} catch (Exception e) {
			logger.error("Error occured while getting user list: "+e.getMessage(), e);
			throw new RuntimeException("Error occured while getting user list:"+e.getMessage(), e);
		} finally {
			if (conn != null) {
				DatabaseConnection.closeConnection(conn);
			}
		}
		return userList;
	}
}
