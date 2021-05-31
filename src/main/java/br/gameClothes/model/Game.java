package br.gameClothes.model;

public enum Game {


    FORTNITE("Fortnite"),
    CS("Counter Strike Global Ofensive"),
    FREEFIRE("FreeFire"),
    ROCKETLEAGUE("Rocket League"),
    LOL("League of Legends"),
    VALORANT("Valorant - Riot Games");
	
    private String descricao;

    Game(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
	
