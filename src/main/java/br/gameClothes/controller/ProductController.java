/**
 * 
 */
package br.gameClothes.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gameClothes.model.Product;
import br.gameClothes.model.User;
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
	@PostMapping(value = "/sign-in", produces = { "application/json",
			"application/xml" }, consumes = "application/json")
	public ResponseEntity<User> signIn(@RequestBody User body) throws Exception {
		
		User user = userDAO.authenticateUser(body.getUsername(), body.getPassword());
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@GetMapping(value = "/my-store")
	public ResponseEntity<List<Product>> getMyList() throws SQLException {
		
		List<Product> products = productDAO.readMyStore();

		if (products == null) {
			return new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		;

		
		return null;

	}

	/**
	 * marcony.souza
	 * 
	 */
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@RequestParam(name = "id") Integer id) throws SQLException {

		Product product = productDAO.findProduct(id);

		if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Product>(product, HttpStatus.OK);
		;

	}

	@GetMapping("/product")
	public ResponseEntity<List<Product>> getProductList() throws SQLException {

		List<Product> products = productDAO.readStore();

		if (products == null) {
			return new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		;

	}
}