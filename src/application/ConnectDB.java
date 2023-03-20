package application;

import java.sql.*;

public class ConnectDB {

public static Connection getConnection() throws ClassNotFoundException, SQLException {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-QLH9M8T:1521:xe","SYSTEM","cdb3zg%2");
	return con;
	}
}
