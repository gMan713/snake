import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame {

	private JFrame j = new JFrame();
	private BufferedImage backgroundPic;
	private Color snakeColor;
	private int speed;
	private Color backgroundColor;
	private boolean running = false;
	private GameBoard board;
	private Timer timer;
	private ActionListener actListener;
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;

	public Game() {

		super("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(WIDTH, HEIGHT);
		setUndecorated(false);
		setLocationRelativeTo(null);
		BufferedImage myPicture;

		try {

			myPicture = ImageIO.read(new File("Snake/Blue Snake.jpg"));
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
					speed = 1;
					snakeColor = Color.RED;
					backgroundColor = Color.BLACK;
					board = new GameBoard(speed, snakeColor, backgroundColor);

					add(board);
					board.repaint();
					setVisible(true);
					background.setVisible(false);

					actListener = new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							if (!isRunning()) {
							timer.stop();
							}
							run();
							board.repaint();
						}
					};
					setVisible(true);

					timer = new Timer(1000 / 10, actListener);
					timer.start();

					if(!timer.isRunning()){
					remove(board);
					background.setVisible(true);
					setVisible(true);
					}

					setVisible(true);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		setVisible(true);
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
	
	public static void pause(){
		
	}
}