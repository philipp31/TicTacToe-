package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	private GamePanel gamePanel;

	public GameWindow(int width, int height) {
		setTitle("TicTacToe-Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, width, height);
		setLocationRelativeTo(null); // in der mitte des Bildschirms zeigen
		setResizable(false);
		setLayout(new BorderLayout());
		gamePanel = new GamePanel();
		//add(gamePanel);
		getContentPane().add(gamePanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void setRechtecke() {
		gamePanel.setRechteckArray();
	}
}
