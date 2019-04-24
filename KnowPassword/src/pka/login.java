package pka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class login {
	Scanner sc = new Scanner(System.in);
	Connection con = get_connection();
	
	//get connection
	public static Connection get_connection() {
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PasswordKnowingApp?useTimezone =true&serverTimezone=PST", "root", "Pilli@123");
			return con;
		}
		catch(Exception e) {
			System.out.println("Not connected");
			e.printStackTrace();
		}
		return null;
	}

	//creating account
	public void createAccount() throws SQLException {
		//take details to create a account
		System.out.println("Create Username: ");
		String username = sc.next();
		System.out.println("Create password: ");
		String password = sc.next();
		//insert into table
		String query = "insert into PasswordKnowingApp.Login(UserID,Password)values(?,?)";
		
		PreparedStatement creation_Status = con.prepareStatement(query);
		creation_Status.setString(1, username);
		creation_Status.setString(2, password);
		creation_Status.executeUpdate();
		login l = new login();
		l.selectingOption();
	}
	
	
	//login to app with username and password
	public void getLoginToApp() throws SQLException {
		System.out.println("Enter your username: ");
		String username = sc.next();
		System.out.println("Enter your password: ");
		String password = sc.next();
		String query = "Select Password from PasswordKnowingApp.Login where UserID=?";
		PreparedStatement creation_Status = con.prepareStatement(query);
		creation_Status.setString(1, username);
		ResultSet rs = creation_Status.executeQuery();
		String dbpwd = null;
		while(rs.next()) {
			//System.out.println("something"+rs.getString("Password"));
			 dbpwd = rs.getString("Password");
		}
		if(Objects.equals(password, dbpwd)) {
			//give all passwords list
			System.out.println("Successfully logged in ");
			
		}
		else {
			System.out.println("Wrong password");
		}
		
		login l = new login();
		l.selectingOption();
	}
	
	public void selectingOption() throws SQLException {
		System.out.println("1. Sign up");
		System.out.println("2.Login"); 
		int option = sc.nextInt();
		login l = new login();
		switch(option) {
			case 1:
				l.createAccount();
			
			case 2: 
				l.getLoginToApp();
		}
	}
	
	public static void main(String args[]) throws SQLException {
		login.get_connection();
		login l = new login();
		l.selectingOption();
		
		
		
		
	}

}
