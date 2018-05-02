package com.reversi;

import java.util.ArrayList;

public class Game {
	/***************************** ATRIBUTOS *****************************/
	private Board board = new Board();
	private Player player1;
	private Player player2;
	
	/****************************** MÉTODOS ******************************/
	/*** Starto o jogo ***/
	public void startGame(int configuration) {
		/*** Reseto o tabuleiro ***/
		this.board.resetBoard();
		/*** Opção Human x Human ***/
		if (configuration == 0) {
			/*** Seto as características dos jogadores ***/
			this.player1 = new Player("Thiago", 'O', 2);
			this.player2 = new Player("Weber", 'X', 2);
			
			/*** Faço um texto de introdução explicativo ***/
			System.out.println("Bem vindx ao modo de jogo humano contra humano (:");
			System.out.println(player1.getName() + " será as peças O e " + player2.getName() + " será as peças X");
			System.out.println("Boa sorte!");
			
			/*** Apresento o tabuleiro com as possíveis jogadas para o jogador ***/
			ArrayList<Transition> transitions = this.board.findPlayableCells(this.board.getCell(), player1.getPiece(), player2.getPiece());
			this.board.printPlayableCells(transitions, this.board.getCell());
			//this.board.printPlayableDetails(transitions, board.getCell());
		}
		/*** Opção Human x IA ***/
		else if(configuration == 1) {
			/*** Seto as características dos jogadores ***/
			this.player1 = new Player("Thiago", 'O');
			this.player2 = new Player("Adalberto", 'X');
			
			/*** Faço um texto de introdução explicativo ***/
			System.out.println("Bem vindx ao modo de jogo humano contra a mais amazing machine that exists :D");
			System.out.println(player1.getName() + " será as peças O e " + player2.getName() + " será as peças X");
			System.out.println("Eu sou Adalberto! Prepare-se!");
			
			/*** Apresento o tabuleiro com as possíveis jogadas para o jogador ***/
			ArrayList<Transition> transitions = this.board.findPlayableCells(this.board.getCell(), player1.getPiece(), player2.getPiece());
			this.board.printPlayableCells(transitions, this.board.getCell());
		}
	}
	
	public void playerPlays() {
		
	}
	
	/***************************** GET'N SET *****************************/
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	
}
