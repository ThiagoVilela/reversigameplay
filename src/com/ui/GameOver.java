package com.ui;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.reversi.Game;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOver {

	public JFrame gameOverFrame;
	protected Object frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOver window = new GameOver();
					window.gameOverFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameOver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gameOverFrame = new JFrame();
		gameOverFrame.setFont(new Font("Constantia", Font.PLAIN, 12));
		gameOverFrame.setTitle("Reversi Game");
		gameOverFrame.setBounds(10, 00, 300, 47);
		gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverFrame.getContentPane().setLayout(null);

		JLabel lblgameOver = new JLabel("Game Over");
		lblgameOver.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblgameOver.setIcon(null);
		lblgameOver.setBounds(100, 100, 300, 47);
		gameOverFrame.getContentPane().add(lblgameOver);
		int scoreArray[] = Game.board.getScore(Game.board.cell);

		/* Printar o vencedor na tela */
		if (scoreArray[0] > scoreArray[1]) {
			JLabel lblVencedor = new JLabel("Vencedor foi: " + Game.player1.getName() + "");
			lblVencedor.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblVencedor.setBounds(10, 00, 300, 47);
			gameOverFrame.getContentPane().add(lblVencedor);
		}
		else if (scoreArray[0] < scoreArray[1]){
			JLabel lblVencedor = new JLabel("Vencedor foi: " + Game.player2.getName() + "");
			lblVencedor.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblVencedor.setBounds(10, 00, 300, 47);
			gameOverFrame.getContentPane().add(lblVencedor);
		}
		else if  (scoreArray[0] == scoreArray[1]){
			JLabel lblVencedor = new JLabel("Empatou");
			lblVencedor.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblVencedor.setBounds(10, 00, 300, 47);
			gameOverFrame.getContentPane().add(lblVencedor);
		}
		else {
			JLabel lblVencedor = new JLabel("Deu erro não consegue-se identificar o vencedor");
			lblVencedor.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblVencedor.setBounds(10, 00, 300, 47);
			gameOverFrame.getContentPane().add(lblVencedor);			
		}
		
		/*** INICIO - Impressão do tabuleiro ***/
		int paddingLeft = 220;
		int paddingTop = 55;

		JLabel lblMatrizTop = new JLabel(Game.board.saveStringBoard(Game.board.getCell()));
		lblMatrizTop.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatrizTop.setBounds(paddingLeft, paddingTop, 550, 100);
		gameOverFrame.getContentPane().add(lblMatrizTop);

		JLabel lblMatriz0 = new JLabel(Game.board.saveStringBoard2(0,Game.board.getCell()));
		lblMatriz0.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz0.setBounds(paddingLeft, paddingTop+20, 550, 100);
		gameOverFrame.getContentPane().add(lblMatriz0);

		JLabel lblMatriz1 = new JLabel(Game.board.saveStringBoard2(1,Game.board.getCell()));
		lblMatriz1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz1.setBounds(paddingLeft, paddingTop+40, 550, 100);
		gameOverFrame.getContentPane().add(lblMatriz1);

		JLabel lblMatriz2 = new JLabel(Game.board.saveStringBoard2(2,Game.board.getCell()));
		lblMatriz2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz2.setBounds(paddingLeft, paddingTop+60, 550, 100);
		gameOverFrame.getContentPane().add(lblMatriz2);

		JLabel lblMatriz3 = new JLabel(Game.board.saveStringBoard2(3,Game.board.getCell()));
		lblMatriz3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz3.setBounds(paddingLeft, paddingTop+80, 550, 100);
		gameOverFrame.getContentPane().add(lblMatriz3);

		JLabel lblMatriz4 = new JLabel(Game.board.saveStringBoard2(4,Game.board.getCell()));
		lblMatriz4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz4.setBounds(paddingLeft, paddingTop+100, 550, 100);
		gameOverFrame.getContentPane().add(lblMatriz4);

		JLabel lblMatriz5 = new JLabel(Game.board.saveStringBoard2(5,Game.board.getCell()));
		lblMatriz5.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz5.setBounds(paddingLeft, paddingTop+120, 550, 100);
		gameOverFrame.getContentPane().add(lblMatriz5);

		JLabel lblMatriz6 = new JLabel(Game.board.saveStringBoard2(6,Game.board.getCell()));
		lblMatriz6.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz6.setBounds(paddingLeft, paddingTop+140, 550, 100);
		gameOverFrame.getContentPane().add(lblMatriz6);

		JLabel lblMatriz7 = new JLabel(Game.board.saveStringBoard2(7,Game.board.getCell()));
		lblMatriz7.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz7.setBounds(paddingLeft, paddingTop+160, 550, 100);
		gameOverFrame.getContentPane().add(lblMatriz7);
		/*** FIM - Impressão do tabuleiro ***/

		JLabel lblEscolhaSuaOp = new JLabel("O que você deseja fazer?");
		lblEscolhaSuaOp.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEscolhaSuaOp.setBounds(263, 190, 272, 47);
		gameOverFrame.getContentPane().add(lblEscolhaSuaOp);

		JButton novoJogoButton = new JButton("Novo Jogo");
		novoJogoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGame window = new NewGame();
				window.newGameFrame.setVisible(true);
				gameOverFrame.dispose();
			}
		});
		novoJogoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		novoJogoButton.setBounds(155, 306, 110, 50);
		gameOverFrame.getContentPane().add(novoJogoButton);

		JButton sairButton = new JButton("Sair");
		sairButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameOver window = new GameOver();
				window.gameOverFrame.setVisible(false);
				gameOverFrame.dispose();
			}
		});
		sairButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sairButton.setBackground(Color.red)	;
		sairButton.setBounds(555, 306, 80, 50);
		gameOverFrame.getContentPane().add(sairButton);
	}

}