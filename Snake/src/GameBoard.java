import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;

public class GameBoard extends JLabel
{
  private Snake snake;
  private int   score;
  private Color backgroundColor;
  private Fruit fruit;
  public GameBoard(int diff, Color snakeColor, Color backgroundColor)
  {
    super();
    setBackground(backgroundColor);
    setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
    score = 0;
    snake = new Snake(diff, snakeColor);
    fruit = new Fruit();
    add(fruit);
    setVisible(true);
  }
  public boolean play()
  {
    if (snake.isOn(fruit))
    {
      score += 1;
      fruit.move(snake);
    }
    if (snake.isDead()) { return false; }
    return true;
  }
}
