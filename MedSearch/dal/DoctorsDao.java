package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.*;
import model.Doctors;
import model.Users;

public class DoctorsDao extends UsersDao {


protected ConnectionManager connectionManager;

private static DoctorsDao instance = null;
protected DoctorsDao() {
	super();
}
public static DoctorsDao getInstance() {
	if(instance == null) {
		instance = new DoctorsDao();
	}
	return instance;
}

public Doctors create(Doctors doctor) throws SQLException {
	create(new Users(doctor.getUserName(), doctor.getPassword(), doctor.getPhone(), doctor.getStreet1(), doctor.getStreet2(), 
			doctor.getCity(), doctor.getState(), doctor.getZipcode()));
	
	String insertDoctor =
		"INSERT INTO Doctors(UserName, FirstName, LastName) " +
		"VALUES(?,?, ?);";
	Connection connection = null;
	PreparedStatement insertStmt = null;
	try {
		connection = connectionManager.getConnection();
		insertStmt = connection.prepareStatement(insertDoctor);
		insertStmt.setString(1, doctor.getUserName());
		insertStmt.setString(2, doctor.getFirstName());
		insertStmt.setString(3, doctor.getLastName());
		
		insertStmt.executeUpdate();
		
		return doctor;
		
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


public Doctors getDoctorFromUserName(String userName) throws SQLException {
	String selectDoctor =
		"SELECT Doctors.UserName AS UserName, FirstName, LastName, Password, Phone, Street1, Street2, City, State, Zipcode" +
		"FROM Doctors INNER JOIN Users " +
		"  ON Doctors.UserName = Users.UserName " +
		"WHERE Doctors.UserName=?;";
	Connection connection = null;
	PreparedStatement selectStmt = null;
	ResultSet results = null;
	try {
		connection = connectionManager.getConnection();
		selectStmt = connection.prepareStatement(selectDoctor);
		selectStmt.setString(1, userName);
		results = selectStmt.executeQuery();
		if(results.next()) {
			String resultUserName = results.getString("UserName");
			String firstName = results.getString("FirstName");
			String lastName = results.getString("LastName");
			String password = results.getString("Password");
			String phone = results.getString("Phone");
			String street1 = results.getString("Street1");
			String street2 = results.getString("Street2");
			String city = results.getString("City");
			String state = results.getString("State");
			String zipcode = results.getString("Zipcode");


			Doctors doctor = new Doctors(resultUserName, firstName, lastName, password,
					phone, street1, street2, city, state, zipcode);
			return doctor;
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


public Doctors delete(Doctors doctor) throws SQLException {
	String deleteBlogComment = "DELETE FROM Doctors WHERE UserName=?;";
	Connection connection = null;
	PreparedStatement deleteStmt = null;
	try {
		connection = connectionManager.getConnection();
		deleteStmt = connection.prepareStatement(deleteBlogComment);
		deleteStmt.setString(1, doctor.getUserName());
		deleteStmt.executeUpdate();
		super.delete(doctor);

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

}