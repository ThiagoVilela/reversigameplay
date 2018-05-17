package com.reversi;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	/***************************** ATRIBUTOS *****************************/
	public static Board board = new Board();
	public static Player player1;
	public static Player player2;
	
	/*** Vari�veis est�ticas para linkar com a interface ***/
	public static int LEVEL = 1;
	public static String namePlayer1 = "";
	public static String namePlayer2 = "";
	
	public static int playerPlaying = 1;

	/****************************** M�TODOS ******************************/
	/*** Starto o jogo ***/
	public void startGame(int configuration) {
		
		/*** Op��o Human x Human ***/
		if (configuration == 0) {
			/*** Reseto o tabuleiro ***/
			Game.board.resetBoard();
			
			/*** Seto as caracter�sticas dos jogadores ***/
			Game.player1 = new Player(Game.namePlayer1, 'O', 2);
			Game.player2 = new Player(Game.namePlayer2, 'X', 2);

			/*** Fa�o um texto de introdu��o explicativo ***/
			System.out.println("Bem vindx ao modo de jogo humano contra humano (:");
			System.out.println(Game.player1.getName() + " ser� as pe�as O e " + Game.player2.getName() + " ser� as pe�as X");
			System.out.println("Boa sorte!");

			/*** Apresento o tabuleiro com as poss�veis jogadas para o jogador ***/
			//ArrayList<Transition> transitions = Game.board.findPlayableCells(Game.board.getCell(), player1.getPiece(), player2.getPiece());
			//Game.board.printPlayableCells(transitions, Game.board.getCell());
			//Game.board.printPlayableDetails(transitions, board.getCell());

			/*** Crio um la�o para intercalar as jogadas dos jogadores ***/
			boolean shouldIStop = false;
			int playerPlaying = 1;
			while(!shouldIStop) {
				if (!Game.board.isFull(Game.board.getCell())) {
					if (playerPlaying == 1) {
						Game.playerPlays(player1.getPiece(), player2.getPiece(), player1.getName());
						playerPlaying++;
					}
					else if(playerPlaying == 2) {
						Game.playerPlays(player2.getPiece(), player1.getPiece(), player2.getName());
						playerPlaying--;
					}
					else {
						shouldIStop = true;
						System.out.println("Erro na escolha do jogador");
					}
				}
				else if(Game.board.isFull(Game.board.getCell())){
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
			Game.board.resetBoard();
			
			/*** Seto as caracter�sticas dos jogadores ***/
			Game.player1 = new Player(Game.namePlayer1, 'O');
			Game.player2 = new Player("Adalberto", 'X');

			/*** Fa�o um texto de introdu��o explicativo ***/
			System.out.println("Bem vindx ao modo de jogo humano contra a mais amazing machine that exists :D");
			System.out.println(player1.getName() + " ser� as pe�as O e " + player2.getName() + " ser� as pe�as X");
			System.out.println("Eu sou Adalberto! Prepare-se!");

			/*** Apresento o tabuleiro com as poss�veis jogadas para o jogador ***/
			/*ArrayList<Transition> transitions = Game.board.findPlayableCells(Game.board.getCell(), player1.getPiece(), player2.getPiece());
			Game.board.printPlayableCells(transitions, Game.board.getCell());*/
			/*** Crio um la�o para intercalar as jogadas dos jogadores ***/
			boolean shouldIStop = false;
			int playerPlaying = 1;
			while(!shouldIStop) {
				if (!Game.board.isFull(Game.board.getCell())) {
					if (playerPlaying == 1) {
						Game.playerPlays(player1.getPiece(), player2.getPiece(), player1.getName());
						playerPlaying++;
					}
					else if(playerPlaying == 2) {
						Game.aiPlays(player2.getPiece(), player1.getPiece(), player2.getName());
						playerPlaying--;
					}
					else {
						shouldIStop = true;
						System.out.println("Erro na escolha do jogador");
					}
				}
				else if(Game.board.isFull(Game.board.getCell())){
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
	public static void playerPlays(char alliedCell, char enemyCell, String playerName) {
		boolean shouldIStop = false;
		while(!shouldIStop) {
			/*** Apresento o tabuleiro com as poss�veis jogadas para o jogador ***/
			ArrayList<Transition> transitions = Game.board.findPlayableCells(Game.board.getCell(), enemyCell, alliedCell);
			System.out.println("Turno dx " + playerName);
			Game.board.printPlayableCells(transitions, Game.board.getCell());
			
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
				Game.board.setCell(Game.board.protectedInsertItem(x, y, alliedCell, playerName, transitions, Game.board.getCell()));
				shouldIStop = true;
			}
			else {
				System.out.println("Erro ao executar jogada, repita novamente.");
			}
		}

	}
	
	/*** Op��o de jogo para a IA ***/
	public static void aiPlays(char alliedCell, char enemyCell, String playerName) {
		/*** Consigo a �rvore de jogadas preenchida ***/
		MinMaxNode minMaxTree = Game.minMax(alliedCell, enemyCell, playerName);
		/*** Identificar o bestMove da �rvore montada ***/
		System.out.println("VOU ENTRAR NA AIPLAYS!");
		Transition bestMove = Game.findBestMoveRoot(minMaxTree);
		
		/*** TO DO - Realizar a jogada ***/
		if (bestMove.initial.get(0).x >= 0 && bestMove.initial.get(0).y >= 0) {
			/*** Executo a jogada informada pelo jogador ***/
			ArrayList<Transition> transitions = Game.board.findPlayableCells(Game.board.getCell(), enemyCell, alliedCell);
			System.out.println("Turno dx " + playerName);
			Game.board.setCell(Game.board.protectedInsertItem(bestMove.initial.get(0).x, bestMove.initial.get(0).y, alliedCell, playerName, transitions, Game.board.getCell()));
			Game.board.printBoard(Game.board.getCell());
		}
		
		else {
			System.out.println("Erro ao identificar a jogada selecionada da IA ");
		}
		
	}
	
	/*** Procura a jogada de maior valor na �rvore derivada ***/
	public static Transition findBestMoveRoot(MinMaxNode tree) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("TO NA FIND ROOTS");
		Transition bestMove = new Transition();
		/*** Crio um bestMove de valor 0 para compara��o ***/
		bestMove.pointsAdd.add(0);
		
		for (int i = 0; i < tree.getSons().size(); i++) {
			if (tree.getBestSon() == null) {
				System.out.println("Vou tentar adicionar o melhor filho da raiz pica");
				tree.setBestSon(Game.findBestMove(tree.getSons().get(i))); //ACHAR O PESO DO MELHOR FILHO
				System.out.println("Voltei pra ROOTS");
				if(tree.getBestSon() == null ) {System.out.println("Tentei adicionar o melhor filho da raiz pica");}
				else {System.out.println();
				System.out.println();
				System.out.println();System.out.println("ADICIONEI UM FILHO PICA 2FLAY NA ROTS!");
				System.out.println();
				System.out.println();
				System.out.println();}
			}
			
			/*** Caso j� exista um bestSon ***/
			else if (tree.getBestSon() != null) {
				System.out.println("VOU COMPARAR O POSSIVEL NOVO BESTSON ROOTS COM O ATUAL");
				/*** Obtenho a pontua��o da jogada feita no bestSon ***/
				int[] score = tree.getBestSon().getBoard().getScore(tree.getBestSon().getBoard().getCell());
				int aiBestSonScore = 0;
				
				if (Game.player2.getPiece() == 'O') {aiBestSonScore = score[0];}
				else if(Game.player2.getPiece() == 'X') {aiBestSonScore = score[1];}
				else {System.out.println("Erro ao identificar a quem pertence o score");}
				
				int aiActualSonScore = -1;
				MinMaxNode possibleBestSon = Game.findBestMove(tree.getSons().get(i));
				
				if (possibleBestSon != null) {
					score = possibleBestSon.getBoard().getScore(tree.getSons().get(i).getBoard().getCell());
					if (Game.player2.getPiece() == 'O') {aiActualSonScore = score[0];}
					else if(Game.player2.getPiece() == 'X') {aiActualSonScore = score[1];}
					else {System.out.println("Erro ao identificar a quem pertence o score");}
				}
				
				/*** Substituo o novo bestSon pelo n� de maior pontua��o  ***/
				if (aiActualSonScore > aiBestSonScore) {
					System.out.println("COMPAREI E TROQUEI O BEST SON DO ROOTS!");
					tree.setBestSon(tree.getSons().get(i));
				}else {
					System.out.println("COMPAREI E NAAAOOO TROQUEI O BEST SON DO ROOTS!");
				}
			}
		}
		
		if (tree.getBestSon() != null) {
			System.out.println("ENTREI PRA CALCULAR OS ROLE E O MEU ROOTS TEM UM MELHOR!");
			/*** Acho a coordenada alterada entre os itens da �rvore - em fun��o da pe�a da IA ***/
			ArrayList<Coordinate> foundCoordinate = (Game.findDifferentCoordinate(tree.getBoard().getCell(), 
																	  tree.getBestSon().getBoard().getCell(), 
																	  Game.player2.getPiece()));
			
			/*** Procuro a coordenada encontrada no vetor de bestPlays ***/
			for (int j = 0; j < tree.getBestPlays().size(); j++) {
				for (int j2 = 0; j2 < foundCoordinate.size(); j2++) {
					if (tree.getBestPlays().get(j).initial.get(0).x == foundCoordinate.get(j2).x
							&&	tree.getBestPlays().get(j).initial.get(0).y == foundCoordinate.get(j2).y) {
							/*** Retorno a transi��o desejada ***/
							System.out.println();
							System.out.println();
							System.out.println();
							System.out.println();
							System.out.println();
							System.out.println("Estou retornando sem erros!");
							System.out.println();
							System.out.println();
							System.out.println();
							System.out.println();
							System.out.println();
							return tree.getBestPlays().get(j);
						}
				}
			}
		}
		
		/*** Retorna a inicial como erro ***/
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("VOU RETORNAR A PADRAO COMO ERRO");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		return tree.getBestPlays().get(0);
	}
	
	/*** Encontra o melhor filho e passo ele para o pai ***/
	public static MinMaxNode findBestMove(MinMaxNode tree) {
		System.out.println("TO NA FIND NUTELLA");
		/*** E um elemento favor�vel a IA ***/
		if (tree.isMin()) {
			System.out.println("ESTOU NUM ITEM MIN MIN MIN MIN");
			if (tree.getSons().size() > 0) {
				for (int i = 0; i < tree.getSons().size(); i++) {
					/*** Bad call total - Player vai ganhar da IA ***/
					if (tree.getSons().get(i).getSons().size() == 0) {
						//Retorno para matar o n� e nem processar uma poss�vel vit�ria da AI
						return null;
					}
					
					else if (tree.getSons().get(i).getSons().size() > 0) {
						if (tree.getBestSon() == null) {
							System.out.println("VOU TENTAR ADD UM FILHO NA NUTELLA MIN");
							tree.setBestSon(Game.findBestMove(tree.getSons().get(i))); //ACHAR O PESO DO MELHOR FILHO
							System.out.println("Voltei pra NUTELLA MIN");
							if(tree.getBestSon() == null ) {System.out.println("Tentei adicionar o melhor filho da raiz nutella");}
							else {System.out.println("ADICIONEI UM FILHO NUTELLA MIN 2FLAY!");}
							System.out.println();
							System.out.println();
							System.out.println();
						}
						
						else if (tree.getBestSon() != null) {
							System.out.println("VOU COMPARAR O POSSIVEL NOVO BESTSON MIN COM O ATUAL");
							/*** Obtenho a pontua��o da jogada feita no bestSon ***/
							int[] score = tree.getBestSon().getBoard().getScore(tree.getBestSon().getBoard().getCell());
							int aiBestSonScore = 0;
							
							if (Game.player2.getPiece() == 'O') {aiBestSonScore = score[0];}
							else if(Game.player2.getPiece() == 'X') {aiBestSonScore = score[1];}
							else {System.out.println("Erro ao identificar a quem pertence o score");}
							
							/*** Obtenho a pontua��o da jogada feita no poss�vel candidato a bestSon ***/
							int aiActualSonScore = -1;
							MinMaxNode possibleBestSon = Game.findBestMove(tree.getSons().get(i));
							
							if (possibleBestSon != null) {
								score = possibleBestSon.getBoard().getScore(tree.getSons().get(i).getBoard().getCell());
								if (Game.player2.getPiece() == 'O') {aiActualSonScore = score[0];}
								else if(Game.player2.getPiece() == 'X') {aiActualSonScore = score[1];}
								else {System.out.println("Erro ao identificar a quem pertence o score");}
							}
							
							/*** Substituo o novo bestSon pelo n� de maior pontua��o  ***/
							if (aiActualSonScore > aiBestSonScore) {
								System.out.println("COMPAREI E TROQUEI O BEST SON DO MIN!");
								tree.setBestSon(tree.getSons().get(i));
							}
							else {
								System.out.println("COMPAREI E NAAAOOOOOO TROQUEI O BEST SON DO MIN!");
							}
							System.out.println();
							System.out.println();
							System.out.println();
							
						}
					}
				}
				
				return tree.getBestSon();
			} 
			/*** N�o tem mais filhos - pode retornar ***/
			else if(tree.getSons().size() == 0){
				System.out.println("VOU RETORNAR UM MIN FOLHA");
				return tree;
			}
		}
		
		else if(!tree.isMin()) {
			System.out.println("ESTOU NUM ITEM MAX MAX MAX MAX");
			if (tree.getSons().size() > 0) {
				for (int i = 0; i < tree.getSons().size(); i++) {
					/*** Good call - IA vai ganhar do Player ***/
					if (tree.getSons().get(i).getSons().size() == 0) {
						if (tree.getBestSon() == null) {
							System.out.println("VOU TENTAR ADD UM FILHO NA MAX");
							tree.setBestSon(Game.findBestMove(tree.getSons().get(i))); //ACHAR O PESO DO MELHOR FILHO
							System.out.println("Voltei pra NUTELLA MAX");
							if(tree.getBestSon() == null ) {System.out.println("Tentei adicionar o melhor filho da raiz nutella");}
							else {System.out.println("ADICIONEI UM FILHO NUTELLA MAX 2FLAY!");}
						}
						
						else if (tree.getBestSon() != null) {
							System.out.println("VOU COMPARAR O POSSIVEL NOVO BESTSON MAX COM O ATUAL");
							/*** Obtenho a pontua��o da jogada feita no bestSon ***/
							int[] score = tree.getBestSon().getBoard().getScore(tree.getBestSon().getBoard().getCell());
							int aiBestSonScore = 0;
							
							if (Game.player2.getPiece() == 'O') {aiBestSonScore = score[0];}
							else if(Game.player2.getPiece() == 'X') {aiBestSonScore = score[1];}
							else {System.out.println("Erro ao identificar a quem pertence o score");}
							
							/*** Obtenho a pontua��o da jogada feita no poss�vel candidato a bestSon ***/
							int aiActualSonScore = -1;
							MinMaxNode possibleBestSon = Game.findBestMove(tree.getSons().get(i));
							
							if (possibleBestSon != null) {
								score = possibleBestSon.getBoard().getScore(tree.getSons().get(i).getBoard().getCell());
								if (Game.player2.getPiece() == 'O') {aiActualSonScore = score[0];}
								else if(Game.player2.getPiece() == 'X') {aiActualSonScore = score[1];}
								else {System.out.println("Erro ao identificar a quem pertence o score");}
							}
							
							/*** Substituo o novo bestSon pelo n� de maior pontua��o  ***/
							if (aiActualSonScore > aiBestSonScore) {
								System.out.println("COMPAREI E TROQUEI O BEST SON DO MAX!");
								tree.setBestSon(tree.getSons().get(i));
							}else {
								System.out.println("COMPAREI E NAAAOOOOO TROQUEI O BEST SON DO MAX!");
							}
							System.out.println();
							System.out.println();
							System.out.println();
							
						}
					}
					
					else if (tree.getSons().get(i).getSons().size() > 0) {
						// Implementar para dificuldades maiores que 1
					}
				}
				return tree.getBestSon();
			} 
			/*** Entrou em um caso que o adves�rio ganha da IA antes do final ***/
			else if(tree.getSons().size() == 0){
				System.out.println("VOU RETORNAR UM MAX FOLHA NULL");
				return null;
			}
		}
		
		System.out.println("Erro ao identificar elementos MIN ou MAX na �rvore p�s montada ");
		return new MinMaxNode();
	}
	
	/*** Encontra a coordenada alterada do antigo board pro novo ***/
	public static ArrayList<Coordinate> findDifferentCoordinate(Cell[][] board, Cell[][] newBoard, char wantedPiece) {
		ArrayList<Coordinate> validCoordinates = new ArrayList<Coordinate>();
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j].content == '_' && newBoard[i][j].content == wantedPiece) {
					validCoordinates.add(new Coordinate(i,j));
				}
			}
		}
		return validCoordinates;
	}
	
	/************** Encontro o estado MIN ou MAX do estado folha da �rvore **************/	
	public boolean leafMinOrMax(MinMaxNode tree) {
		/*** TO DO ***/
		return true;
	}
	
	/*** Realiza o minMax ***/
	public static MinMaxNode minMax(char alliedCell, char enemyCell, String playerName) {
		System.out.println();
		System.out.println();
		System.out.println("ENTREI NO MINMAX ");
		System.out.println();
		System.out.println();
		/*** Crio a �rvore para l�gica do MINMAX ***/
		MinMaxNode treeRoot = new MinMaxNode();
		
		/************** MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX **************/
		/*** Apresento o tabuleiro com as poss�veis jogadas para o jogador ***/
		ArrayList<Transition> transitions = Game.board.findPlayableCells(Game.board.getCell(), enemyCell, alliedCell);
		//Game.board.printPlayableCells(transitions, Game.board.getCell());
		System.out.println("Turno dx " + playerName);
		
		/*** Chama o alphaBeta para poder "podar" a quantidade de possibilidades derivadas no MinMax ***/
		ArrayList<Transition> bestPlays = Game.alphaBeta(transitions);
		
		/*** Atribui��o dos elementos do n� ra�z da �rvore MINMAX ***/
		treeRoot.setBoard(Game.board); //Atribuo o Board atual no n� ra�z da �rvore
		treeRoot.setBestPlays(bestPlays); //Atribuo a leitura do quadro atual (jogo real) ao n� ra�z da �rvore
		treeRoot.setMin(false); //Atribuo false no MIN (true no MAX por tabela)
		treeRoot.setSons(new ArrayList<MinMaxNode>()); //Inicializo o vetor de filhos
		
		/*** Fa�o simula��es individuais de cada elemento no bestPlays - e salvo em filhos no n� raiz ***/
		/************** MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN **************/
		for (int i = 0; i < treeRoot.getBestPlays().size(); i++) {
			//Board newBoard = treeRoot.getBoard();
			// Crio um novo tabuleiro para sofrer altera��es - baseado no da raiz
			Board newBoard = new Board();
			//Fa�o a transferencia de conte�do devido a manuten��o da posi��o de mem�ria diretamente
			for (int j = 0; j < Board.SIZE; j++) {
				for (int j2 = 0; j2 < Board.SIZE; j2++) {
					newBoard.cell[j][j2] = new Cell();
					newBoard.cell[j][j2].content = treeRoot.getBoard().cell[j][j2].content;
				}
			}
			
			//Fa�o as inser��es neste novo tabuleiro baseado no bestPlays
			newBoard.setCell(newBoard.protectedInsertItem(bestPlays.get(i).initial.get(0).x, 
														  bestPlays.get(i).initial.get(0).y, 
														  Game.player2.getPiece(), 
														  Game.player2.getName(), 
														  transitions, 
														  newBoard.getCell())); 
			
			//Acho as novas poss�veis transi��es sobre esse novo tabuleiro - considerando a vis�o do Player1
			ArrayList<Transition> newBoardTransitions = newBoard.findPlayableCells(newBoard.getCell(), 
					Game.player2.getPiece(), 
					Game.player1.getPiece());
			
			// N�o crio um novo vetor de bestPlays baseado nas novas transi��es poss�veis - porque deve analisar todas as jogadas inimigas
			//ArrayList<Transition> newBestPlays = Game.alphaBeta(newBoardTransitions); 
			
			MinMaxNode son = new MinMaxNode();
			son.setBoard(newBoard); //Atribuo o board imagin�rio alterado no n� ra�z da �rvore
			son.setBestPlays(newBoardTransitions); //Atribuo a leitura do novo quadro imagin�rio - depois do n� ra�z da �rvore
			son.setMin(!treeRoot.isMin()); //Atribuo o contr�rio do MIN (sempre invertido)
			son.setSons(new ArrayList<MinMaxNode>()); //Inicializo o vetor de filhos
			
			treeRoot.getSons().add(son); //Adiciono o novo filho constru�do para a �rvore
		}
		
		Game.printTree(treeRoot);
		
		/************** Chamo a fun��o recursivamente para altera��o da �rvore **************/
		/************** O m�nimo necess�rio para fazer um MINMAX b�sico s�o 4 n�veis (0 a 3) **************/
		/************** Sendo assim - o usu�rio poder� escolher o n�vel de dificuldade da IA **************/
		/************** Que sempre ser� par (4~6~8~...~N) - assim ser� o fator que multiplica o numero 2 **************/
		for (int i = 0; i < 2*Game.LEVEL; i++) {
			treeRoot = Game.continueMinMax(treeRoot);
		}
		
		//treeRoot = Game.continueMinMax(treeRoot);
		
		Game.printTree(treeRoot);
		
		return treeRoot;
	}
	
	/************** Fa�o os dois passos finais (que podem ser recursivos) do MINMAX **************/
	public static MinMaxNode continueMinMax(MinMaxNode tree) {
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("ENTREI NO CONTINUE MINMAX");
		
		System.out.println();
		System.out.println();
		System.out.println();
		/*** Caso a lista de filhos NAO esteja vazia ***/
		if (tree.getSons().size() > 0) {
			for (int i = 0; i < tree.getSons().size(); i++) {
				/*** Chame a fun��o at� chegar todos os folhas ***/
				tree.getSons().set(i, Game.continueMinMax(tree.getSons().get(i)));
			}
			return tree;
		}
		/*** Caso a lista de filhos esteja vazia - encontrou um n� folha ***/
		else if(tree.getSons().size() == 0) {
			//Fa�o com alpha beta
			if (!tree.isMin()) {
				/************** MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX MAX **************/
				for (int i = 0; i < tree.getBestPlays().size(); i++) {
					// Crio um novo tabuleiro para sofrer altera��es - baseado no da raiz
					Board newBoard = new Board();
					//Fa�o a transferencia de conte�do devido a manuten��o da posi��o de mem�ria diretamente
					for (int j = 0; j < Board.SIZE; j++) {
						for (int j2 = 0; j2 < Board.SIZE; j2++) {
							newBoard.cell[j][j2] = new Cell();
							newBoard.cell[j][j2].content = tree.getBoard().cell[j][j2].content;
						}
					}
					
					//Fa�o as inser��es neste novo tabuleiro baseado no bestPlays
					newBoard.setCell(newBoard.protectedInsertItem(tree.getBestPlays().get(i).initial.get(0).x, 
																  tree.getBestPlays().get(i).initial.get(0).y, 
																  Game.player2.getPiece(), 
																  Game.player2.getName(), 
																  tree.getBestPlays(), 
																  newBoard.getCell())); 
					
					//Acho as novas poss�veis transi��es sobre esse novo tabuleiro - considerando a vis�o da IA
					ArrayList<Transition> newBoardTransitions = newBoard.findPlayableCells(newBoard.getCell(), 
							Game.player2.getPiece(), 
							Game.player1.getPiece());
					
					// Crio um novo vetor de bestPlays baseado nas novas transi��es poss�veis - porque deve analisar todas as jogadas inimigas
					ArrayList<Transition> newBestPlays = Game.alphaBeta(newBoardTransitions); 
					
					MinMaxNode son = new MinMaxNode();
					son.setBoard(newBoard); //Atribuo o board imagin�rio alterado no n� ra�z da �rvore
					son.setBestPlays(newBestPlays); //Atribuo a leitura do novo quadro imagin�rio - depois do n� ra�z da �rvore
					son.setMin(!tree.isMin()); //Atribuo o contr�rio do MIN (sempre invertido)
					son.setSons(new ArrayList<MinMaxNode>()); //Inicializo o vetor de filhos
					
					tree.getSons().add(son); //Adiciono o novo filho constru�do para a �rvore
				}
				
				return tree;
			}
			//fa�o sem alpha beta
			else if(tree.isMin()) {
				/************** MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN MIN **************/
				for (int i = 0; i < tree.getBestPlays().size(); i++) {
					// Crio um novo tabuleiro para sofrer altera��es - baseado no da raiz
					Board newBoard = new Board();
					//Fa�o a transferencia de conte�do devido a manuten��o da posi��o de mem�ria diretamente
					for (int j = 0; j < Board.SIZE; j++) {
						for (int j2 = 0; j2 < Board.SIZE; j2++) {
							newBoard.cell[j][j2] = new Cell();
							newBoard.cell[j][j2].content = tree.getBoard().cell[j][j2].content;
						}
					}
					
					//Fa�o as inser��es neste novo tabuleiro baseado no bestPlays
					newBoard.setCell(newBoard.protectedInsertItem(tree.getBestPlays().get(i).initial.get(0).x, 
																  tree.getBestPlays().get(i).initial.get(0).y, 
																  Game.player1.getPiece(), 
																  Game.player1.getName(), 
																  tree.getBestPlays(), 
																  newBoard.getCell())); 
					
					//Acho as novas poss�veis transi��es sobre esse novo tabuleiro - considerando a vis�o do P1
					ArrayList<Transition> newBoardTransitions = newBoard.findPlayableCells(newBoard.getCell(), 
							Game.player1.getPiece(), 
							Game.player2.getPiece());
					
					// N�o crio um novo vetor de bestPlays baseado nas novas transi��es poss�veis - porque deve analisar todas as jogadas inimigas
					//ArrayList<Transition> newBestPlays = Game.alphaBeta(newBoardTransitions); 
					
					MinMaxNode son = new MinMaxNode();
					son.setBoard(newBoard); //Atribuo o board imagin�rio alterado no n� ra�z da �rvore
					son.setBestPlays(newBoardTransitions); //Atribuo a leitura do novo quadro imagin�rio - depois do n� ra�z da �rvore
					son.setMin(!tree.isMin()); //Atribuo o contr�rio do MIN (sempre invertido)
					son.setSons(new ArrayList<MinMaxNode>()); //Inicializo o vetor de filhos
					
					tree.getSons().add(son); //Adiciono o novo filho constru�do para a �rvore
				}
				
				return tree;
			}
			else {
				System.out.println("Erro ao identificar se � MAX ou MIN do terceiro n�vel em diante da �rvore de MINMAX");
			}
		}
		
		
		return tree;
	}
	
	/************** Imprimo o conte�do da �rvore **************/	
	public static void printTree(MinMaxNode tree) {
		System.out.println();
		System.out.println();
		System.out.println("VOU IMPRIMIR O PAI");
		System.out.println();
		System.out.println();
		tree.printNodeContent();
		
		System.out.println();
		System.out.println();
		System.out.println("VOU IMPRIMIR OS FILHOS");
		System.out.println();
		System.out.println();
		for (int i = 0; i < tree.getSons().size(); i++) {
			tree.getSons().get(i).printNodeContent();
			if (tree.getSons().get(i).getSons().size() > 0) {
				Game.printTree(tree.getSons().get(i));
			}
			
		}
	}
	
	/*** Realiza a poda alpha beta ***/
	public static ArrayList<Transition> alphaBeta(ArrayList<Transition> transitions) {
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
		Game.printAlphaBetaResult(bestTransitions);
		
		return bestTransitions;
	}
	
	/*** Imprime o resultado de alpha beta ***/
	public static void printAlphaBetaResult(ArrayList<Transition> transitions){
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
		Game.board = board;
	}
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		Game.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		Game.player2 = player2;
	}


}
