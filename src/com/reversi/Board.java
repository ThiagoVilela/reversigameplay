package com.reversi;

import java.util.ArrayList;

public class Board {
	/***************************** ATRIBUTOS *****************************/
	/*** Tamanho estático do tabuleiro quadrado ***/
	public static int SIZE = 8;
	/*** Número de casas do tabuleiro em função do tamanho SIZE ***/
	public Cell[][] cell = new Cell[SIZE][SIZE];

	/****************************** MÉTODOS ******************************/
	/*** Verifica se a posição está vazia ***/
	public boolean isEmpty(int x, int y, Cell[][] cell) {
		if (cell[x][y].content == '_') {
			return true;
		} else {
			return false;
		}
	}

	/*** Verifica se a posição no tabuleiro possui algum elemento inimigo ao seu redor ***/
	public ArrayList<Coordinate> hasBadNeighborhood(int x, int y, char enemyCell, Cell[][] cell) {
		ArrayList<Coordinate> badNeighbors = new ArrayList<Coordinate>();
		System.out.println("LOOKING FOR ENEMIES AROUND ["+x+"]["+y+"] - LOOKING FOR '" + enemyCell+"'");
		for (int i = x-1; i <= x+1; i++) {
			for (int j = y-1; j <= y+1; j++) {
				/* Respeito os limites de bordas do tabuleiro */
				if (i >= 0 && j >= 0 && i < SIZE && j < SIZE) {
					/* Verifico se é uma célula inimiga */
					if (cell[i][j].content == enemyCell) {
						/* Armazeno a célula inimiga em uma lista dinamica de coordenadas inteiras */
						System.out.print("FOUND AN ENEMY CELL (" + enemyCell +") AT [" +i+"]["+j+"]" );
						badNeighbors.add(new Coordinate(i,j));
						System.out.println(" - WATCHING TARGET, OVER.");
					}
				}
			}
		}
		return badNeighbors;
	}

	/*** Verifica toda a direção de uma coordenada (em relação a outra) até encontrar uma peça aliada ***/
	public boolean findAllies(int x, int y, char alliedCell, ArrayList<Coordinate> badNeighbors, Cell[][] matrix) {
		for (int i = 0; i < badNeighbors.size(); i++) {
			/* Diagonal superior esquerda */
			if (badNeighbors.get(i).x == x-1 && badNeighbors.get(i).y == y-1) {
				System.out.println("Encontrei ["+badNeighbors.get(i).x + "]["+badNeighbors.get(i).y+"] na Diagonal superior esquerda");
				/* Busca direcionada na diagonal superior esquerda */
				for (int j = badNeighbors.get(i).y; j >= 0; j--) {
					if (matrix[j][j].content == alliedCell) {
						System.out.println("Encontrei um aliado na diagonal superior esquerda ["+j+"]["+j+"]");
						/* Quebra da condição ao achar um elemento aliado */
						j = -1;
						return true;
					}
				}
			}
			/* Esquerda */
			else if(badNeighbors.get(i).x == x-0 && badNeighbors.get(i).y == y-1) {
				System.out.println("Encontrei ["+badNeighbors.get(i).x + "]["+badNeighbors.get(i).y+"] esquerda");
				/* Busca direcionada na esquerda */
				for (int j = badNeighbors.get(i).y; j >= 0; j--) {
					if (matrix[badNeighbors.get(i).x][j].content == alliedCell) {
						System.out.println("Encontrei um aliado na esquerda ["+badNeighbors.get(i).x+"]["+j+"]");
						/* Quebra da condição ao achar um elemento aliado */
						j = -1;
						return true;
					}
				}
			}
			/* Diagonal inferior esquerda */
			else if(badNeighbors.get(i).x == x+1 && badNeighbors.get(i).y == y-1) {
				System.out.println("Encontrei [" + badNeighbors.get(i).x + "][" +badNeighbors.get(i).y+ "]  inferior esquerda");
				/* Busca direcionada na diagonal inferior esquerda */
				for (int j = 1; (badNeighbors.get(i).y-j >= 0) && (badNeighbors.get(i).x+j < SIZE); j++) {
					if (matrix[badNeighbors.get(i).x+j][badNeighbors.get(i).y-j].content == alliedCell) {
						System.out.println("Encontrei um aliado na diagonal inferior esquerda ["+(badNeighbors.get(i).x+j)+"]["+(badNeighbors.get(i).y-j)+"]");
						/* Quebra da condição ao achar um elemento aliado */
						j = badNeighbors.get(i).y +1;
						return true;
					}
				}
			}
			/* Baixo */
			else if(badNeighbors.get(i).x == x+1 && badNeighbors.get(i).y == y-0) {
				System.out.println("Encontrei [" + badNeighbors.get(i).x + "][" +badNeighbors.get(i).y+ "] abaixo");
				/* Busca direcionada pra baixo */
				for (int j = badNeighbors.get(i).x; j < SIZE; j++) {
					if (matrix[j][badNeighbors.get(i).y].content == alliedCell) {
						System.out.println("Encontrei um aliado embaixo ["+j+"]["+badNeighbors.get(i).y+"]");
						/* Quebra da condição ao achar um elemento aliado */
						j = SIZE;
						return true;
					}
				}
			}
			/* Diagonal inferior direita */
			else if(badNeighbors.get(i).x == x+1 && badNeighbors.get(i).y == y+1) {
				System.out.println("Encontrei [" + badNeighbors.get(i).x + "][" +badNeighbors.get(i).y+ "] Diagonal inferior direita");
				/* Busca direcionada na diagonal inferior direita */
				for (int j = badNeighbors.get(i).y; j < SIZE; j++) {
					if (matrix[j][j].content == alliedCell) {
						System.out.println("Encontrei um aliado na diagonal inferior direita ["+j+"]["+j+"]");
						/* Quebra da condição ao achar um elemento aliado */
						j = SIZE;
						return true;
					}
				}
			}
			/* Direita */
			else if(badNeighbors.get(i).x == x-0 && badNeighbors.get(i).y == y+1) {
				System.out.println("Encontrei ["+badNeighbors.get(i).x + "]["+badNeighbors.get(i).y+"] na direita");
				/* Busca direcionada na direita */
				for (int j = badNeighbors.get(i).y; j < SIZE; j++) {
					if (matrix[badNeighbors.get(i).x][j].content == alliedCell) {
						System.out.println("Encontrei um aliado na direita ["+badNeighbors.get(i).x+"]["+j+"]");
						/* Quebra da condição ao achar um elemento aliado */
						j = SIZE;
						return true;
					}
				}
			}
			/* Diagonal superior direita */
			else if(badNeighbors.get(i).x == x-1 && badNeighbors.get(i).y == y+1) {
				System.out.println("Encontrei [" + badNeighbors.get(i).x + "][" +badNeighbors.get(i).y+ "] Diagonal superior direita");
				/* Busca direcionada na diagonal superior direita */
				for (int j = 1; (badNeighbors.get(i).x-j >= 0) && (badNeighbors.get(i).y+j < SIZE); j++) {
					if (matrix[badNeighbors.get(i).x-j][badNeighbors.get(i).y+j].content == alliedCell) {
						System.out.println("Encontrei um aliado na diagonal superior direita ["+(badNeighbors.get(i).x-j)+"]["+(badNeighbors.get(i).y+j)+"]");
						/* Quebra da condição ao achar um elemento aliado */
						j = badNeighbors.get(i).x +1;
						return true;
					}
				}
			}
			/* Em cima */
			else if(badNeighbors.get(i).x == x-1 && badNeighbors.get(i).y == y-0) {
				System.out.println("Encontrei ["+badNeighbors.get(i).x + "]["+badNeighbors.get(i).y+"] em cima");
				/* Busca direcionada pra cima */
				for (int j = badNeighbors.get(i).x; j >= 0; j--) {
					if (matrix[j][badNeighbors.get(i).y].content == alliedCell) {
						System.out.println("Encontrei um aliado logo acima ["+j+"]["+badNeighbors.get(i).y+"]");
						/* Quebra da condição ao achar um elemento aliado */
						j = -1;
						return true;
					}
				}
			}
			/* Erro - não encaixou em nenhum dos casos */
			else {
				System.out.println("Erro ao verificar linha de aliados!");
				return false;
			}
		}
		return false;
	}

	/*** Verifica toda a matriz para identificar casas jogáveis no turno ***/
	public ArrayList<Coordinate> findPlayableCells(Cell[][] board, char enemyCell, char alliedCell){
		ArrayList<Coordinate> playableCells = new ArrayList<Coordinate>();
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				/* Caso a célular esteja vazia */
				if (this.isEmpty(i, j, board)) {
					/* Caso a célula tenha inimigos ao seu redor */
					if (this.hasBadNeighborhood(i, j, enemyCell, board).size() > 0) {
						/* E possua aliado(s) a frente desse(s) inimigo(s) */
						if (this.findAllies(i, j, alliedCell, this.hasBadNeighborhood(i, j, enemyCell, board), board)) {
							playableCells.add(new Coordinate(i, j));
						}
					}
				}
			}
		}
		/* Retorna uma lista com todas as posições jogáveis no momento */
		return playableCells;
	}
	
	/*** Imprime uma lista de possíveis jogadas ***/
	public void printPlayableCells(ArrayList<Coordinate> playableCells) {
		if (playableCells.size() > 0) {
			System.out.println("Possíveis jogadas:");
			for (int i = 0; i < playableCells.size(); i++) {
				System.out.println("Jogada "+(i+1)+": ("+playableCells.get(i).x+","+playableCells.get(i).y+")");
			}
		} else {
			System.out.println("Não existem jogadas possíveis.");
		}
	}
	
	/*** Método de inserção no tabuleiro ***/
	public void insertItem(int x, int y, char newContent, String player) {
		System.out.println("Jogada: " + player + " jogou na casa ["+x+"]["+y+"] = " + newContent);
		this.cell[x][y].content = newContent;
	}

	/*** Reseta o tabuleiro ***/
	public void resetBoard() {
		for (int i = 0; i < this.cell.length; i++) {
			for (int j = 0; j < this.cell[i].length; j++) {
				if (i==((SIZE/2)-1) && j==((SIZE/2)-1) || i==(SIZE/2) && j==(SIZE/2)) {
					this.cell[i][j].content = 'O';
				}else if(i==((SIZE/2)-1) && j==(SIZE/2) || i==(SIZE/2) && j==((SIZE/2)-1)){
					this.cell[i][j].content = 'X';
				}
			}
		}
	}

	/*** Método Imprimir tabuleiro na tela ***/
	public void printBoard(Cell[][] board) {
		/**** Limpa a tela para exibir a matriz ****/
		this.screenClear();

		/**** Topo da tabela ****/
		for (int i = 0; i < board.length; i++) {
			if (i == 0)
				System.out.print("   ");

			System.out.print("_"+ i +"_ ");

			if (i == board.length-1)
				System.out.println();
		}

		/**** Imprime o resto da tabela ****/
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (j == 0) {System.out.print(i + " |_" + board[i][j].content + "_|");}
				else {System.out.print("_" + board[i][j].content + "_|");}
			}
			System.out.println();
		}
	}

	/*** "Limpa" a tela ***/
	public void screenClear(){
		for(int i = 0; i <= 20; i++){
			System.out.println();
		}
	}

	/***************************** GET'N SET *****************************/
	public Cell[][] getCell() {
		return cell;
	}
	public void setCell(Cell[][] cell) {
		this.cell = cell;
	}

	public static void main(String[] args) {
		Board board = new Board();

		Cell celulinha[][] = new Cell[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				celulinha[i][j] = new Cell();
			}
		}

		board.setCell(celulinha);
		board.printBoard(board.getCell());
		//board.insertItem(0, 0, 'X', "Humano");
		board.resetBoard();
		board.printBoard(board.getCell());
		//board.hasBadNeighborhood(3, 3, 'X');
		
		/*** Teste das direções básicas ***/
		/* Inserir a direita para teste */
		board.insertItem(3, 5, 'X', "Humano");
		board.insertItem(3, 6, 'O', "Humano");
		board.insertItem(3, 7, 'O', "Humano");
		
		/* Inserir logo abaixo para teste*/
		board.insertItem(5, 3, 'X', "Humano");
		board.insertItem(6, 3, 'O', "Humano");
		board.insertItem(7, 3, 'O', "Humano");
		
		/* Inserir esquerda para teste */
		board.insertItem(3, 2, 'X', "Humano");
		board.insertItem(3, 1, 'O', "Humano");
		board.insertItem(3, 0, 'O', "Humano");
		
		/* Inserir logo acima para teste*/
		board.insertItem(2, 3, 'X', "Humano");
		board.insertItem(1, 3, 'O', "Humano");
		board.insertItem(0, 3, 'O', "Humano");
		
		/*** Teste das direções complexas ***/
		/* Inserir a diagonal superior esquerda para teste */
		board.insertItem(2, 2, 'X', "Humano");
		board.insertItem(1, 1, 'O', "Humano");
		board.insertItem(0, 0, 'O', "Humano");
		
		/* Inserir a diagonal inferior esquerda para teste */
		board.insertItem(4, 2, 'X', "Humano");
		board.insertItem(5, 1, 'O', "Humano");
		board.insertItem(6, 0, 'O', "Humano");
		
		/* Inserir a diagonal superior direita para teste */
		board.insertItem(2, 4, 'X', "Humano");
		board.insertItem(1, 5, 'O', "Humano");
		board.insertItem(0, 6, 'O', "Humano");
		
		/* Inserir a diagonal inferior direita para teste */
		board.insertItem(4, 4, 'X', "Humano");
		board.insertItem(5, 5, 'X', "Humano");
		board.insertItem(6, 6, 'O', "Humano");
		board.insertItem(7, 7, 'O', "Humano");
		
		board.printBoard(board.getCell());
		//board.findAllies(2, 5, 'O', board.hasBadNeighborhood(2, 5, 'X', board.getCell()), board.getCell());
		board.printPlayableCells(board.findPlayableCells(board.getCell(), 'X', 'O'));
		board.printBoard(board.getCell());
	}
}
