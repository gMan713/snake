import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class GameBoard extends Canvas {

	private Snake snake;
	private int score;
	private Fruit fruit;
	private BufferStrategy bs;

	public GameBoard(Color snakeColor, Color backgroundColor) {
		snake = new Snake(snakeColor);
		fruit = new Fruit();
		setBackground(backgroundColor);
		setSize(Game.WIDTH, Game.HEIGHT);
		score = 0;
		addKeyListener(snake);
		setFocusable(true);
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
			score += 10;
			snake.grow();
			fruit.move(snake);
		}
	}

	public void update(Graphics g) {
		render();
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
