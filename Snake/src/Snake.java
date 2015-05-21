import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by ros_cqmarshall on 5/13/2015.
 */
public class Snake
{
  private boolean            alive;
  private boolean            grow;
  private int                speed;
  private int                direction; //1,2,3,4 is N,E,S,W
  private int                length = 4;
  private Color              color;
  private ArrayList<Segment> segments;
  public Snake(int speed, Color color)
  {
    alive = true;
    this.speed = speed;
    direction = 2;
    this.color = color;
    segments = new ArrayList<Segment>();
    segments.add(new Segment(40, 360, color));
  }
  public void grow(int growCount)
  {
    grow = true;
    if (growCount == 4)
    {
      grow = false;
    }
  }
  public void move()
  {
    if (grow == true)
    {
      if (direction == 1)
      {
        segments.add(new Segment(segments.get(0).getX(), segments.get(0).getY() - 10, color));
      }
      else if (direction == 2)
      {
        segments.add(new Segment(segments.get(0).getX() + 10, segments.get(0).getY(), color));
      }
      else if (direction == 3)
      {
        segments.add(new Segment(segments.get(0).getX(), segments.get(0).getY() + 10, color));
      }
      else if (direction == 4)
      {
        segments.add(new Segment(segments.get(0).getX() - 10, segments.get(0).getY(), color));
      }
    }
    if (grow == false)
    {
      if (direction == 1)
      {
        segments.get(segments.size() - 1).move(segments.get(0).getX(), segments.get(0).getY() - 10);
      }
      else if (direction == 2)
      {
        segments.get(segments.size() - 1).move(segments.get(0).getX() + 10, segments.get(0).getY());
      }
      else if (direction == 3)
      {
        segments.get(segments.size() - 1).move(segments.get(0).getX(), segments.get(0).getY() + 10);
      }
      else if (direction == 4)
      {
        segments.get(segments.size() - 1).move(segments.get(0).getX() - 10, segments.get(0).getY());
      }
    }
  }
  public void kill()
  {
    alive = false;
  }
  public void draw()
  {
  }
  public int getX()
  {
    return segments.get(0).getX();
  }
  public int getY()
  {
    return segments.get(0).getY();
  }
  public boolean isOn(Fruit fruit)
  {
    for (Segment segment : segments)
    {
      if (segment.getX() == fruit.getX() && segment.getY() == fruit.getX()) { return true; }
    }
    return false;
  }
  public boolean isDead()
  {
    for (Segment seg2 : segments)
    {
      if (segments.get(0).getX() == seg2.getX() && segments.get(0).getX() == seg2.getY()) { return true; }
    }
    return false;
  }
}