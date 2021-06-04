/**
 * 
 */
package br.gameClothes.controller;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gameClothes.enums.Game;
import br.gameClothes.model.Product;
import br.gameClothes.model.User;
import br.gameClothes.persistence.ProductDAO;
import br.gameClothes.persistence.UserDAO;

/**
 * @author marcony.souza
 *
 */
@RestController
@RequestMapping("/services")
public class ProductController {

	final ProductDAO productDAO = new ProductDAO();
	final UserDAO userDAO = new UserDAO();

	/**
	 * marcony.souza
	 * 
	 * @throws Exception
	 * 
	 */
	@PostMapping(value = "/signIn", produces = { "application/json",
			"application/xml" }, consumes = "application/json")
	public String signIn(@RequestBody String body) throws Exception {

		JSONObject json = new JSONObject(body);
		String username = json.getString("username");
		String password = json.getString("password");
		User user = userDAO.authenticateUser(username, password);

		if (user == null) {
			return HttpStatus.BAD_REQUEST.toString();
		}

		return "usuario " + username + " Autenticado com sucesso";

	}

	@PostMapping(value = "/alterProduct", produces = { "application/json",
			"application/xml" }, consumes = "application/json")
	public String alterProduct(@RequestBody String body) throws Exception {

		try {
			JSONObject json = new JSONObject(body);
			Integer idProduct = json.getInt("id_product");
			String title = json.getString("title");
			String image = json.getString("image");
			Double price = json.getDouble("price");
		boolean r =	productDAO.alterProduct(idProduct, title, image, price);


			if(!r) {
				return "Erro ao criar produto";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Produto alterado com sucesso!";

	}

	@PostMapping(value = "/createProduct", produces = { "application/json",
			"application/xml" }, consumes = "application/json")
	public String createProduct(@RequestBody String body) throws Exception {

		try {
			JSONObject json = new JSONObject(body);
			Integer idUser = json.getInt("id_user");
			String title = json.getString("title");
			String image = json.getString("image");
			Double price = json.getDouble("price");
			String game = json.getString("game");
			boolean r = productDAO.createProduct(idUser, title, image,Game.valueOf(game), price);
			
			if(!r) {
				return "Erro ao criar produto";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Produto criado com sucesso!";

	}

	@PutMapping(value = "/deleteProduct", produces = { "application/json",
			"application/xml" }, consumes = "application/json")
	public String deleteProduct(@RequestBody String body) throws Exception {


		try {
			JSONObject json = new JSONObject(body);
			Integer idProduct = json.getInt("id_product");
			boolean r = productDAO.deleteProduct(idProduct);


			if(!r) {
				return "Erro ao criar produto";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Produto Deletado com sucesso!";

	}

	@GetMapping(value = "/my-store")
	public ResponseEntity<List<Product>> getMyList(@RequestParam(name = "id") Integer id) throws SQLException {

		List<Product> products;
		try {
			products = productDAO.readMyStore(id);

		} catch (Exception e) {
			return new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

	/**
	 * marcony.souza
	 * 
	 */
	@GetMapping("/productById")
	public ResponseEntity<Product> getProduct(@RequestParam(name = "id") Integer id) throws SQLException {

		Product product;
		try {
			product = productDAO.findProduct(id);
		} catch (Exception e) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@GetMapping("/product")
	public ResponseEntity<List<Product>> getProductList() throws SQLException {

		List<Product> products;
		try {
			products = productDAO.readStore();
		} catch (Exception e) {
			return new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}
}