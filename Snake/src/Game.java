import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Game extends JFrame {

	private JFrame j = new JFrame();
	private BufferedImage backgroundPic;
	private GameBoard board;
	private Timer timer;

	private Color snakeColor;
	private int speed;
	private Color backgroundColor;
	private boolean running = false;

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;

	public Game() {

		setResizable(false);
		pack();

		setTitle("Snake");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		menu();
	}

	public void run() {
		tick();
		render();
	}

	public void tick() {
		board.tick();
	}

	public void render() {
		board.render();
	}

	public boolean isRunning() {
		return board.isRunning();
	}

	public static void pause() {

	}

	public void menu() {
		try {

			BufferedImage myPicture = ImageIO.read(new File("Blue Snake.jpg"));
			final JLabel background = new JLabel(new ImageIcon(myPicture));
			add(background);
			background.setLayout(new FlowLayout());

			final JButton option = new JButton();
			option.setText("Options");
			option.setBackground(Color.WHITE);

			background.add(option);

			final JButton quit = new JButton();
			quit.setText("Quit");
			quit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			background.add(quit);

			final JButton play = new JButton();
			play.setText("Play");
			play.setBackground(Color.WHITE);
			background.add(play);
			play.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// start game
					speed = 2;
					snakeColor = Color.RED;
					backgroundColor = Color.BLACK;
					board = new GameBoard(snakeColor, backgroundColor);

					add(board);
					setVisible(true);
					background.setVisible(false);

					ActionListener actListener = new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							if (!isRunning()) {
								timer.stop();
							}
							run();
						}
					};
					setVisible(true);

					Timer timer = new Timer(1000 / (speed * 10), actListener);
					timer.start();

					if (!timer.isRunning()) {
						remove(board);
						background.setVisible(true);

					}

					setVisible(true);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}

}