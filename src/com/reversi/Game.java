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
			/*ArrayList<Transition> transitions = this.board.findPlayableCells(this.board.getCell(), player1.getPiece(), player2.getPiece());
			this.board.printPlayableCells(transitions, this.board.getCell());*/
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
					System.out.println("Erro ao verificar se o tabuleiro est� cheio");
				}
			}
		}
	}

	/*** Op��o de jogo para um jogador Humano ***/
	public void playerPlays(char alliedCell, char enemyCell, String playerName) {
		boolean shouldIStop = false;
		while(!shouldIStop) {
			/*** Apresento o tabuleiro com as poss�veis jogadas para o jogador ***/
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
	
	/*** Op��o de jogo para a IA ***/
	public void aiPlays(char alliedCell, char enemyCell, String playerName) {
		/*** J� entro no estado de MAX ***/
		this.minMax(alliedCell, enemyCell, playerName);
	}
	
	/*** Realiza o minMax ***/
	public void minMax(char alliedCell, char enemyCell, String playerName) {
		/*** Crio a �rvore para l�gica do MINMAX ***/
		MinMaxNode treeRoot = new MinMaxNode();
		
		/************** MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX **************/
		/*** Apresento o tabuleiro com as poss�veis jogadas para o jogador ***/
		ArrayList<Transition> transitions = this.board.findPlayableCells(this.board.getCell(), enemyCell, alliedCell);
		//this.board.printPlayableCells(transitions, this.board.getCell());
		System.out.println("Turno dx " + playerName);
		
		/*** Chama o alphaBeta para poder "podar" a quantidade de possibilidades derivadas no MinMax ***/
		ArrayList<Transition> bestPlays = this.alphaBeta(transitions);
		
		/*** Atribui��o dos elementos do n� ra�z da �rvore MINMAX ***/
		treeRoot.setBoard(this.board); //Atribuo o Board atual no n� ra�z da �rvore
		treeRoot.setBestPlays(bestPlays); //Atribuo a leitura do quadro atual (jogo real) ao n� ra�z da �rvore
		treeRoot.setMin(false); //Atribuo false no MIN (true no MAX por tabela)
		treeRoot.setSons(new ArrayList<MinMaxNode>()); //Inicializo o vetor de filhos
		
		/*** Fa�o simula��es individuais de cada elemento no bestPlays - e salvo em filhos no n� raiz ***/
		/************** MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN **************/
		for (int i = 0; i < treeRoot.getBestPlays().size(); i++) {
			
			Board newBoard = treeRoot.getBoard();// Crio um novo tabuleiro para sofrer altera��es - baseado no da raiz
			
			//Fa�o as inser��es neste novo tabuleiro baseado no bestPlays
			newBoard.setCell(newBoard.protectedInsertItem(bestPlays.get(i).initial.get(0).x, 
														  bestPlays.get(i).initial.get(0).y, 
														  this.player2.getPiece(), 
														  this.player2.getName(), 
														  transitions, 
														  newBoard.getCell())); 
			
			//Acho as novas poss�veis transi��es sobre esse novo tabuleiro - considerando a vis�o do Player1
			ArrayList<Transition> newBoardTransitions = newBoard.findPlayableCells(newBoard.getCell(), 
																				   this.player2.getPiece(), 
																				   this.player1.getPiece());
			
			// N�o crio um novo vetor de bestPlays baseado nas novas transi��es poss�veis - porque deve analisar todas as jogadas inimigas
			//ArrayList<Transition> newBestPlays = this.alphaBeta(newBoardTransitions); 
			
			MinMaxNode son = new MinMaxNode();
			son.setBoard(newBoard); //Atribuo o board imagin�rio alterado no n� ra�z da �rvore
			son.setBestPlays(newBoardTransitions); //Atribuo a leitura do novo quadro imagin�rio - depois do n� ra�z da �rvore
			son.setMin(!treeRoot.isMin()); //Atribuo o contr�rio do MIN (sempre invertido)
			son.setSons(new ArrayList<MinMaxNode>()); //Inicializo o vetor de filhos
			
			treeRoot.getSons().add(son); //Adiciono o novo filho constru�do para a �rvore
		}
		
		/************** Devo continuar os pr�ximos n�s da �rvore **************/	
	}
	
	/*** Realiza a poda alpha beta ***/
	public ArrayList<Transition> alphaBeta(ArrayList<Transition> transitions) {
		/*** Lista das vari�veis filtradas no poda alphaBeta ***/
		ArrayList<Transition> bestTransitions = new ArrayList<Transition>();
		
		/*** Procura a melhor jogada e utiliza ela para processamento de �rvore no minMax ***/
		int bestPlay = 0;
		int bestPlayPosition = 0;
		for (int i = 0; i < transitions.size(); i++) {
			/*** Caso ela seja maior que a melhor jogada - se torna a melhor jogada ***/
			if (transitions.get(i).getRealPoints() > bestPlay) {
				bestPlay = transitions.get(i).getRealPoints();
				bestPlayPosition = i;
			}
		}
		/*** Adiciono a melhor jogada encontrada na lista de melhores transi��es ***/
		bestTransitions.add(transitions.get(bestPlayPosition));
		
		/*** Utilizo a melhor jogada encontrada em uma compara��o com todas as outras para encontrar semelhantes ***/
		for (int i = 0; i < transitions.size(); i++) {
			/*** Caso ela seja equivalente a melhor ***/
			if (transitions.get(i).getRealPoints() == bestTransitions.get(0).getRealPoints()) {
				/*** Caso ela seja equivalente a melhor - e n�o esteja adicionada na lista ***/
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
			System.out.println("A melhor posi��o para jogar � "
					+ "("+transitions.get(0).initial.get(0).x+","+transitions.get(0).initial.get(0).y+")");
			System.out.println();
		}
		else if (transitions.size() > 1) {
			System.out.println();
			System.out.print("As melhores posi��es para jogar s�o: ");
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
