package pka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class login {
	public static void logIntoApp(String username, String password) {
		
	}
	public static void createAccount() throws SQLException {
		System.out.println("Create Username: ");
		Scanner sc = new Scanner(System.in);
		String username = sc.next();
		System.out.println("Create password: ");
		String password = sc.next();
		String query = "insert into PasswordKnowingApp.Login(UserID,Password)values(?,?)";
		Connection con = get_connection();
		PreparedStatement creation_Status = con.prepareStatement(query);
		creation_Status.setString(1, username);
		creation_Status.setString(2, password);
		creation_Status.executeUpdate();
	}
	public static Connection get_connection() {
		//get connection
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PasswordKnowingApp?useTimezone =true&serverTimezone=PST", "root", "Pilli@123");
			//System.out.println("connected");
			return con;
		}
		catch(Exception e) {
			System.out.println("Not connected");
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String args[]) throws SQLException {
		//login.get_connection();
		login.createAccount();
		
	}

}
