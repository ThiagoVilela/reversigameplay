package com.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.reversi.Board;
import com.reversi.Game;
import com.reversi.Transition;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class HumanGameScreen {

	public JFrame humanGameScreenFrame;
	private JTextField jogadaLinhaField;
	private JTextField jogadaColunaField;
	protected Object frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HumanGameScreen window = new HumanGameScreen();
					window.humanGameScreenFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HumanGameScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		humanGameScreenFrame = new JFrame();
		humanGameScreenFrame.setFont(new Font("Constantia", Font.PLAIN, 12));
		humanGameScreenFrame.setTitle("Vez de " + Game.namePlayer1 + " jogar ");
		humanGameScreenFrame.setBounds(100, 100, 802, 573);
		humanGameScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		humanGameScreenFrame.getContentPane().setLayout(null);
		
		int paddingLeft = 220;
		int paddingTop = 55;
		
		/*** INICIO - Impress�o do tabuleiro ***/
		JLabel lblMatrizTop = new JLabel(Game.board.saveStringBoard(Game.board.getCell()));
		lblMatrizTop.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatrizTop.setBounds(paddingLeft, paddingTop, 550, 100);
		humanGameScreenFrame.getContentPane().add(lblMatrizTop);
		
		JLabel lblMatriz0 = new JLabel(Game.board.saveStringBoard2(0,Game.board.getCell()));
		lblMatriz0.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz0.setBounds(paddingLeft, paddingTop+20, 550, 100);
		humanGameScreenFrame.getContentPane().add(lblMatriz0);
		
		JLabel lblMatriz1 = new JLabel(Game.board.saveStringBoard2(1,Game.board.getCell()));
		lblMatriz1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz1.setBounds(paddingLeft, paddingTop+40, 550, 100);
		humanGameScreenFrame.getContentPane().add(lblMatriz1);
		
		JLabel lblMatriz2 = new JLabel(Game.board.saveStringBoard2(2,Game.board.getCell()));
		lblMatriz2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz2.setBounds(paddingLeft, paddingTop+60, 550, 100);
		humanGameScreenFrame.getContentPane().add(lblMatriz2);
		
		JLabel lblMatriz3 = new JLabel(Game.board.saveStringBoard2(3,Game.board.getCell()));
		lblMatriz3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz3.setBounds(paddingLeft, paddingTop+80, 550, 100);
		humanGameScreenFrame.getContentPane().add(lblMatriz3);
		
		JLabel lblMatriz4 = new JLabel(Game.board.saveStringBoard2(4,Game.board.getCell()));
		lblMatriz4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz4.setBounds(paddingLeft, paddingTop+100, 550, 100);
		humanGameScreenFrame.getContentPane().add(lblMatriz4);
		
		JLabel lblMatriz5 = new JLabel(Game.board.saveStringBoard2(5,Game.board.getCell()));
		lblMatriz5.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz5.setBounds(paddingLeft, paddingTop+120, 550, 100);
		humanGameScreenFrame.getContentPane().add(lblMatriz5);
		
		JLabel lblMatriz6 = new JLabel(Game.board.saveStringBoard2(6,Game.board.getCell()));
		lblMatriz6.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz6.setBounds(paddingLeft, paddingTop+140, 550, 100);
		humanGameScreenFrame.getContentPane().add(lblMatriz6);
		
		JLabel lblMatriz7 = new JLabel(Game.board.saveStringBoard2(7,Game.board.getCell()));
		lblMatriz7.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMatriz7.setBounds(paddingLeft, paddingTop+160, 550, 100);
		humanGameScreenFrame.getContentPane().add(lblMatriz7);
		/*** FIM - Impress�o do tabuleiro ***/
		
		
		
		
		/*** Imprimir poss�veis jogadas na tela ***/
		ArrayList<Transition> transitions = new ArrayList<Transition>();
		if (Game.playerPlaying == 1) {
			transitions = Game.board.findPlayableCells(Game.board.getCell(), Game.player2.getPiece(), Game.player1.getPiece());
			
		} else if (Game.playerPlaying == 2) {
			transitions = Game.board.findPlayableCells(Game.board.getCell(), Game.player1.getPiece(), Game.player2.getPiece());
			
		} else {
			System.err.println("ERRO AO IDENTIFICAR JOGADOR JOGANDO");
		}

		JLabel lblPossiblePlays = new JLabel(Game.board.savePlayableCells(transitions, Game.board.getCell()));
		lblPossiblePlays.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPossiblePlays.setBounds(100, 250, 550, 150);
		humanGameScreenFrame.getContentPane().add(lblPossiblePlays);
		/*** FIM - Imprimir poss�veis jogadas na tela ***/
		
		
		
		
		/*** Campo para linha ***/
		jogadaLinhaField = new JTextField();
		jogadaLinhaField.setBounds(100, 400, 242, 20);
		humanGameScreenFrame.getContentPane().add(jogadaLinhaField);
		jogadaLinhaField.setColumns(10);

		JLabel jogadaLinhaLabel = new JLabel("Linha:");
		jogadaLinhaLabel.setBounds(100, 380, 100, 14);
		humanGameScreenFrame.getContentPane().add(jogadaLinhaLabel);
		
		
		
		
		/*** Campo para coluna ***/
		jogadaColunaField = new JTextField();
		jogadaColunaField.setBounds(450, 400, 242, 20);
		humanGameScreenFrame.getContentPane().add(jogadaColunaField);
		jogadaColunaField.setColumns(10);

		JLabel nomePlayer2Label = new JLabel("Coluna:");
		nomePlayer2Label.setBounds(450, 380, 100, 14);
		humanGameScreenFrame.getContentPane().add(nomePlayer2Label);
		
		
		
		/*** Campo para jogar ***/
		JButton playButton = new JButton("Jogar");
		JLabel sucessoNomesLabel = new JLabel("Jogada efetuada com sucesso");
		sucessoNomesLabel.setBounds(100, 454, 0, 14);
		humanGameScreenFrame.getContentPane().add(sucessoNomesLabel);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jogadaLinhaField.getText().equals("") || jogadaColunaField.getText().equals("") ){
					sucessoNomesLabel.setText("Preencha todos os campos para jogar.");
					sucessoNomesLabel.setBounds(523, 208, 180, 14);
				} 

				else{
					sucessoNomesLabel.setBounds(523, 208, 0, 14);
					int linhas = -1;
					int colunas = -1;
					/*** Converto os dados ***/
					try {
						linhas = Integer.parseInt(jogadaLinhaField.getText());
						colunas = Integer.parseInt(jogadaColunaField.getText());
					}catch (NumberFormatException e1) {
						System.err.println("Erro ao converter o campo linha/coluna para inteiro");
					}
					
					if (linhas >= 0 && colunas >= 0) {
						/*** Executo a jogada informada pelo jogador ***/
						if (Game.playerPlaying == 1) {
							ArrayList<Transition> transitions = Game.board.findPlayableCells(Game.board.getCell(), Game.player2.getPiece(), Game.player1.getPiece());
							
							Board newBoard = new Board();
							newBoard.setCell(Game.board.getCell());
							
							Game.board.setCell(Game.board.protectedInsertItem(linhas, colunas, Game.player1.getPiece(), Game.player1.getName(), transitions, Game.board.getCell()));
							
							
							if (!newBoard.isBoardEqualAnotherBoard(newBoard.getCell(), Game.board.getCell())) {
								Game.playerPlaying++;
								
								HumanGameScreen window = new HumanGameScreen();
								window.humanGameScreenFrame.setVisible(true);
								humanGameScreenFrame.dispose();
							}
							
							else {
								//Aqui tem de exibir um label de erro
							}
							
							
						} else if (Game.playerPlaying == 2) {
							ArrayList<Transition> transitions = Game.board.findPlayableCells(Game.board.getCell(), Game.player1.getPiece(), Game.player2.getPiece());
							
							Board newBoard = new Board();
							newBoard.setCell(Game.board.getCell());
							Game.board.setCell(Game.board.protectedInsertItem(linhas, colunas, Game.player2.getPiece(), Game.player2.getName(), transitions, Game.board.getCell()));
							
							if (!newBoard.isBoardEqualAnotherBoard(newBoard.getCell(), Game.board.getCell())) {
								Game.playerPlaying--;
								
								HumanGameScreen window = new HumanGameScreen();
								window.humanGameScreenFrame.setVisible(true);
								humanGameScreenFrame.dispose();
							}
							
							else {
								//Aqui tem de exibir um label de erro
							}
	
						} else {
							System.err.println("ERRO AO IDENTIFICAR JOGADOR JOGANDO");
						}
					}
					
					
					
				}
			}
		});
		playButton.setBounds(101, 454, 89, 23);
		humanGameScreenFrame.getContentPane().add(playButton);
		
		
		
		/*** Bot�o para cancelar ***/
		JButton button = new JButton("Cancelar jogo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGame window = new NewGame();
				window.newGameFrame.setVisible(true);
				humanGameScreenFrame.dispose();
			}
		});
		button.setBounds(535, 454, 213, 23);
		humanGameScreenFrame.getContentPane().add(button);
	}
}
