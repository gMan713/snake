import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;

public class GameBoard extends JLabel, Canvas{

	private Snake snake;
	private int score;
	private Color backgroundColor;
	private Fruit fruit;

	public GameBoard(int speed, Color snakeColor, Color backgroundColor) {
		super();
		setBackground(backgroundColor);
		setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		score = 0;
		snake = new Snake(speed, snakeColor);
		fruit = new Fruit();
		add(fruit);
		setVisible(true);
		setOpaque(true);
		repaint();
	}

	public void play() {
		snake.move();
		if (snake.isOn(fruit)) {
			int i = 0;
			score += 1;
			snake.grow(i);
			while (snake.isOn(fruit)) {
				fruit.move(snake);
			}
		}
		if (snake.isGrowing()){
			i++;
			snake.grow(i);
		}
		repaint();
	}
}
