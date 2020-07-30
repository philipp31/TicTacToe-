package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.TicTacToeGame;


public class GamePanel extends JPanel implements MouseListener {
	
	private int gameStatus;
	private int[][] fieldStatus;
	private int[][] winningLine;
	private JLabel schriftzug;
	private RechteckElement[][] rechtecke;
	private WinningInfo infoPanel;

	public GamePanel() {
		firstInit();
		add(schriftzug);
		setBackground(Color.WHITE);
		requestFocus();		//Notwendig für Anklickbarkeit
		addMouseListener(this);	//Gamepanel muss wissen, dass MouseListener fuer ein Objekt dieser Klasse aufgerufen werden sollen->hinzugefuegt	
	}
	
	public void firstInit() {
		gameStatus = 0;
		setBounds(10, 10, 500, 500);
		fieldStatus = new int[3][3];
		winningLine = new int[3][3];
		rechtecke = new RechteckElement[3][3];
		schriftzug = new JLabel("Es ist Spieler X dran.", SwingConstants.CENTER);
		schriftzug.setOpaque(true);		// Sichtbarkeit des BACKROUNDS!
		schriftzug.setBackground(Color.lightGray);
		schriftzug.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// jetzt eigene Zeichnungen:
		Graphics2D g2d = (Graphics2D) g;
		for(RechteckElement elmts : TicTacToeGame.obj.getRecs()) {
			elmts.draw(g2d);
		}
		try {
			schriftzug.setText("Es ist Spieler " + TicTacToeGame.obj.getCurrentPlayer() + " dran.");
		} catch(NullPointerException a) {
			
		}
	
	}
	
	//***********************************
	@Override
	public void mouseClicked(MouseEvent e) {
		checkField(e.getX(),e.getY());	// Position des Klicks übergeben!!
		gameStatus = checkWin();
		showWinningCombination(gameStatus);
		if(gameStatus == -1) {
			infoPanel = new WinningInfo("O");
			if(infoPanel.getPlayAgain()) {	// NEUES SPIEL EINSTELLEN:
				resetGame();
			}
		}
		else if(gameStatus == 1) {
			infoPanel = new WinningInfo("X");
			if(infoPanel.getPlayAgain()) {	// NEUES SPIEL EINSTELLEN:
				resetGame();
			}
		}
	}//*************************************
	
	public void resetGame() {
		for(RechteckElement elmts : TicTacToeGame.obj.getRecs()) {
			elmts.setValue(FieldValue.EMPTY);
			elmts.setColor(false);
		}
		resetWinningField();
		resetCacheField();
		gameStatus = 0;
		repaint();
	}
	
	private void showWinningCombination(int status) {
		if(status != 0) {
			for(int i=0; i<3; i++) {
				for(int k=0; k<3; k++) {
					if(winningLine[i][k] == 1) {
						rechtecke[i][k].setColor(true);
					}
				}
			}
		}	
	}

	private void checkField(int x, int y) {
		//Der Pixel wo die Maus hinklickt:
		Rectangle cursorHitbox =  new Rectangle(x,y,1,1);	
		for(RechteckElement elmts : TicTacToeGame.obj.getRecs()) {
			
		// nur wenn keiner der Spieler gewonnen hat soll das weiterspielen ermoeglicht werden:
			if(gameStatus == 0) {
				
			//Abfrage ob Pixel wo hingeklickt wurde sich mit einem Rechteck überschneidet:
				if(cursorHitbox.intersects(elmts)) {
					if(elmts.getValue() == FieldValue.EMPTY) {
						elmts.setValue(TicTacToeGame.obj.getCurrentPlayer());
						repaint();
						TicTacToeGame.obj.nextPlayerTurn();
					}
					break;
				}
			}
		}
	}

	private int checkWin() {
		FieldValue[][] condition = new FieldValue[3][3];
		fillingStructure(condition);
		
		int rowStatus = checkRows(condition);
		int columnStatus = checkColumns(condition);
		int crosswiseStatus = checkCrosswise(condition);
		int returnValue = 0;
		if(rowStatus == -1 || columnStatus == -1 || crosswiseStatus == -1) {
			returnValue = -1;
		} else if(rowStatus == 1 || columnStatus == 1 || crosswiseStatus == 1) {
			returnValue = 1;
		}
		return returnValue;
	}
	
	public void fillingStructure(FieldValue[][] condition) {
		rechtecke[0][0] = TicTacToeGame.obj.getRecs().get(0);	// Reihe Numero Uno
		rechtecke[1][0] = TicTacToeGame.obj.getRecs().get(1);
		rechtecke[2][0] = TicTacToeGame.obj.getRecs().get(2);
		
		rechtecke[0][1] = TicTacToeGame.obj.getRecs().get(3);	// Reihe Numero Dos
		rechtecke[1][1] = TicTacToeGame.obj.getRecs().get(4);
		rechtecke[2][1] = TicTacToeGame.obj.getRecs().get(5);
		
		rechtecke[0][2] = TicTacToeGame.obj.getRecs().get(6);	// Reihe Numero Trés
		rechtecke[1][2] = TicTacToeGame.obj.getRecs().get(7);
		rechtecke[2][2] = TicTacToeGame.obj.getRecs().get(8);
		// erste Reihe befuellen:
		condition[0][0] = rechtecke[0][0].getValue();
		condition[1][0] = rechtecke[1][0].getValue();
		condition[2][0] = rechtecke[2][0].getValue();
		
		// zweite Reihe befuellen:
		condition[0][1] = rechtecke[0][1].getValue();
		condition[1][1] = rechtecke[1][1].getValue();
		condition[2][1] = rechtecke[2][1].getValue();
	
		// dritte Reihe befuellen:
		condition[0][2] = rechtecke[0][2].getValue();
		condition[1][2] = rechtecke[1][2].getValue();
		condition[2][2] = rechtecke[2][2].getValue();
	}
	
	public int checkCrosswise(FieldValue[][] condition) {
		int counter = 0;
		for(int i=0; i<3; i++) {
			if(condition[i][i] == FieldValue.X) {
				counter++;
				fieldStatus[i][i] = 1;
			}
		}
		if(counter > 2) {
			writeWinningLine();
			return 1;
		} else resetCacheField();
		counter = 0;
		
		for(int i=0; i<3; i++) {
			if(condition[i][i] == FieldValue.O) {
				counter++;
				fieldStatus[i][i] = 1;
			}
		}
		if(counter > 2) {
			writeWinningLine();
			return -1;
		} else resetCacheField();
		counter = 0;
		
		
		
		for(int i=0; i<3; i++) {
			if(condition[i][2-i] == FieldValue.O) {
				counter++;
				fieldStatus[i][2-i] = 1;
			}
		}
		if(counter > 2) {
			writeWinningLine();
			return -1;
		} else resetCacheField();
		
		for(int i=0; i<3; i++) {
			if(condition[i][2-i] == FieldValue.X) {
				counter++;
				fieldStatus[i][2-i] = 1;
			}
		}
		if(counter > 2) {
			writeWinningLine();
			return 1;
		} else resetCacheField();
		
		return 0;
	}
	
	public int checkColumns(FieldValue[][] condition) {
		int counter = 0;
		for(int i=0; i<3; i++) {
			for(int k=0; k<3; k++) {
				if(condition[k][i] == FieldValue.X) {
					counter++;
					fieldStatus[k][i] = 1;
				}
				if(counter > 2) {
					writeWinningLine();
					return 1;
				}
			}
			counter=0;	// nachdem eine Spalte geprueft wurde counter reset
			resetCacheField();
		}	
		counter = 0;
		resetCacheField();
		for(int i=0; i<3; i++) {
			for(int k=0; k<3; k++) {
				if(condition[k][i] == FieldValue.O) {
					counter++;
					fieldStatus[k][i] = 1;
				}
				if(counter > 2) {
					writeWinningLine();
					return -1;
				}
			}
			resetCacheField();
			counter=0;	// nachdem eine Spalte geprueft wurde counter reset
		}
		resetCacheField();
		return 0;
	}
	
	public int checkRows(FieldValue[][] condition) {
		int counter = 0;
		for(int i=0; i<3; i++) {
			for(int k=0; k<3; k++) {
				if(condition[i][k] == FieldValue.X) {
					counter++;
					fieldStatus[i][k] = 1;
				}
				if(counter > 2) {
					writeWinningLine();
					return 1;	// Spieler X gewinnt bei return 1
				}
			}
			resetCacheField();
			counter = 0; 	// nachdem eine Reihe geprueft wurde counter reset
		}
		resetCacheField();
		counter = 0;
		for(int i=0; i<3; i++) {
			for(int k=0; k<3; k++) {
				if(condition[i][k] == FieldValue.O) {
					counter++;
					fieldStatus[i][k] = 1;
				}
				if(counter > 2) {
					writeWinningLine();
					return -1;	// Spieler O gewinnt bei return -1
				}
			}
			resetCacheField();
			counter = 0;	// nachdem eine Reihe geprueft wurde counter reset
		}
		return 0;
	}
	
	private void writeWinningLine() {
		for(int i=0; i<3; i++) {
			for(int k=0; k<3; k++) {
				winningLine[i][k] = fieldStatus[i][k];
			}
		}	
	}
	
	private void resetWinningField() {
		for(int i=0; i<3; i++) {
			for(int k=0; k<3; k++) {
				winningLine[i][k] = 0;
			}
		}
	}
	
	private void resetCacheField() {
		for(int i=0; i<3; i++) {
			for(int k=0; k<3; k++) {
				fieldStatus[i][k] = 0;
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
