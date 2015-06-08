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
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Game extends JFrame {

	private JFrame j = new JFrame();
	private BufferedImage backgroundPic;
	private BufferedImage myPicture;
	private GameBoard board;
	private Timer timer;
	private ActionListener actListener;
	private ActionListener playListener;

	private Color snakeColor;
	private int speed;
	private Color backgroundColor;
	private boolean running = false;

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;

	public Game() {

		super("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);

		menu();
	}

	public void menu() {
		try {
			myPicture = ImageIO.read(new File("Blue Snake.jpg"));
			final JLabel background = new JLabel(new ImageIcon(myPicture));
			add(background);
			background.setLayout(new FlowLayout());

			final JButton option = new JButton();
			option.setText("Options");

			// background.add(option);

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
			background.add(play);
			play(background);
			play.addActionListener(playListener);

		} catch (IOException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}

	public void play(final JLabel background) {
		playListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGame(background);
				timer = new Timer(1000 / (speed * 10), actListener);
				timer.start();
			}
		};
	}

	public void playGame(final JLabel background) {

		speed = 3;
		snakeColor = Color.RED;
		backgroundColor = Color.BLACK;
		board = new GameBoard(snakeColor, backgroundColor);

		add(board);
		board.requestFocus();
		board.repaint();
		setVisible(true);
		background.setVisible(false);

		actListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				run();
				board.repaint();
				if (!isRunning()) {
					timer.stop();

					final JPanel deathPanel = new JPanel();
					deathPanel.setSize(300, 100);
					deathPanel.setBackground(Color.LIGHT_GRAY);

					final JLabel scoreLabel = new JLabel("Score: "
							+ board.getScore(), SwingConstants.CENTER);
					int width = scoreLabel.getFontMetrics(scoreLabel.getFont())
							.stringWidth(scoreLabel.getText().toString());
					scoreLabel.setSize(width, 25);
					deathPanel.add(scoreLabel);

					final JButton playAgain = new JButton();
					playAgain.setText("Play Again");
					playAgain.setBackground(Color.WHITE);
					playAgain.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							remove(deathPanel);
							playGame(background);
							timer = new Timer(1000 / (speed * 10), actListener);
							timer.start();

						}
					});
					deathPanel.add(playAgain);

					final JButton menu = new JButton();
					menu.setText("Menu");
					menu.setBackground(Color.WHITE);
					menu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							remove(deathPanel);
							menu();
						}
					});
					deathPanel.add(menu);

					remove(board);
					add(deathPanel);
					setVisible(true);
				}
			}
		};
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
}