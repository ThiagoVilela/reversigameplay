package com.reversi;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	/***************************** ATRIBUTOS *****************************/
	private Board board = new Board();
	private Player player1;
	private Player player2;

	/****************************** MÉTODOS ******************************/
	/*** Starto o jogo ***/
	public void startGame(int configuration) {
		
		/*** Opção Human x Human ***/
		if (configuration == 0) {
			/*** Reseto o tabuleiro ***/
			this.board.resetBoard();
			
			/*** Seto as características dos jogadores ***/
			this.player1 = new Player("Thiago", 'O', 2);
			this.player2 = new Player("Weber", 'X', 2);

			/*** Faço um texto de introdução explicativo ***/
			System.out.println("Bem vindx ao modo de jogo humano contra humano (:");
			System.out.println(player1.getName() + " será as peças O e " + player2.getName() + " será as peças X");
			System.out.println("Boa sorte!");

			/*** Apresento o tabuleiro com as possíveis jogadas para o jogador ***/
			//ArrayList<Transition> transitions = this.board.findPlayableCells(this.board.getCell(), player1.getPiece(), player2.getPiece());
			//this.board.printPlayableCells(transitions, this.board.getCell());
			//this.board.printPlayableDetails(transitions, board.getCell());

			/*** Crio um laço para intercalar as jogadas dos jogadores ***/
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
					System.out.println("Erro ao verificar se o tabuleiro está cheio");
				}
			}
		}
		/*** Opção Human x IA ***/
		else if(configuration == 1) {
			/*** Reseto o tabuleiro ***/
			this.board.resetBoard();
			
			/*** Seto as características dos jogadores ***/
			this.player1 = new Player("Thiago", 'O');
			this.player2 = new Player("Adalberto", 'X');

			/*** Faço um texto de introdução explicativo ***/
			System.out.println("Bem vindx ao modo de jogo humano contra a mais amazing machine that exists :D");
			System.out.println(player1.getName() + " será as peças O e " + player2.getName() + " será as peças X");
			System.out.println("Eu sou Adalberto! Prepare-se!");

			/*** Apresento o tabuleiro com as possíveis jogadas para o jogador ***/
			/*ArrayList<Transition> transitions = this.board.findPlayableCells(this.board.getCell(), player1.getPiece(), player2.getPiece());
			this.board.printPlayableCells(transitions, this.board.getCell());*/
			/*** Crio um laço para intercalar as jogadas dos jogadores ***/
			boolean shouldIStop = false;
			int playerPlaying = 1;
			while(!shouldIStop) {
				if (!this.board.isFull(this.board.getCell())) {
					if (playerPlaying == 1) {
						this.playerPlays(player1.getPiece(), player2.getPiece(), player1.getName());
						playerPlaying++;
					}
					else if(playerPlaying == 2) {
						this.aiPlays(player2.getPiece(), player1.getPiece(), player2.getName());
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
					System.out.println("Erro ao verificar se o tabuleiro está cheio");
				}
			}
		}
	}

	/*** Opção de jogo para um jogador Humano ***/
	public void playerPlays(char alliedCell, char enemyCell, String playerName) {
		boolean shouldIStop = false;
		while(!shouldIStop) {
			/*** Apresento o tabuleiro com as possíveis jogadas para o jogador ***/
			ArrayList<Transition> transitions = this.board.findPlayableCells(this.board.getCell(), enemyCell, alliedCell);
			System.out.println("Turno dx " + playerName);
			this.board.printPlayableCells(transitions, this.board.getCell());
			
			/*** Leitura via teclado - ARRUMAR ESSE ERRO INFERNO DE NEXTINT ***/
			Scanner in = new Scanner(System.in);
			System.out.print("Digite a linha de sua jogada: ");
			int x = -1;
			if (in.hasNextInt()) {
				x = in.nextInt();
			}
			System.out.print("Digite a coluna de sua jogada: ");
			int y = -1;
			if (in.hasNextInt()) {
				y = in.nextInt();
			}
			in.close();
			
			if (x >= 0 && y >= 0) {
				/*** Executo a jogada informada pelo jogador ***/
				this.board.setCell(this.board.protectedInsertItem(x, y, alliedCell, playerName, transitions, this.board.getCell()));
				shouldIStop = true;
			}
			else {
				System.out.println("Erro ao executar jogada, repita novamente.");
			}
		}

	}
	
	/*** Opção de jogo para a IA ***/
	public void aiPlays(char alliedCell, char enemyCell, String playerName) {
		/*** Apresento o tabuleiro com as possíveis jogadas para o jogador ***/
		ArrayList<Transition> transitions = this.board.findPlayableCells(this.board.getCell(), enemyCell, alliedCell);
		System.out.println("Turno dx " + playerName);
		//this.board.printPlayableCells(transitions, this.board.getCell());
		
		/*** Já entro no estado de MAX ***/
		this.minMax(transitions);
		
	}
	
	/*** Realiza o minMax ***/
	public void minMax(ArrayList<Transition> transitions) {
		/*** Chama o alphaBeta para poder "podar" a quantidade de possibilidades derivadas no MinMax ***/
		
		/************** MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN **************/
		/*** Resultado implica no resultado do MIN ***/
		ArrayList<Transition> bestPlays = this.alphaBeta(transitions);
		
		/*** Crio um vetor de boards para simular as jogadas obtidas no bestPlays ***/
		Board[] boards = new Board[bestPlays.size()];
		for (int i = 0; i < boards.length; i++) {
			boards[i] = this.board;
			boards[i].setCell(boards[i].insertItem(bestPlays.get(i).initial.get(0).x, 
												   bestPlays.get(i).initial.get(0).y, 
												   this.player2.getPiece(), 
												   this.player2.getName(), 
												   boards[i].getCell())
												   );
		}
		
		/************** MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX **************/
		
		
	}
	
	/*** Realiza a poda alpha beta ***/
	public ArrayList<Transition> alphaBeta(ArrayList<Transition> transitions) {
		/*** Lista das variáveis filtradas no poda alphaBeta ***/
		ArrayList<Transition> bestTransitions = new ArrayList<Transition>();
		
		/*** Procura a melhor jogada e utiliza ela para processamento de árvore no minMax ***/
		int bestPlay = 0;
		int bestPlayPosition = 0;
		for (int i = 0; i < transitions.size(); i++) {
			/*** Caso ela seja maior que a melhor jogada - se torna a melhor jogada ***/
			if (transitions.get(i).getRealPoints() > bestPlay) {
				bestPlay = transitions.get(i).getRealPoints();
				bestPlayPosition = i;
			}
		}
		/*** Adiciono a melhor jogada encontrada na lista de melhores transições ***/
		bestTransitions.add(transitions.get(bestPlayPosition));
		
		/*** Utilizo a melhor jogada encontrada em uma comparação com todas as outras para encontrar semelhantes ***/
		for (int i = 0; i < transitions.size(); i++) {
			/*** Caso ela seja equivalente a melhor ***/
			if (transitions.get(i).getRealPoints() == bestTransitions.get(0).getRealPoints()) {
				/*** Caso ela seja equivalente a melhor - e não esteja adicionada na lista ***/
				if (bestTransitions.get(0).initial.get(0).x != transitions.get(i).initial.get(0).x && 
					bestTransitions.get(0).initial.get(0).y != transitions.get(i).initial.get(0).y) {
					/*** E adicionada na lista ***/
					bestTransitions.add(transitions.get(i));
				}
			}
		}
		
		/*** Imprime o resultado obtido para testes ***/
		this.printAlphaBetaResult(bestTransitions);
		
		return bestTransitions;
	}
	
	/*** Imprime o resultado de alpha beta ***/
	public void printAlphaBetaResult(ArrayList<Transition> transitions){
		if (transitions.size() == 1){
			System.out.println();
			System.out.println("A melhor posição para jogar é "
					+ "("+transitions.get(0).initial.get(0).x+","+transitions.get(0).initial.get(0).y+")");
			System.out.println();
		}
		else if (transitions.size() > 1) {
			System.out.println();
			System.out.print("As melhores posições para jogar são: ");
			for (int i = 0; i < transitions.size(); i++) {
				System.out.print("("+transitions.get(i).initial.get(0).x+","+transitions.get(i).initial.get(0).y+")");
			}
			System.out.println();
			System.out.println();
		} 
		else {
			System.out.println("Erro ao imprimir o resultado da lista obtida por poda alphabeta.");
		}
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
