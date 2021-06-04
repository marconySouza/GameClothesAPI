/**
 * 
 */
package br.gameClothes.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import br.gameClothes.database.DBConnection;
import br.gameClothes.model.User;

/**
 * @author marcony.souza + luiz.viana
 *
 */
public class UserDAO {

	public boolean alterUser(String idUser, String username, String password) {

		if (idUser == null)
			return false;

		try {
			Connection con = DBConnection.getConnection();
			Statement stm = con.createStatement();

			String query = "UPDATE USERS SET USERNAME = '" + username + "', PASSWORD = '" + password
					+ "' where id_user = " + idUser;

			stm.execute(query);
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean createUser(String username, String password) throws Exception {
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();

		try {

			String query = "SELECT COUNT(id_user) as qtd FROM USERS WHERE USERNAME = '" + username + "'";
			ResultSet rs = stm.executeQuery(query);
			rs.next();
			int count = rs.getInt("qtd");
			if (count > 0)
				throw new Exception("Já existe um usuário com esse username");

			
			String queryInsert = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES ('" + username + "', '" + password + "'" + ")";
			stm.execute(queryInsert);
			return true;
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public User authenticateUser(String username, String password) throws Exception {
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();
		User user = new User();
		try {

			String query = "SELECT ID_USER, USERNAME FROM USERS WHERE USERNAME = '" + username
					+ "' and PASSWORD = '" + password + "'";
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				user.setIdUser(rs.getInt("id_user"));
				user.setUsername(rs.getString("username"));
			}
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User findUser(Integer idUser) throws Exception {
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();
		User user = new User();

		try {

			String query = "SELECT ID_USER, USERNAME FROM USERS WHERE ID_USER = " + idUser;
			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				user.setIdUser(rs.getInt("id_user"));
				user.setUsername(rs.getString("username"));
			}

			return user;

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	public User findUser(String username) throws Exception {
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();
		User user = new User();

		try {

			String query = "SELECT ID_USER, USERNAME FROM USERS WHERE USERNAME = '" + username + "'";
			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				user.setIdUser(rs.getInt("id_user"));
				user.setUsername(rs.getString("username"));
			}

			return user;

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
		return null;
	}
}
