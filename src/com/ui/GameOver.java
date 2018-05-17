package com.ui;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
		gameOverFrame.setBounds(100, 100, 802, 573);
		gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverFrame.getContentPane().setLayout(null);
		
		JLabel lblgameOver = new JLabel("Game Over");
		lblgameOver.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblgameOver.setIcon(null);
		lblgameOver.setBounds(155, 77, 567, 136);
		gameOverFrame.getContentPane().add(lblgameOver);

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
