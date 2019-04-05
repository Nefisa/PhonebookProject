package phonebook.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import phonebook.beans.User;
import phonebook.main.ConnectionManager;

public class UserManager {

	private static Connection conn = ConnectionManager.getInstance().getConnection();

	public static User createUser(String firstName, String lastName, String password) throws SQLException {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		
		String sql = "INSERT INTO user(firstName, lastName, password) VALUES(?,?,?)";
		ResultSet rs = null;
		
		try (
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				) {
			
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, password);
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				rs = stmt.getGeneratedKeys();
				rs.next();
				int newKey = rs.getInt(1);
				user.setUserID(newKey);
			} else {
				System.out.println("No rows affected");
			}
			
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null)
				rs.close();
		}

		return user;
	}

	public static boolean checkLogin(int userID, String password) throws SQLException {

		String sql = "SELECT password FROM user WHERE userID = ?";
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, userID);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					System.out.println("Login successfull.");
					return true;
				} else {
					System.out.println("Wrong password.Try again.");
					return false;
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null)
				rs.close();
		}
		return true;
	}
	

}
