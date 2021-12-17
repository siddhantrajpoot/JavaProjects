package mydrivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	public static Connection connectJdbc(String dbName) throws ClassNotFoundException {
		Connection con = null;
		final String url = "jdbc:mysql://localhost:3306/" + dbName;
		final String username = "root";
		final String password = "root";
		final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(url, username, password);
			// for checking the successful connection
		} catch (SQLException ex) {
			System.out.println("unable to connect with database");
			con=null;
		}
		return con;
	}

}
