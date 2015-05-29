import java.awt.*;

public class GameBoard extends Canvas{

	private Snake snake;
	private int score;
	private Fruit fruit;

	public GameBoard(int speed, Color snakeColor, Color backgroundColor) {
		snake = new Snake(speed, snakeColor);
		fruit = new Fruit();
		
		setBackground(backgroundColor);
		setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		
		score = 0;
	}

	public boolean play() {
		
		snake.move();
		
		if (snake.isOn(fruit)) {
			score += 1;
			snake.grow();
			while (snake.isOn(fruit)) {
				fruit.move(snake);
			}
		}
		return snake.isAlive();
	}
	
	public void paint(Graphics g){
		snake.paint(g);
		fruit.paint(g);
	}
}
