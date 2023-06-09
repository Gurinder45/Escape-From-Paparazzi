package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class will create and design the start panel
 * 
 * @author Haruka Mibuchi
 */
public class StartPanel extends JPanel {
	private static StartPanel instance = null;
	private JButton startBtn;
	private BufferedImage backgroundImg;

	private StartPanel() {
		instance = this;
		this.setLayout(null);
		loadBackground();
		this.startBtn = new JButton("START");
		this.add(startBtn);
		startBtn.setBounds(750, 370, 200, 100);
		startBtn.setBackground(new Color(143, 0, 225));
		startBtn.setForeground(Color.BLACK);
		startBtn.setFocusPainted(false);
		startBtn.setFont(new Font("Serif", Font.BOLD, 40));
		addActListeners();

	}

	public static StartPanel getInstance() {
		if (instance == null) {
			new StartPanel();
		}
		return instance;
	}

	/**
	 * 
	 * this method will add the action listener to the list
	 * when the button is clicked, it will start the game
	 */
	public void addActListeners() {
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameFrame.getInstance().startGame();
			}
		});
	}

	/**
	 * 
	 * this method will load the background image
	 */
	public void loadBackground() {
		try {
			backgroundImg = ImageIO.read(getClass().getResourceAsStream("/images/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();

	}

	/**
	 * 
	 * this method will design and add the buttons in the paused panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backgroundImg, 0, 0, GameFrame.getInstance().screenWidth, GameFrame.getInstance().screenHeight,
				null);
	}
}
