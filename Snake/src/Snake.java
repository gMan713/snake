import java.awt.Color;
import java.awt.Graphics;
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
		segments = new ArrayList<Segment>();
		segments.add(new Segment(40, 360, color));
	}

	public void grow() {
		growsLeft = 4;
	}

	public void move() {
		if (growsLeft > 0) {
			if (direction == 1) {
				segments.add(new Segment(segments.get(0).getX(), segments
						.get(0).getY() - 10, color));
			} else if (direction == 2) {
				segments.add(new Segment(segments.get(0).getX() + 10, segments
						.get(0).getY(), color));
			} else if (direction == 3) {
				segments.add(new Segment(segments.get(0).getX(), segments
						.get(0).getY() + 10, color));
			} else if (direction == 4) {
				segments.add(new Segment(segments.get(0).getX() - 10, segments
						.get(0).getY(), color));
			}
			growsLeft--;
			length++;
		} else {
			if (direction == 1) {
				segments.get(segments.size() - 1).move(segments.get(0).getX(),
						segments.get(0).getY() - 10);
			} else if (direction == 2) {
				segments.get(segments.size() - 1).move(
						segments.get(0).getX() + 10, segments.get(0).getY());
			} else if (direction == 3) {
				segments.get(segments.size() - 1).move(segments.get(0).getX(),
						segments.get(0).getY() + 10);
			} else if (direction == 4) {
				segments.get(segments.size() - 1).move(
						segments.get(0).getX() - 10, segments.get(0).getY());
			}
		}
	}

	public void paint(Graphics g) {
		for (Segment x : segments) {
			x.paint(g);
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
					&& segment.getY() == fruit.getX()) {
				return true;
			}
		}
		return false;
	}

	public boolean isAlive() {
		for (int i = 1; i < segments.size(); i++) {
			if (segments.get(0).getX() == segments.get(i).getX()
					&& segments.get(0).getY() == segments.get(i).getY()) {
				alive = false;
			}
		}
		return alive;
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
		} else if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {

		}
	}

	public void keyReleased(KeyEvent k) {
	}

	public void keyTyped(KeyEvent k) {
	}
}
