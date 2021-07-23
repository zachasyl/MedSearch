package dal;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Doctors;
import model.Pharmacies;
import model.Users;

public class PharmaciesDao extends UsersDao {


protected ConnectionManager connectionManager;

private static PharmaciesDao instance = null;
protected PharmaciesDao() {
	super();
}
public static PharmaciesDao getInstance() {
	if(instance == null) {
		instance = new PharmaciesDao();
	}
	return instance;
}

public Pharmacies create(Pharmacies pharmacy) throws SQLException {
	create(new Users(pharmacy.getUserName(), pharmacy.getPassword(), pharmacy.getPhone(), pharmacy.getStreet1(), pharmacy.getStreet2(), 
			pharmacy.getCity(), pharmacy.getState(), pharmacy.getZipcode()));
	
	String insertPharmacy =
		"INSERT INTO Pharmacies(UserName, OpenTime, CloseTime) " +
		"VALUES(?,?, ?, ?);";
	Connection connection = null;
	PreparedStatement insertStmt = null;
	try {
		connection = connectionManager.getConnection();
		insertStmt = connection.prepareStatement(insertPharmacy);
		insertStmt.setString(1, pharmacy.getUserName());
		insertStmt.setString(2, pharmacy.getPharmacyName());
		insertStmt.setDate(3, new java.sql.Date(pharmacy.getOpenTime().getTime()));
		insertStmt.setDate(4, new java.sql.Date(pharmacy.getCloseTime().getTime()));

		
		insertStmt.executeUpdate();
		
		return pharmacy;
		
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

// This is a list because according to our schema its possible pharmacies may have the same name
// the only unique identifier is the username for each pharmacy.
public List<Pharmacies> getPharmacyFromPharmacyName(String pharmacyname) throws SQLException {
	List<Pharmacies> pharmacies = new ArrayList<Pharmacies>();

	String selectDoctor =
		"SELECT Pharmacies.UserName AS UserName, PharmacyName, OpenTime, CloseTime, Password, Phone, Street1, Street2, City, State, Zipcode" +
		"FROM Pharmacies INNER JOIN Users " +
		"  ON Pharmacies.UserName = Users.UserName " +
		"WHERE Pharmacies.PharmacyName=?;";
	Connection connection = null;
	PreparedStatement selectStmt = null;
	ResultSet results = null;
	try {
		connection = connectionManager.getConnection();
		selectStmt = connection.prepareStatement(selectDoctor);
		selectStmt.setString(1, pharmacyname);
		results = selectStmt.executeQuery();
		while(results.next()) {
			String username = results.getString("UserName");
			String resultPharmacyName = results.getString("PharmacyName");
			Time opentime = results.getTime("OpenTime");
			Time closetime = results.getTime("OpenTime");
			String password = results.getString("Password");
			String phone = results.getString("Phone");
			String street1 = results.getString("Street1");
			String street2 = results.getString("Street2");
			String city = results.getString("City");
			String state = results.getString("State");
			String zipcode = results.getString("Zipcode");

			
			Pharmacies pharmacy = new Pharmacies(username, password, phone, street1, street2, city, state, zipcode, resultPharmacyName,
					opentime, closetime);
			pharmacies.add(pharmacy);
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
	return pharmacies;
}


public Pharmacies pharmacy(Pharmacies pharmacy) throws SQLException {
	String deletePharamcacy = "DELETE FROM Pharmacies WHERE UserName=?;";
	Connection connection = null;
	PreparedStatement deleteStmt = null;
	try {
		connection = connectionManager.getConnection();
		deleteStmt = connection.prepareStatement(deletePharamcacy);
		deleteStmt.setString(1, pharmacy.getUserName());
		deleteStmt.executeUpdate();
		super.delete(pharmacy);

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