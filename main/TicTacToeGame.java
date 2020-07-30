package main;

import gui.FieldValue;
import gui.GameWindow;
import gui.RechteckElement;
import java.util.ArrayList;

public class TicTacToeGame {
	
	public static TicTacToeGame obj;
	private FieldValue currentPlayer;
	
	private ArrayList<RechteckElement> rechtecke;

	public static void main(String[] args) {
		obj = new TicTacToeGame();
	}
	
	public TicTacToeGame() {
		new GameWindow(600,600);
		initGame();
	}
	
	public void initGame() {
		currentPlayer = FieldValue.X;	// first player is X
		
		int rechteckBreite = 500/3;
		int rechteckHoehe = 500/3;
		rechtecke = new ArrayList<RechteckElement>();
		// erste Reihe:
		rechtecke.add(new RechteckElement(40, 50, rechteckBreite, rechteckHoehe)); // (x,y,Breite,Hoehe)
		rechtecke.add(new RechteckElement(40 + 1*rechteckBreite, 50, rechteckBreite, rechteckHoehe));
		rechtecke.add(new RechteckElement(40 + 2*rechteckBreite, 50, rechteckBreite, rechteckHoehe));
		// zweite Reihe
		rechtecke.add(new RechteckElement(40, 50 + 1*rechteckHoehe, rechteckBreite, rechteckHoehe));
		rechtecke.add(new RechteckElement(40 + 1*rechteckBreite, 50 + 1*rechteckHoehe, rechteckBreite, rechteckHoehe));
		rechtecke.add(new RechteckElement(40 + 2*rechteckBreite, 50 + 1*rechteckHoehe, rechteckBreite, rechteckHoehe));
		// dritte Reihe:
		rechtecke.add(new RechteckElement(40, 50 + 2*rechteckHoehe, rechteckBreite, rechteckHoehe));
		rechtecke.add(new RechteckElement(40 + 1*rechteckBreite, 50 + 2*rechteckHoehe, rechteckBreite, rechteckHoehe));
		rechtecke.add(new RechteckElement(40 + 2*rechteckBreite, 50 + 2*rechteckHoehe, rechteckBreite, rechteckHoehe));

	}
	
	public void nextPlayerTurn() {
		if(currentPlayer == FieldValue.X) {
			currentPlayer = FieldValue.O;
		}
		else {
			currentPlayer = FieldValue.X;
		}
	}
	
	public FieldValue getCurrentPlayer() {
		return currentPlayer;
	}
	
	public ArrayList<RechteckElement> getRecs() {
		return rechtecke;
	}
}
