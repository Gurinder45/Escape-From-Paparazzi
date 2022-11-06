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
 * This class will control the lose panel
 * 
 * @author Haruka Mibuchi
 */
public class LosePanel extends JPanel {
	private JButton restartBtn;
	private GameFrame gFrame;
	private BufferedImage backgroundImg;

	public LosePanel(GameFrame gFrame) {
		this.gFrame = gFrame;
		this.setLayout(null);
		loadBackground();
		this.restartBtn = new JButton("RESTART");
		this.add(restartBtn);
		restartBtn.setBounds(730, 500, 250, 100);
		restartBtn.setBackground(new Color(225, 225, 0));
		restartBtn.setForeground(Color.BLACK);
		restartBtn.setFocusPainted(false);
		restartBtn.setFont(new Font("Serif", Font.BOLD, 40));
		addActListeners();

	}

	/**
	 * Add action listener to restart button
	 */
	public void addActListeners() {
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gFrame.restartGame();
			}
		});
	}

	/**
	 * load the background image
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
	 * Draws the losing panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backgroundImg, 0, 0, gFrame.screenWidth, gFrame.screenHeight, null);

		String text = "YOU LOST";
		g2d.setFont(new Font("Serif", Font.BOLD, 65));
		g2d.setColor(Color.black);
		g2d.drawString(text, 680, 370); // x, y);
	}
}