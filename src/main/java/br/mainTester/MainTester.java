package br.mainTester;

import java.sql.SQLException;

import br.gameClothes.model.User;
import br.gameClothes.persistence.ProductDAO;
import br.gameClothes.persistence.UserDAO;

public class MainTester {

	public static void main(String[] args) throws Exception {
		
		
		UserDAO dao = new UserDAO();
		
		User user = new User();
		
		user.setPassword("vivi");
		user.setUsername("Vitoria");
		
		dao.createUser(user.getUsername(), user.getPassword());
		
		System.out.println(dao.findUser(user.getUsername()));
	}

}
