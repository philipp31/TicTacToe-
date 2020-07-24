package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {

	public GameWindow(int width, int height) {
		
		setTitle("TicTacToe-Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,width, height);
		setLocationRelativeTo(null); // in der mitte des Bildschirms zeigen
		setResizable(false);
		setLayout(new BorderLayout());
		
		GamePanel gamePanel = new GamePanel();

		//add(gamePanel);
		getContentPane().add(gamePanel, BorderLayout.CENTER);
		setVisible(true);
		System.out.println("gameW. ende");
		
	}
	
}
