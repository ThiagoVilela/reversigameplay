package com.reversi;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	/***************************** ATRIBUTOS *****************************/
	private Board board = new Board();
	private Player player1;
	private Player player2;

	/****************************** M�TODOS ******************************/
	/*** Starto o jogo ***/
	public void startGame(int configuration) {
		
		/*** Op��o Human x Human ***/
		if (configuration == 0) {
			/*** Reseto o tabuleiro ***/
			this.board.resetBoard();
			
			/*** Seto as caracter�sticas dos jogadores ***/
			this.player1 = new Player("Thiago", 'O', 2);
			this.player2 = new Player("Weber", 'X', 2);

			/*** Fa�o um texto de introdu��o explicativo ***/
			System.out.println("Bem vindx ao modo de jogo humano contra humano (:");
			System.out.println(player1.getName() + " ser� as pe�as O e " + player2.getName() + " ser� as pe�as X");
			System.out.println("Boa sorte!");

			/*** Apresento o tabuleiro com as poss�veis jogadas para o jogador ***/
			//ArrayList<Transition> transitions = this.board.findPlayableCells(this.board.getCell(), player1.getPiece(), player2.getPiece());
			//this.board.printPlayableCells(transitions, this.board.getCell());
			//this.board.printPlayableDetails(transitions, board.getCell());

			/*** Crio um la�o para intercalar as jogadas dos jogadores ***/
			boolean shouldIStop = false;
			int playerPlaying = 1;
			while(!shouldIStop) {
				if (!this.board.isFull(this.board.getCell())) {
					if (playerPlaying == 1) {
						this.playerPlays(player1.getPiece(), player2.getPiece(), player1.getName());
						playerPlaying++;
					}
					else if(playerPlaying == 2) {
						this.playerPlays(player2.getPiece(), player1.getPiece(), player2.getName());
						playerPlaying--;
					}
					else {
						shouldIStop = true;
						System.out.println("Erro na escolha do jogador");
					}
				}
				else if(this.board.isFull(this.board.getCell())){
					shouldIStop = true;
					System.out.println("Acabou o jogo!");
					System.out.println("Tabuleiro cheio!");
				}
				else {
					shouldIStop = true;
					System.out.println("Erro ao verificar se o tabuleiro est� cheio");
				}
			}
		}
		/*** Op��o Human x IA ***/
		else if(configuration == 1) {
			/*** Reseto o tabuleiro ***/
			this.board.resetBoard();
			
			/*** Seto as caracter�sticas dos jogadores ***/
			this.player1 = new Player("Thiago", 'O');
			this.player2 = new Player("Adalberto", 'X');

			/*** Fa�o um texto de introdu��o explicativo ***/
			System.out.println("Bem vindx ao modo de jogo humano contra a mais amazing machine that exists :D");
			System.out.println(player1.getName() + " ser� as pe�as O e " + player2.getName() + " ser� as pe�as X");
			System.out.println("Eu sou Adalberto! Prepare-se!");

			/*** Apresento o tabuleiro com as poss�veis jogadas para o jogador ***/
			ArrayList<Transition> transitions = this.board.findPlayableCells(this.board.getCell(), player1.getPiece(), player2.getPiece());
			this.board.printPlayableCells(transitions, this.board.getCell());
		}
	}

	/*** Op��o de jogo para um jogador Humano ***/
	public void playerPlays(char alliedCell, char enemyCell, String playerName) {
		/*** Apresento o tabuleiro com as poss�veis jogadas para o jogador ***/
		ArrayList<Transition> transitions = this.board.findPlayableCells(this.board.getCell(), enemyCell, alliedCell);
		System.out.println("Turno dx " + playerName);
		this.board.printPlayableCells(transitions, this.board.getCell());
		
		/*** Leitura via teclado - Atualmente ***/
		Scanner in = new Scanner(System.in);
		System.out.print("Digite a linha de sua jogada: ");
		int x = in.nextInt();
		System.out.print("Digite a coluna de sua jogada: ");
		int y = in.nextInt();
		in.close();
		
		/*** Executo a jogada informada pelo jogador ***/
		this.board.setCell(this.board.protectedInsertItem(x, y, alliedCell, playerName, transitions, this.board.getCell()));
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
