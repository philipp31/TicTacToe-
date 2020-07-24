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
	
	JButton ok;
	JLabel schriftzug;
	String spieler;
	
	public WinningInfo(String info) {
		super();					// Aufrufen des Konstruktors der Vaterklasse
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(400, 150);						// BREITE, HOEHE
		this.spieler = info;
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		this.setTitle("Spiel vorbei!");
		initComponents();
		
		// Adding all components:
		setBackground(Color.GRAY);
		add(schriftzug,BorderLayout.NORTH);
		add(ok,BorderLayout.SOUTH);
		setModal(true);
		setVisible(true);
	}
	
	public void initComponents() {
		ok = new JButton("OK schadé");
		ok.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
		schriftzug = new JLabel("   Spieler " + spieler + " hat gewonnen!", SwingConstants.CENTER);
		schriftzug.setSize(100, 50);
		schriftzug.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
		ok.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
	
		});
	}

}
