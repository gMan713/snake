import java.awt.Toolkit;

import javax.swing.JLabel;

public class Fruit extends JLabel
{
  private int x;
  private int y;
  public Fruit()
  {
    this.x = (int) ((int) Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    this.y = (int) ((int) Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    setLocation(x, y);
  }
  public int getX()
  {
    return x;
  }
  public int getY()
  {
    return y;
  }
  public void move(Snake snake)
  {
    this.x = (int) ((int) Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    this.y = (int) ((int) Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    setLocation(x, y);
    while (snake.isOn(this))
    {
      move(snake);
    }
  }
}
