import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Fruit {

	private int x;
	private int y;

	public Fruit() {
		this.x = (int) (Math.random() * Game.WIDTH / 10) * 10;
		this.y = (int) (Math.random() * Game.HEIGHT / 10) * 10;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void move(Snake snake) {
		this.x = (int) ((int) Math.random() * Game.WIDTH);
		this.y = (int) ((int) Math.random() * Game.HEIGHT);
		while (snake.isOn(this)) {
			move(snake);
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x + 1, y + 1, 10, 10);
	}
}
