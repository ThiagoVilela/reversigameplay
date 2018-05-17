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

		/* Adicionando simbolo jogador na tela */
		if(Game.playerPlaying ==1) {
			JLabel simbolo1Title = new JLabel(Game.player1.getName() + "");
			simbolo1Title.setFont(new Font("Tahoma", Font.PLAIN, 14));
			simbolo1Title.setBounds(10, 0, 300, 47);
			humanGameScreenFrame.getContentPane().add(simbolo1Title);

			JLabel jogadorSimboloLabel1 = new JLabel("Você está jogando com:  " + Game.player1.getPiece() + "");
			jogadorSimboloLabel1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			jogadorSimboloLabel1.setBounds(10, 20, 300, 47);
			humanGameScreenFrame.getContentPane().add(jogadorSimboloLabel1);
		}
		else {
			JLabel simbolo2Title = new JLabel(Game.player2.getName() + "");
			simbolo2Title.setFont(new Font("Tahoma", Font.PLAIN, 14));
			simbolo2Title.setBounds(10, 0, 300, 47);
			humanGameScreenFrame.getContentPane().add(simbolo2Title);

			JLabel jogadorSimboloLabel2 = new JLabel("Você está jogando com:  " + Game.player2.getPiece() + "");
			jogadorSimboloLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			jogadorSimboloLabel2.setBounds(10, 20, 300, 47);
			humanGameScreenFrame.getContentPane().add(jogadorSimboloLabel2);
		}
		/* FIM - Imprimir simbolo jogador na tela */


		/* Adicionando placar */
		JLabel scoreTitle = new JLabel("Score " + Game.player1.getName() + "");
		scoreTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scoreTitle.setBounds(300, 0, 100, 47);
		humanGameScreenFrame.getContentPane().add(scoreTitle);

		int scoreArray[] = Game.board.getScore(Game.board.cell);
		String scoreString1 = scoreArray[0] + "";
		JLabel score1Label = new JLabel(scoreString1);
		score1Label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		score1Label.setBounds(335, 25, 100, 47);
		humanGameScreenFrame.getContentPane().add(score1Label);

		JLabel scoreTitle2 = new JLabel("Score " + Game.player2.getName() + "");
		scoreTitle2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scoreTitle2.setBounds(400, 0, 100, 47);
		humanGameScreenFrame.getContentPane().add(scoreTitle2);

		String scoreString2 = scoreArray[1] + "";
		JLabel score2Label = new JLabel(scoreString2);
		score2Label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		score2Label.setBounds(435, 25, 100, 47);
		humanGameScreenFrame.getContentPane().add(score2Label);
		/* FIM - Adicionar placar */

		/*** INICIO - Impressão do tabuleiro ***/
		int paddingLeft = 220;
		int paddingTop = 55;

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
		/*** FIM - Impressão do tabuleiro ***/




		/*** Imprimir possíveis jogadas na tela ***/
		ArrayList<Transition> transitions = new ArrayList<Transition>();
		if (Game.playerPlaying == 1) {
			transitions = Game.board.findPlayableCells(Game.board.getCell(), Game.player2.getPiece(), Game.player1.getPiece());

		} else if (Game.playerPlaying == 2) {
			transitions = Game.board.findPlayableCells(Game.board.getCell(), Game.player1.getPiece(), Game.player2.getPiece());

		} else {
			System.err.println("ERRO AO IDENTIFICAR JOGADOR JOGANDO");
		}

		if (transitions.size() <= 4) {
			JLabel lblPossiblePlays = new JLabel(Game.board.savePlayableCells(transitions, Game.board.getCell()));
			lblPossiblePlays.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblPossiblePlays.setBounds(100, 250, 550, 150);
			humanGameScreenFrame.getContentPane().add(lblPossiblePlays);
		}
		else if((transitions.size() > 4) && (transitions.size() < 7) ) {
			JLabel lblPossiblePlays = new JLabel(Game.board.savePlayableCells(transitions, Game.board.getCell()));
			lblPossiblePlays.setFont(new Font("Tahoma", Font.PLAIN, 9));
			lblPossiblePlays.setBounds(100, 250, 550, 150);
			humanGameScreenFrame.getContentPane().add(lblPossiblePlays);		
		}
		else if(transitions.size() > 7) {
			JLabel lblPossiblePlays = new JLabel(Game.board.savePlayableCells(transitions, Game.board.getCell()));
			lblPossiblePlays.setFont(new Font("Tahoma", Font.PLAIN, 7));
			lblPossiblePlays.setBounds(100, 250, 550, 150);
			humanGameScreenFrame.getContentPane().add(lblPossiblePlays);		
		}
		/*** FIM - Imprimir possíveis jogadas na tela ***/




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
					if (Game.board.isFull(Game.board.getCell())) {
						//Colocar label de erro de inserção
					}
					else {
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
								newBoard.setCell(newBoard.transferBoardContent(Game.board.getCell()));

								Game.board.setCell(Game.board.protectedInsertItem(linhas, colunas, Game.player1.getPiece(), Game.player1.getName(), transitions, Game.board.getCell()));


								if (newBoard.isBoardDifferentAnotherBoard(newBoard.getCell(), Game.board.getCell())) {
									Game.playerPlaying++;

									HumanGameScreen window = new HumanGameScreen();
									window.humanGameScreenFrame.setVisible(true);
									humanGameScreenFrame.dispose();
								}

								else {
									sucessoNomesLabel.setText("Coordenadas inválidas - Digite novamente");
									sucessoNomesLabel.setBounds(550, 208, 180, 80);
								}


							} else if (Game.playerPlaying == 2) {
								ArrayList<Transition> transitions = Game.board.findPlayableCells(Game.board.getCell(), Game.player1.getPiece(), Game.player2.getPiece());

								Board newBoard = new Board();
								newBoard.setCell(newBoard.transferBoardContent(Game.board.getCell()));
								Game.board.setCell(Game.board.protectedInsertItem(linhas, colunas, Game.player2.getPiece(), Game.player2.getName(), transitions, Game.board.getCell()));

								if (newBoard.isBoardDifferentAnotherBoard(newBoard.getCell(), Game.board.getCell())) {
									Game.playerPlaying--;

									HumanGameScreen window = new HumanGameScreen();
									window.humanGameScreenFrame.setVisible(true);
									humanGameScreenFrame.dispose();
								}

								else {
									sucessoNomesLabel.setText("Coordenadas inválidas - Digite novamente");
									sucessoNomesLabel.setBounds(550, 208, 180, 80);
								}

							} else {
								System.err.println("ERRO AO IDENTIFICAR JOGADOR JOGANDO");
							}
						}
						else {
							sucessoNomesLabel.setText("Coordenadas inválidas - Digite novamente");
							sucessoNomesLabel.setBounds(550, 208, 180, 80);
						}
					}
				}
			}
		});
		playButton.setBounds(100, 454, 89, 23);
		humanGameScreenFrame.getContentPane().add(playButton);



		/*** Botão para cancelar ***/
		JButton button = new JButton("Cancelar jogo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGame window = new NewGame();
				window.newGameFrame.setVisible(true);
				humanGameScreenFrame.dispose();
			}
		});
		button.setBounds(550, 454, 120, 23);
		humanGameScreenFrame.getContentPane().add(button);
	}
}
