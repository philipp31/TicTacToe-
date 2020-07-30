package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WinningInfo extends JDialog {
	
	private JButton ok;
	private JButton nochmal;
	private JLabel schriftzug;
	private String spieler;
	private Boolean playAgain;
	
	public WinningInfo(String info) {
		super();					// Aufrufen des Konstruktors der Vaterklasse
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(400, 150);						// BREITE, HOEHE
		this.spieler = info;
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		this.setTitle("Spiel vorbei!");
		initComponents();
		playAgain = false;
		// Adding all components:
		setBackground(Color.GRAY);
		add(schriftzug,BorderLayout.NORTH);
		add(ok,BorderLayout.SOUTH);
		add(nochmal);
		setModal(true);
		setVisible(true);
	}
	
	public void initComponents() {
		ok = new JButton("OK schadé");
		nochmal = new JButton("Nochmal spielen!");
		ok.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
		nochmal.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
		schriftzug = new JLabel("   Spieler " + spieler + " hat gewonnen!", SwingConstants.CENTER);
		schriftzug.setSize(100, 50);
		schriftzug.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
		ok.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
	
		});
		
		nochmal.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e) {
            	playAgain = true;
                dispose();
            }
	
		});
	}

	public Boolean getPlayAgain() {
		return playAgain;
	}

	public void setPlayAgain(Boolean playAgain) {
		this.playAgain = playAgain;
	}

}
