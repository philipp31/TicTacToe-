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

@SuppressWarnings("serial")
public class WinningInfo extends JDialog {
	
	private JButton ok;
	private JButton nochmal;
	private JLabel schriftzug;
	private String spieler;
	private boolean playAgain;
	private boolean fieldFull;
	
	public WinningInfo(String info, boolean fieldFull) {
		super();					// Aufrufen des Konstruktors der Vaterklasse
		this.fieldFull = fieldFull;
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(600, 200);						// BREITE, HOEHE
		this.spieler = info;
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Spiel vorbei!");
		initComponents();
		playAgain = false;
		// Adding all components:
		setBackground(Color.GRAY);
		add(schriftzug, BorderLayout.NORTH);
		add(ok, BorderLayout.SOUTH);
		add(nochmal);
		setModal(true);
		setVisible(true);
	}
	
	public void initComponents() {
		ok = new JButton("OK schadé");
		nochmal = new JButton("Nochmal spielen!");
		ok.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
		nochmal.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
		if(!fieldFull) {
			schriftzug = new JLabel("   Spieler " + spieler + " hat gewonnen!", SwingConstants.CENTER);
			schriftzug.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
		} else {
			schriftzug = new JLabel(" " + spieler, SwingConstants.CENTER);
			schriftzug.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
		}
		schriftzug.setSize(100, 50);
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
