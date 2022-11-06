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
public class WinPanel extends JPanel {
	private JButton restartBtn;
	private GameFrame gFrame;
	private BufferedImage backgroundImg;

	public WinPanel(GameFrame gFrame) {
		this.gFrame = gFrame;
		this.setLayout(null);
		loadBackground();
		this.restartBtn = new JButton("RESTART");
		this.add(restartBtn);
		// restartBtn.setBounds((gFrame.screenWidth / 2) - 40, (gFrame.screenHeight / 2)
		// - 15, 80, 30);
		restartBtn.setBounds(710, 450, 300, 100);
		restartBtn.setBackground(new Color(143, 0, 225));
		restartBtn.setForeground(Color.YELLOW);
		restartBtn.setFocusPainted(false);
		restartBtn.setFont(new Font("Serif", Font.BOLD, 40));
		addActListeners();

	}

	/**
	 * 
	 * this method will add the action listener to the list
	 * when the button is clicked, it will start the game
	 */
	public void addActListeners() {
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gFrame.restartGame();
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
		g2d.drawImage(backgroundImg, 0, 0, gFrame.screenWidth, gFrame.screenHeight, null);

		String text = "YOU WON!";
		g2d.setBackground(new Color(143, 0, 225));
		g2d.setFont(new Font("Serif", Font.BOLD, 65));

		g2d.setColor(Color.black);
		g2d.drawString(text, 710, 370);
	}
}