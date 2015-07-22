package user.controller;

import java.sql.Connection;
import java.util.ArrayList;

import user.dao.UserDAO;
import user.dto.User;
import user.util.DatabaseConnection;

public class UserWSManager {
	Connection conn = null;
	UserDAO userDAO = new UserDAO();

	public User addUser(User contact) {
		User userObject = null;
		try {
			conn = DatabaseConnection.getConnection();
			userObject = userDAO.addUser(conn, contact);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection(conn);
		}
		return userObject;
	}

	public ArrayList<User> getUserList() {
		ArrayList<User> userList = new ArrayList<User>();
		try {
			conn = DatabaseConnection.getConnection();
			userList = userDAO.getUserList(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection(conn);
		}
		return userList;
	}
}
