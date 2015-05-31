import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Timer;

public class GameBoard extends Canvas {

	private Snake snake;
	private int score;
	private Fruit fruit;

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
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		paint(g);
		g.dispose();
		bs.show();
	}
}
