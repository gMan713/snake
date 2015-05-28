import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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

public class Game extends JFrame {
	
	private BufferedImage backgroundPic;
	private Color snakeColor;
	private int speed;
	private Color backgroundColor;

	public Game() {
		super("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		setUndecorated(true);
		setLocationRelativeTo(null);
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("Blue Snake.jpg"));
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
				@Override
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
				@Override
				public void actionPerformed(ActionEvent e) {
					// start game
					GameBoard board;
					speed = 1;
					snakeColor = Color.RED;
					backgroundColor = Color.BLACK;
					board = new GameBoard(speed, snakeColor, backgroundColor);
					background.add(board);
					background.remove(play);
					background.remove(quit);
					background.remove(option);
					repaint();
					while (snake.isAlive()) {
						board.play();
					}
					remove(board);
					background.add(play);
					background.add(option);
					background.add(quit);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}
}
