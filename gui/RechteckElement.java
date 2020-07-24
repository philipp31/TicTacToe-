package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RechteckElement extends Rectangle {
	
	private FieldValue value;
	private boolean setColor;
	
	public RechteckElement(int x, int y, int width, int height) {
		super(x, y, width, height);
		value = FieldValue.EMPTY;
		setColor = false;
	}
	
// Jedes Element des GamePanels soll sich selber zeichnen können:
	public void draw(Graphics2D g2d) {
		// Rahmen zeichnen:
		if(!setColor) {
			g2d.setColor(Color.BLACK);
		} else {
			g2d.setColor(Color.ORANGE);
		}
		if(setColor) {
			g2d.fillRect(x+5,y+5,width-10,height-10);
			g2d.setColor(Color.BLACK);
		}
		g2d.drawRect(x,y,width,height); 
		// Symbole zeichnen:
		if(value == FieldValue.O) {
			g2d.drawOval(x+5, y+5, width-10, height-10);
		} else if(value == FieldValue.X) {
			g2d.drawLine(x+5, y+5, x+width-5, y+height-5);
			g2d.drawLine(x+width-5, y+5, x+5, y+height-5);
		}
	}
	
	public void setColor(boolean k) {
		setColor = k;
	}

	public FieldValue getValue() {
		return value;
	}

	public void setValue(FieldValue value) {
		this.value = value;
	}
}
