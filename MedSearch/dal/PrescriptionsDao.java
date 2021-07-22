package dal;

import model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class PrescriptionsDao {
	protected ConnectionManager connectionManager;
	private static PrescriptionsDao instance = null;
	
	protected PrescriptionsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static PrescriptionsDao getInstance() {
		if (instance == null) {
			instance = new PrescriptionsDao();
		}
		return instance;
	}
	
	public Prescriptions create(Prescriptions prescription) throws SQLException {
		String insertPrescription = "INSERT INTO Prescriptions(Customer, Drug, " +
							     "FillDate, Doctor) VALUES(?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPrescription, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, prescription.getCustomerUserName());
			insertStmt.setString(2, prescription.getDrugId());
			insertStmt.setDate(3, (Date) prescription.getFillDate());
			insertStmt.setString(4, prescription.getDoctorUserName());
			insertStmt.executeUpdate();
			
			// retrieve the auto-generated ID
			resultKey = insertStmt.getGeneratedKeys();
			int prescriptionId = -1;
			if (resultKey.next()) {
				prescriptionId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			// update the prescriptions instance with the auto-generated ID
			prescription.setPrescriptionId(prescriptionId);
			return prescription;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	public Prescriptions delete(Prescriptions prescription) throws SQLException {
		String deletePrescription = "DELETE FROM Prescriptions WHERE PrescriptionName = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePrescription);
			deleteStmt.setInt(1, prescription.getPrescriptionId());
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
	
	public Prescriptions getPrescriptionByPrescriptionId(String prescriptionId) throws SQLException {
		String selectPrescription = "SELECT * FROM Prescriptions WHERE PrescriptionId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPrescription);
			selectStmt.setString(1, prescriptionId);
			results = selectStmt.executeQuery();
			
			if (results.next()) {
				int resultPrescriptionId = results.getInt("PrescriptionId");
				String resultCustomer = results.getString("Customer");
				String resultDrug = results.getString("Drug");
				Date resultFillDate = results.getDate("FillDate");
				String resultDoctor = results.getString("Doctor");
				Prescriptions prescription = new Prescriptions(resultPrescriptionId, resultCustomer, resultDrug, resultFillDate, resultDoctor);
				return prescription;
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