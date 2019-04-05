package phonebook.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import phonebook.beans.PersonPB;
import phonebook.beans.User;
import phonebook.main.ConnectionManager;

public class PhonebookManager {

	private static Connection conn = ConnectionManager.getInstance().getConnection();

	public static void displayAllRows(User user) throws SQLException {

		String sql = "SELECT * FROM phonebook WHERE userID = ?";
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, user.getUserID());
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer row = new StringBuffer();
				row.append(rs.getInt("ID") + " ");
				row.append(rs.getString("firstName") + " ");
				row.append(rs.getString("lastName") + " ");
				row.append(rs.getString("phone") + " ");
				row.append(rs.getString("mobile") + " ");
				row.append(rs.getInt("userID"));
				System.out.println(row.toString());
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null)
				rs.close();
		}
	}

	public static boolean deletePerson(int ID, User user) {

		String sql = "DELETE * FROM phonebook WHERE ID = ? AND userID = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, ID);
			stmt.setInt(2, user.getUserID());
			int affected = stmt.executeUpdate();
			if (affected == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	public static PersonPB insertPerson(String firstName, String lastName, String phone, String mobile, User user)
			throws SQLException {

		PersonPB person = new PersonPB();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhone(phone);
		person.setMobile(mobile);
		person.setUserID(user.getUserID());

		String sql = "INSERT INTO phonebook(firstName, lastName, phone, mobile, userID) VALUES(?,?,?,?,?)";
		ResultSet keys = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, person.getFirstName());
			stmt.setString(2, person.getLastName());
			stmt.setString(3, person.getPhone());
			stmt.setString(4, person.getMobile());
			stmt.setInt(5, user.getUserID());

			int affected = stmt.executeUpdate();
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				person.setPhonebookID(newKey);
			} else {
				System.err.println("No rows affected!");
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (keys != null)
				keys.close();
		}
		System.out.println("New person added to Phonebook!");
		return person;
	}

	public static boolean updateFirstName(int personID, String firstName, User user) throws SQLException {

		String sql = "UPDATE phonebook SET firstName = ? WHERE ID =  ? AND userID = ?";
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, firstName);
			stmt.setInt(2, personID);
			stmt.setInt(3, user.getUserID());
			int affected = stmt.executeUpdate();

			if (affected == 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			if (rs != null)
				rs.close();
		}
	}

	public static boolean updateLastName(int personID, String lastName, User user) throws SQLException {

		String sql = "UPDATE phonebook SET lastName = ? WHERE ID =  ? AND userID = ?";
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, lastName);
			stmt.setInt(2, personID);
			stmt.setInt(3, user.getUserID());
			int affected = stmt.executeUpdate();

			if (affected == 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			if (rs != null)
				rs.close();
		}
	}

	public static boolean updatePhoneNumber(int personID, String phoneNumber, User user) throws SQLException {

		String sql = "UPDATE phonebook SET lastName = ? WHERE ID =  ? AND userID = ?";
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, phoneNumber);
			stmt.setInt(2, personID);
			stmt.setInt(3, user.getUserID());
			int affected = stmt.executeUpdate();

			if (affected == 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			if (rs != null)
				rs.close();
		}
	}

	public static boolean updateMobileNumber(int personID, String mobileNumber, User user) throws SQLException {

		String sql = "UPDATE phonebook SET lastName = ? WHERE ID =  ? AND userID = ?";
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, mobileNumber);
			stmt.setInt(2, personID);
			stmt.setInt(3, user.getUserID());
			int affected = stmt.executeUpdate();

			if (affected == 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			if (rs != null)
				rs.close();
		}
	}

	public static ArrayList<String> searchByFirstName(String firstName, User user) throws SQLException {

		String sql = "SELECT firstName, lastName, phone, mobile FROM phonebook WHERE firstName = ? AND userID = ?";
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<>();

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, firstName);
			stmt.setInt(2, user.getUserID());
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer row = new StringBuffer();
				row.append(rs.getString("firstName") + " ");
				row.append(rs.getString("lastName") + " ");
				row.append(rs.getString("phone") + " ");
				row.append(rs.getString("mobile"));
				list.add(row.toString());
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null)
				rs.close();
		}

		return list;
	}

	public static ArrayList<String> searchByLastName(String lastName, User user) throws SQLException {

		String sql = "SELECT ID, firstName, lastName, phone, mobile FROM phonebook WHERE lastName = ? AND userID = ?";
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<>();

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, lastName);
			stmt.setInt(2, user.getUserID());
			rs = stmt.executeQuery();
			while (rs.next()) {
				StringBuffer row = new StringBuffer();
				row.append(rs.getString("firstName") + " ");
				row.append(rs.getString("lastName") + " ");
				row.append(rs.getString("phone") + " ");
				row.append(rs.getString("mobile"));
				list.add(row.toString());
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null)
				rs.close();
		}
		return list;
	}

}
