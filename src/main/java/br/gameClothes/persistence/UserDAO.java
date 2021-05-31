/**
 * 
 */
package br.gameClothes.persistence;

import java.lang.reflect.GenericArrayType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;

import br.gameClothes.database.DBConnection;

/**
 * @author marcony.souza
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

			String queryInsert = "INSERT INTO USERS (ID_USER, USERNAME, PASSWORD, FLAG_ADMIN) VALUES (DEFAULT, '"
					+ username + "', '" + password + "', 0)";
			stm.execute(queryInsert);
			return true;
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public boolean authenticateUser(String username, String password) throws Exception {
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();
		try {

			String query = "SELECT COUNT(id_user) as qtd FROM USERS WHERE USERNAME = '" + username
					+ "' and PASSWORD = '" + password + "'";
			ResultSet rs = stm.executeQuery(query);
			rs.next();
			int qtd = rs.getInt("qtd");
			if (qtd == 1)
				return true;
			else
				throw new Exception("Verifique se o login ou senha estão corretos");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * marcony.souza
	 * 
	 * @throws SQLException
	 * 
	 */
	public boolean saveInMyList(String idEntertainment, String idUser, String type) throws SQLException {
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();
		try {
			String query = "";
			if (type.equalsIgnoreCase("filme")) {
				query += "INSERT INTO SAVED_MOVIES (ID_SAVED_MOVIES, ID_MOVIE, ID_USER) VALUES (DEFAULT, "
						+ idEntertainment + ", " + idUser + ")";
			} else {
				query += "INSERT INTO SAVED_TV_SHOWS (ID_TV_SHOW_SAVED, ID_TV_SHOW, ID_USER) VALUES (DEFAULT, "
						+ idEntertainment + ", " + idUser + ")";
			}
			stm.execute(query);
			return true;
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}
		return false;
	}
}
