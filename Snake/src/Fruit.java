import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;

public class Fruit extends Canvas {
	
	private int x;
	private int y;

	public Fruit() {
		super();
		setBackground(Color.YELLOW);
		this.x = (int) (Math.random()
				* Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 10) * 10;
		this.y = (int) (Math.random()
				* Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 10) * 10;
		System.out.println(x + " " + y);
		setLocation(x, y);
		setSize(new Dimension(8, 8));
		setVisible(true);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void move(Snake snake) {
		this.x = (int) ((int) Math.random() * Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth());
		this.y = (int) ((int) Math.random() * Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight());
		setLocation(x, y);
		while (snake.isOn(this)) {
			move(snake);
		}
		paint(g);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillRect(x+1, y+1, 8, 8);
	}
}
