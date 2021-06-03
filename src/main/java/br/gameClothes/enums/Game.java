package br.gameClothes.enums;

public enum Game {

	FORTNITE("Fortnite"), CS("Counter Strike Global Ofensive"), FREEFIRE("FreeFire"), ROCKETLEAGUE("Rocket League"),
	LOL("League of Legends"), VALORANT("Valorant - Riot Games");

	private String descricao;

	Game(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Game findGame(String descricao) {

		for (Game game : values()) {
			if (game.getDescricao().equals(descricao)) {
				return game;
			}
		}
		return null;
	}
}
