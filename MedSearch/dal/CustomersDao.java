package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customers;
import model.Doctors;
import model.Users;

public class CustomersDao extends UsersDao {

private static CustomersDao instance = null;
protected CustomersDao() {
	super();
}
public static CustomersDao getInstance() {
	if(instance == null) {
		instance = new CustomersDao();
	}
	return instance;
}

public Customers create(Customers customer) throws SQLException {
	create(new Users(customer.getUserName(), customer.getPassword(), customer.getPhone(), customer.getStreet1(), customer.getStreet2(), 
			customer.getCity(), customer.getState(), customer.getZipcode()));
	
	String insertDoctor =
		"INSERT INTO Customers(UserName, FirstName, LastName, Age, Sex) " +
		"VALUES(?,?,?,?,?);";
	Connection connection = null;
	PreparedStatement insertStmt = null;
	try {
		connection = connectionManager.getConnection();
		insertStmt = connection.prepareStatement(insertDoctor);
		insertStmt.setString(1, customer.getUserName());
		insertStmt.setString(2, customer.getFirstName());
		insertStmt.setString(3, customer.getLastName());
		insertStmt.setInt(4, customer.getAge());
		insertStmt.setString(5, customer.getSex().name());
		insertStmt.executeUpdate();
		
		return customer;
		
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


public Customers getCustomerFromUserName(String userName) throws SQLException {
	String selectDoctor =
		"SELECT Customers.UserName AS UserName, FirstName, LastName, Age, Sex, Password, Phone, Street1, Street2, City, State, Zipcode " +
		"FROM Customers INNER JOIN Users " +
		"ON Customers.UserName = Users.UserName " +
		"WHERE Customers.UserName=?;";
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
			int age = results.getInt("Age");
			Customers.Sex sex = Customers.Sex.valueOf(
					results.getString("Sex"));
			String password = results.getString("Password");
			String phone = results.getString("Phone");
			String street1 = results.getString("Street1");
			String street2 = results.getString("Street2");
			String city = results.getString("City");
			String state = results.getString("State");
			String zipcode = results.getString("Zipcode");


			Customers customer = new Customers(resultUserName, password, phone, street1, street2, city,
					state, zipcode, firstName, lastName, age, sex);
			
			return customer;
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


public Customers delete(Customers customer) throws SQLException {
	String deleteBlogComment = "DELETE FROM Customers WHERE UserName=?;";
	Connection connection = null;
	PreparedStatement deleteStmt = null;
	try {
		connection = connectionManager.getConnection();
		deleteStmt = connection.prepareStatement(deleteBlogComment);
		deleteStmt.setString(1, customer.getUserName());
		deleteStmt.executeUpdate();
		super.delete(customer);

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
