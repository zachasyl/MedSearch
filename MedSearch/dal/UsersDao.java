package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Users;



public class UsersDao {

protected ConnectionManager connectionManager;

private static UsersDao instance = null;
protected UsersDao() {
	connectionManager = new ConnectionManager();
}
public static UsersDao getInstance() {
	if(instance == null) {
		instance = new UsersDao();
	}
	return instance;
}

public Users create(Users user) throws SQLException {
	String insertUser =
		"INSERT INTO Users(UserName, Password, Phone, Street1, Street2, City, State, Zipcode) " +
		"VALUES(?,?,?, ?, ?, ?, ?, ?);";
	Connection connection = null;
	PreparedStatement insertStmt = null;
	ResultSet resultKey = null;
	try {
		connection = connectionManager.getConnection();
		insertStmt = connection.prepareStatement(insertUser,
			Statement.RETURN_GENERATED_KEYS);
		//insertStmt.setInt(1, restaurant.getRestaurantId());
		insertStmt.setString(1, user.getUserName());
		insertStmt.setString(2, user.getPassword());
		insertStmt.setString(3, user.getPhone());
		insertStmt.setString(4, user.getStreet1());
		insertStmt.setString(5, user.getStreet2());
		insertStmt.setString(6, user.getCity());
		insertStmt.setString(7, user.getState());
		insertStmt.setString(8, user.getZipcode());
		
		
		
		insertStmt.executeUpdate();
		
		return user;
		
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if(connection != null) {
			connection.close();
		}
		if(insertStmt != null) {
			insertStmt.close();
		}
		
	}
}



public Users delete(Users user) throws SQLException {
	String deleteBlogComment = "DELETE FROM Users WHERE UserName=?;";
	Connection connection = null;
	PreparedStatement deleteStmt = null;
	try {
		connection = connectionManager.getConnection();
		deleteStmt = connection.prepareStatement(deleteBlogComment);
		deleteStmt.setString(1, user.getUserName());
		deleteStmt.executeUpdate();

		return null;
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if(connection != null) {
			connection.close();
		}
		if(deleteStmt != null) {
			deleteStmt.close();
		}
	}
}


public Users updatePhone(Users user, String newPhone) throws SQLException {
	String updateUser = "UPDATE Users SET Phone=? WHERE UserName=?;";
	Connection connection = null;
	PreparedStatement updateStmt = null;
	try {
		connection = connectionManager.getConnection();
		updateStmt = connection.prepareStatement(updateUser);
		updateStmt.setString(1, newPhone);
		updateStmt.setString(2, user.getUserName());
		updateStmt.executeUpdate();
		
		// Update the person param before returning to the caller.
		user.setPhone(newPhone);
		return user;
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if(connection != null) {
			connection.close();
		}
		if(updateStmt != null) {
			updateStmt.close();
		}
	}
}

		

public Users getUserByUserName(String username) throws SQLException {
	String selectBlogComment =
		"SELECT * FROM Users WHERE UserName=?;";
	Connection connection = null;
	PreparedStatement selectStmt = null;
	ResultSet results = null;
	try {
		connection = connectionManager.getConnection();
		selectStmt = connection.prepareStatement(selectBlogComment);
		selectStmt.setString(1, username);
		results = selectStmt.executeQuery();
		if(results.next()) {
			String Resultusername = results.getString("UserName");
			String password = results.getString("Password");
			String phone = results.getString("Phone");
			String street1 = results.getString("Street1");
			String street2 = results.getString("Street2");
			String city = results.getString("City");
			String state = results.getString("Street2");
			String zipcode = results.getString("Zipcode");

		
			
			Users user = new Users(Resultusername, password, phone,
					street1, street2, city, state, zipcode);
					
			return user;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if(connection != null) {
			connection.close();
		}
		if(selectStmt != null) {
			selectStmt.close();
		}
		if(results != null) {
			results.close();
		}
	}
	return null;
}



public List<Users> getUsersById(String username) throws SQLException {
	List<Users> users = new ArrayList<Users>();
	String selectBlogComment =
		"SELECT UserName, Password, Phone, Street1, Street2, City, State, Zipcode" +
		"FROM Users " +
		"WHERE UserName=?;";
	Connection connection = null;
	PreparedStatement selectStmt = null;
	ResultSet results = null;
	try {
		connection = connectionManager.getConnection();
		selectStmt = connection.prepareStatement(selectBlogComment);
		selectStmt.setString(1, username);
		results = selectStmt.executeQuery();
		while(results.next()) {
			String resultUserName = results.getString("UserName");
			String password = results.getString("Password");
			String phone = results.getString("Phone");
			String street1 = results.getString("Street1");
			String street2 = results.getString("Street2");
			String city = results.getString("City");
			String state = results.getString("Street2");
			String zipcode = results.getString("Zipcode");

			Users user = new Users(resultUserName, password, phone,
					street1, street2, city, state, zipcode);
					
			
			users.add(user);
			return users;
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if(connection != null) {
			connection.close();
		}
		if(selectStmt != null) {
			selectStmt.close();
		}
		if(results != null) {
			results.close();
		}
	}
	return null;
	}


}
