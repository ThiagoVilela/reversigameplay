package com.reversi;

public class Main {
	/***************************** ATRIBUTOS *****************************/
	private Game game = new Game();

	/***************************** MAINZAO DA MASSA *****************************/
	public static void main(String[] args) {
		Game game = new Game();
		game.startGame(0);
	}
	
	/***************************** GET'N SET *****************************/
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
