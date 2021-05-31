/**
 * 
 */
package br.gameClothes.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gameClothes.model.Product;

/**
 * @author marcony.souza
 *
 */
@RestController
@RequestMapping("/services")
public class GameClothesServices {

	/**
	 * marcony.souza
	 * 
	 * @throws Exception
	 * 
	 */
	@PostMapping(value="/sign-in", produces={"application/json","application/xml"}, consumes="application/json")
	public String signIn(@RequestBody String body) throws Exception {
		return body;
		
	}

	/**
	 * marcony.souza
	 * 
	 * @throws SQLException
	 * 
	 */
	@GetMapping(value="/my-store", produces={"application/json","application/xml"}, consumes="application/json")
	public ResponseEntity<Map<String, List<?>>> getMyList(@RequestBody String body) throws SQLException {
		return null;
	
	}

	/**
	 * marcony.souza
	 * 
	 */
	@GetMapping("/product/{id}")
	public ResponseEntity<List<Product>> getProduct() throws SQLException {
		
		ResponseEntity<List<Product>> list = null;
		return list;
	
	}
	}