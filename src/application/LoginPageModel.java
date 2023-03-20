package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LoginPageModel {
	
private boolean authentication;
public static String userType; 

	public LoginPageModel() {
		this.authentication= false;
	}
	
	public void retrieveData(String UserName, String PassWord) {
		try {
			Connection con = ConnectDB.getConnection();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM USERS WHERE USERNAME = '"+UserName+"' AND PASSWORD = '"+PassWord+"'");
			while(rs.next()) {
				
				if(rs.getString(1).equals(UserName) && rs.getString(2).equals(PassWord)) {
					this.authentication = true;
					userType = UserName;
					System.out.println(userType);
				}
				else {
					this.authentication = false;
				}
			}
		}
		catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
	}
	
	public boolean isAuthentication() {
		return authentication;
	}
}
