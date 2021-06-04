/**
 * 
 */
package br.gameClothes.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.gameClothes.database.DBConnection;
import br.gameClothes.enums.Game;
import br.gameClothes.model.Product;
import br.gameClothes.model.User;

/**
 * @author luiz.viana
 *
 */
public class ProductDAO {

	static private UserDAO userDAO;

	//Alterar um produto
	public boolean alterProduct(Integer idProduct, String title, String image, Double price) {

		if (idProduct == null)
			return false;
		try {
			Connection con = DBConnection.getConnection();
			Statement stm = con.createStatement();

			String query = "UPDATE PRODUCTS SET TITLE = '" + title + "'";
						

			if(price != null) {
				
					query += ", PRICE = " + price;
				
			}
			if(image != null) {
				if(!image.trim().equalsIgnoreCase("")) {
					query += ", IMAGE = '" + image + "'";
				}
			}
			
			query += " where id_product = " + idProduct;
			
			stm.execute(query);
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	//Deletar um produto
	public boolean deleteProduct(Integer idProduct) throws Exception {

		if (idProduct == null) {
			return false;
		}

		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();

		try {

			String queryDelete = "DELETE FROM PRODUCTS WHERE ID_PRODUCT = " + idProduct;
			stm.execute(queryDelete);
			return true;
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
		return false;
	}
	
	//Criar um produto
	public boolean createProduct(Integer idUser, String title, String image, Game game, double price) throws Exception {

		if (idUser == null || game == null) {
			return false;
		}

		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();

		try {

			String query = "SELECT COUNT(id_product) as qtd FROM PRODUCTS WHERE TITLE = '" + title + "'";
			ResultSet rs = stm.executeQuery(query);
			rs.next();
			int count = rs.getInt("qtd");
			if (count > 0)
				throw new Exception("Já existe um produto com esse título");

			String queryInsert = "INSERT INTO PRODUCTS (ID_USER, TITLE, IMAGE, GAME, PRICE, CREATE_DATE) VALUES ('"
					+ idUser + "', '" + title + "', '" + image + "', '" + game.getDescricao() + "', " + price
					+ ", current_date)";
			stm.execute(queryInsert);
			return true;
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
		return false;
	}

	//Pesquisar um produto pelo ID
	public Product findProduct(Integer idProduct) throws Exception {
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();
		Product product = new Product();
		userDAO = new UserDAO();

		try {

			String query = "SELECT ID_PRODUCT, ID_USER, TITLE, PRICE, IMAGE, CREATE_DATE, GAME FROM PRODUCTS WHERE ID_PRODUCT = " + idProduct;
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				product.setIdProduct(rs.getInt("id_product"));
				product.setUser(userDAO.findUser(rs.getInt("ID_USER")));
				product.setTitle(rs.getString("title"));
				product.setPrice(rs.getDouble("price"));
				product.setImage(rs.getString("image"));
				product.setCreateDate(rs.getDate("create_date"));
				product.setGame(Game.findGame(rs.getString("game")));
			}

			return product;

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
		return null;
	}

	//Retornar a loja inteira 
	public List<Product> readStore() throws Exception {
		List<Product> store = new ArrayList<Product>();
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();
		userDAO = new UserDAO();
		try {

			String query = "SELECT ID_PRODUCT, ID_USER, TITLE, PRICE, IMAGE, CREATE_DATE, GAME FROM PRODUCTS";
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				Product product = new Product();
				product.setIdProduct(rs.getInt("id_product"));
				product.setUser(userDAO.findUser(rs.getInt("ID_USER")));
				product.setTitle(rs.getString("title"));
				product.setPrice(rs.getDouble("price"));
				product.setImage(rs.getString("image"));
				product.setCreateDate(rs.getDate("create_date"));
				product.setGame(Game.findGame(rs.getString("game")));

				store.add(product);
			}

			return store;
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
		return null;
	}

	//Retornar a loja de acordo com o ID do usuário
	public List<Product> readMyStore(Integer idUser) throws Exception {
		List<Product> myStore = new ArrayList<Product>();
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();
		userDAO = new UserDAO();

		try {

			String query = "SELECT ID_PRODUCT, ID_USER, TITLE, PRICE, IMAGE, CREATE_DATE, GAME FROM PRODUCTS WHERE USER = " + idUser;
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				Product product = new Product();
				product.setIdProduct(rs.getInt("id_product"));
				product.setUser(userDAO.findUser(rs.getInt("ID_USER")));
				product.setTitle(rs.getString("title"));
				product.setPrice(rs.getDouble("price"));
				product.setImage(rs.getString("image"));
				product.setCreateDate(rs.getDate("create_date"));
				product.setGame(Game.findGame(rs.getString("game")));

				myStore.add(product);
			}

			return myStore;
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
		return null;
	}

}
