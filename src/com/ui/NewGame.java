package com.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.reversi.Main;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewGame {
	
	public int typeOfGame = -1;
	JFrame newGameFrame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGame window = new NewGame();
					window.newGameFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		newGameFrame = new JFrame();
		newGameFrame.setFont(new Font("Constantia", Font.PLAIN, 12));
		newGameFrame.setTitle("Modalidade");
		newGameFrame.setBounds(100, 100, 802, 573);
		newGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newGameFrame.getContentPane().setLayout(null);

		JLabel lblBemVindo = new JLabel("Escolhe seu modo de jogo");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblBemVindo.setIcon(null);
		lblBemVindo.setBounds(135, 77, 567, 136);
		newGameFrame.getContentPane().add(lblBemVindo);

		JLabel lblEscolhaSuaOp = new JLabel("O que você deseja fazer?");
		lblEscolhaSuaOp.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEscolhaSuaOp.setBounds(263, 190, 272, 47);
		newGameFrame.getContentPane().add(lblEscolhaSuaOp);

		JButton humaHumaButton = new JButton("Humano X Humano");
		humaHumaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HumanHuman window = new HumanHuman();
				window.humanHumanFrame.setVisible(true);
				newGameFrame.dispose();
				Main.gameType = 0;
			}
		});
		humaHumaButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		humaHumaButton.setBounds(140, 306, 200, 66);
		newGameFrame.getContentPane().add(humaHumaButton);

		JButton humaPCButton = new JButton("Humano X Computador");
		humaPCButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HumanPC window = new HumanPC();
				window.humanPCFrame.setVisible(true);
				newGameFrame.dispose();
				Main.gameType = 1;
			}
		});
		humaPCButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		humaPCButton.setBounds(460, 306, 200, 66);
		newGameFrame.getContentPane().add(humaPCButton);
		
		JButton button = new JButton("Voltar para menu principal");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu window = new Menu();
				window.menuFrame.setVisible(true);
				newGameFrame.dispose();
			}
		});
		button.setBounds(535, 454, 213, 23);
		newGameFrame.getContentPane().add(button);
	}
}
