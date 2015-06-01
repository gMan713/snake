import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Snake implements KeyListener {

	private boolean alive;
	private int growsLeft;
	private int speed;
	private int direction; // 1,2,3,4 is N,E,S,W
	private int length = 4;
	private Color color;
	private ArrayList<Segment> segments;

	public Snake(int speed, Color color) {
		alive = true;
		this.speed = speed;
		direction = 2;
		this.color = color;
		segments = new ArrayList<Segment>();// 0 is the front of the snake
		segments.add(new Segment(50, Game.HEIGHT / 2, color));
		segments.add(new Segment(60, Game.HEIGHT / 2, color));
		segments.add(new Segment(70, Game.HEIGHT / 2, color));
		segments.add(new Segment(80, Game.HEIGHT / 2, color));
	}

	public void grow() {
		growsLeft = 8;
	}

	public void move() {
		if (growsLeft > 0) {
			if (direction == 1) {
				segments.add(0, new Segment(getX(), getY() - 10, color));
			} else if (direction == 2) {
				segments.add(0, new Segment(getX() + 10, getY(), color));
			} else if (direction == 3) {
				segments.add(0, new Segment(getX(), getY() + 10, color));
			} else if (direction == 4) {
				segments.add(0, new Segment(getX() - 10, getY(), color));
			}
			growsLeft--;
			length++;
		} else {
			Segment last = segments.get(segments.size()-1);
			if (direction == 1) {
				last.move(getX(), getY() - 10);
				addToFront(last);
			} else if (direction == 2) {
				last.move(getX() + 10, getY());
				addToFront(last);
			} else if (direction == 3) {
				last.move(getX(), getY() + 10);
				addToFront(last);
			} else if (direction == 4) {
				last.move(getX() - 10, getY());
				addToFront(last);
			}
		}
	}
	
	public void addToFront(Segment last){
		segments.remove(last);
		segments.add(0, last);
		
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < segments.size(); i++) {
			segments.get(i).paint(g);
		}
	}

	public int getX() {
		return segments.get(0).getX();
	}

	public int getY() {
		return segments.get(0).getY();
	}

	public boolean isOn(Fruit fruit) {
		for (Segment segment : segments) {
			if (segment.getX() == fruit.getX()
					&& segment.getY() == fruit.getY()) {
				return true;
			}
		}
		return false;
	}

	public boolean isAlive() {
		int width = Game.WIDTH;
		int height = Game.HEIGHT;
		for (int i = 1; i < segments.size(); i++) {
			if (getX() == segments.get(i).getX()
					&& getY() == segments.get(i).getY()) {
				alive = false;
			}
		}
		if (getX() < 0 || getX() > width || getY() < 0 || getY() > height) {
			return false;
		}
		return true;
	}

	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			direction = 1;
		} else if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
			direction = 2;
		} else if (k.getKeyCode() == KeyEvent.VK_DOWN) {
			direction = 3;
		} else if (k.getKeyCode() == KeyEvent.VK_LEFT) {
			direction = 4;
		}
	}

	public void keyReleased(KeyEvent k) {
	}

	public void keyTyped(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Game.pause();
		}
	}
}
