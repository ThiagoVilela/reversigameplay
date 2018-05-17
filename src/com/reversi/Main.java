package com.reversi;

import java.awt.EventQueue;

import com.ui.Menu;

public class Main {
	/***************************** ATRIBUTOS *****************************/
	/*** Variáveis estáticas para linkar com a interface ***/
	public static Game game = new Game();
	public static int gameType = -1;

	/***************************** MAINZAO DA MASSA *****************************/
	public static void main(String[] args) {
		
		/*** Crio a tela de Menu ***/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.menuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/*** Parametro 0 - Humano x Humano ***/
		/*** Parametro 1 - Humano x IA ***/
		//game.startGame(0);
	}
	
	/***************************** GET'N SET *****************************/
}
