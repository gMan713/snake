import java.awt.*;

import javax.swing.JLabel;

public class Segment{

	private Color color;
	private int locationX;
	private int locationY;

	public Segment(int x, int y, Color color) {
		locationX = x;
		locationY = y;
		this.color = color;
	}

	public int getX() {
		return locationX;
	}

	public int getY() {
		return locationY;
	}

	public void move(int x, int y) {
		locationX = x;
		locationY = y;
	}
	
	public void paint(Graphics g){
		g.setColor(color);
		g.fillRect(locationX, locationY, 10, 10);
	}
}

