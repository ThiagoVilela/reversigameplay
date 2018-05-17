package com.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.reversi.Game;
import com.reversi.Player;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HumanPC {

	public JFrame humanPCFrame;
	protected Object frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HumanPC window = new HumanPC();
					window.humanPCFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HumanPC() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		humanPCFrame = new JFrame();
		humanPCFrame.setFont(new Font("Constantia", Font.PLAIN, 12));
		humanPCFrame.setTitle("Reversi Game");
		humanPCFrame.setBounds(100, 100, 802, 573);
		humanPCFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		humanPCFrame.getContentPane().setLayout(null);

		JLabel lblEscolhaSuaDif = new JLabel("Escolha sua dificuldade:");
		lblEscolhaSuaDif.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEscolhaSuaDif.setBounds(263, 220, 272, 47);
		humanPCFrame.getContentPane().add(lblEscolhaSuaDif);

		JRadioButton rbnFacil = new JRadioButton("Fácil");
		JRadioButton rbnMedio = new JRadioButton("Médio");
		JRadioButton rbnDificil = new JRadioButton("Difícil");
		rbnMedio.setEnabled(false);
		rbnDificil.setEnabled(false);
		rbnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*rbnMedio.setEnabled(false);
				rbnDificil.setEnabled(false);*/

			}
		});
		rbnFacil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rbnFacil.setBounds(100, 286, 200, 66);
		humanPCFrame.add(rbnFacil);
		rbnMedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*rbnFacil.setEnabled(false);
				rbnDificil.setEnabled(false);*/

			}
		});
		rbnMedio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rbnMedio.setBounds(330, 286, 200, 66);
		humanPCFrame.add(rbnMedio);
		rbnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*rbnFacil.setEnabled(false);
				rbnMedio.setEnabled(false);*/
			}
		});
		rbnDificil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rbnDificil.setBounds(560, 286, 200, 66);
		humanPCFrame.add(rbnDificil);

		JLabel lblPreenchaOCampo = new JLabel("Preencha os campos abaixo com os nomes dos jogadores:");
		lblPreenchaOCampo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPreenchaOCampo.setBounds(101, 95, 557, 30);
		humanPCFrame.getContentPane().add(lblPreenchaOCampo);

		JTextField nomePlayer1Field;
		nomePlayer1Field = new JTextField();
		nomePlayer1Field.setBounds(101, 174, 242, 20);
		humanPCFrame.getContentPane().add(nomePlayer1Field);
		nomePlayer1Field.setColumns(10);

		JLabel nomePlayer1Label = new JLabel("Nome player 1:");
		nomePlayer1Label.setBounds(103, 149, 100, 14);
		humanPCFrame.getContentPane().add(nomePlayer1Label);


		JButton addBotao = new JButton("Começar");
		JLabel sucessoNomesLabel = new JLabel("Turma Adicionada com Sucesso");
		sucessoNomesLabel.setBounds(523, 208, 0, 14);
		humanPCFrame.getContentPane().add(sucessoNomesLabel);
		addBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(nomePlayer1Field.getText().equals("") ){
					sucessoNomesLabel.setText("Preencha todos os campos.");
					sucessoNomesLabel.setBounds(523, 208, 180, 14);
				} 

				else{
					sucessoNomesLabel.setBounds(523, 208, 0, 14);
					Game.namePlayer1 = nomePlayer1Field.getText();
					Game.playerPlaying = 1;
					
					Game.board.resetBoard();
					
					/*** Seto as características dos jogadores ***/
					Game.player1 = new Player(Game.namePlayer1, 'O', 2);
					Game.player2 = new Player("Adalberto", 'X', 2);
				}
			}
		});
		addBotao.setBounds(350, 379, 89, 23);
		humanPCFrame.getContentPane().add(addBotao);
		
		JButton button = new JButton("Voltar para menu principal");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGame window = new NewGame();
				window.newGameFrame.setVisible(true);
				humanPCFrame.dispose();
			}
		});
		button.setBounds(535, 454, 213, 23);
		humanPCFrame.getContentPane().add(button);
	}
}
