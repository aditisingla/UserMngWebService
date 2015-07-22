package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import user.dto.User;

public class UserDAO {
	static Logger logger = Logger.getLogger(UserDAO.class);
	
	public ArrayList<User> getUserList(Connection connection) {
		ArrayList<User> userData = new ArrayList<User>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM user ORDER BY id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User userObject = new User();
				userObject.setId((long) rs.getInt("Id"));
				userObject.setFirstName(rs.getString("firstName"));
				userObject.setLastName(rs.getString("lastName"));
				userData.add(userObject);
			}
		} catch (SQLException e) {
			logger.error("Error occured while getting user list: ", e);
			throw new RuntimeException(e.getMessage(), e);
		}
		return userData;
	}

	public User addUser(Connection connection, User userObject) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("Insert into User (firstName, lastName) values (?,?)");
			ps.setString(1, userObject.getFirstName());
			ps.setString(2, userObject.getLastName());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error occured while adding user: ", e);
			throw new RuntimeException(e.getMessage(), e);
		}
		return userObject;
	}
}
