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
    setOpaque(true);
    setVisible(true);
  }
  public boolean play()
  {
    snake.move();
    if (snake.isOn(fruit))
    {
      score += 1;
    }
    while (snake.isOn(fruit))
    {
      fruit.move();
    }
    if (snake.isDead()) { return false; }
    return true;
  }
}
