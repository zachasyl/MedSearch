package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ConnectionManager {
	
	private final String user = "yu_hsuan";
	private final String password = "cs5200group4";
	private final String hostName = "medsearch.cuzo0rpeaecu.us-east-2.rds.amazonaws.com";
	private final int port = 3306;
	private final String schema = "MedSearch";
	private final String timezone = "UTC";
	
	/**
	 * Get the connection to the DB instance.
	 */
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Properties connectionProperties = new Properties();
			connectionProperties.put("user", this.user);
			connectionProperties.put("password", this.password);
			connectionProperties.put("serverTimezone", this.timezone);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
			connection = DriverManager.getConnection(
					"jdbc:mysql://" + this.hostName + ":" + this.port + "/" + this.schema + "?useSSL=false",
					connectionProperties);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return connection;
	}
	
	/**
	 * Close the connection to the DB instance.
	 */
	public void closeConnection(Connection connection) throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}