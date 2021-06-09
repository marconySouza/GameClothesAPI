package br.gameClothes.model;

import java.sql.Date;

public class Product {

		private Integer idProduct;
		
		private Date createDate;
	
		private User user;
		
		private String title;
		
		private String image;
		
		private String game;
		
		private double price;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getGame() {
			return game;
		}

		public void setGame(String game) {
			this.game = game;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public Integer getIdProduct() {
			return idProduct;
		}

		public void setIdProduct(Integer idProduct) {
			this.idProduct = idProduct;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	
}
