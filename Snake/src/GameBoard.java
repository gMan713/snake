import java.awt.*;

import javax.swing.JLabel;

public class GameBoard extends JLabel{

	private Snake snake;
	private int score;
	private Color backgroundColor;
	private Fruit fruit;

	public GameBoard(int speed, Color snakeColor, Color backgroundColor) {
		super();
		setBackground(backgroundColor);
		setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		score = 0;
		this.add(new Snake(speed, snakeColor));
		this.add(new Fruit());
		add(fruit);
		setVisible(true);
		setOpaque(true);
		update(g);
	}

	public boolean play() {
		snake.move();
		if (snake.isOn(fruit)) {
			score += 1;
			snake.grow();
			while (snake.isOn(fruit)) {
				fruit.move(snake, g);
			}

		}

		update(g);
		return snake.isAlive();
	}
}
