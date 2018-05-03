package com.reversi;

import java.util.ArrayList;

public class MinMaxNode {
	/***************************** ATRIBUTOS *****************************/
	/*** Momento do tabuleiro em que o nó se encontra ***/
	private Board board = new Board();
	/*** Melhores jogadas (com filtro alpha beta caso seja IA) para o tabuleiro atual ***/
	private ArrayList<Transition> bestPlays = new ArrayList<Transition>();
	/*** Lista de filhos (jogadas derivadas das bestPlays) ***/
	private ArrayList<MinMaxNode> sons = null;
	/*** Indicador de MIN ou MAX ***/
	private boolean min;
	
	/***************************** GET'N SET *****************************/
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public ArrayList<Transition> getBestPlays() {
		return bestPlays;
	}
	public void setBestPlays(ArrayList<Transition> bestPlays) {
		this.bestPlays = bestPlays;
	}
	public ArrayList<MinMaxNode> getSons() {
		return sons;
	}
	public void setSons(ArrayList<MinMaxNode> sons) {
		this.sons = sons;
	}
	public boolean isMin() {
		return min;
	}
	public void setMin(boolean min) {
		this.min = min;
	}
	
	
}
