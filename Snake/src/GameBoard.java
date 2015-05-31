import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Timer;

import javafx.scene.input.KeyCode;

public class GameBoard extends Canvas {

	private Snake snake;
	private int score;
	private Fruit fruit;
	private BufferStrategy bs;

	public GameBoard(int speed, Color snakeColor, Color backgroundColor) {
		snake = new Snake(speed, snakeColor);
		fruit = new Fruit();
		setBackground(backgroundColor);
		setSize(Game.WIDTH, Game.HEIGHT);
		score = 0;
	}

	public boolean isRunning() {
		return snake.isAlive();
	}

	public void paint(Graphics g) {
		snake.paint(g);
		fruit.paint(g);
	}

	public void tick() {
		snake.move();
		//listen for key
		if (snake.isOn(fruit)) {
			score += 1;
			snake.grow();
			fruit.move(snake);
		}
	}
	
	public void update(Graphics g){
		repaint();
	}

	public void render() {
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		paint(g);
		g.dispose();
		bs.show();
		bs.dispose();
	}
}
