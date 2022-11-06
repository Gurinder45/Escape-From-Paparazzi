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
 * This class will design the pause panel
 * 
 * @author Haruka Mibuchi
 */
public class PausedPanel extends JPanel {

	private JButton resumeBtn;
	private GameFrame gFrame;
	private BufferedImage backgroundImg;

	public PausedPanel(GameFrame gFrame) {

		this.gFrame = gFrame;
		this.setLayout(null);
		loadBackground();
		this.resumeBtn = new JButton("RESUME");
		this.add(resumeBtn);
		resumeBtn.setBounds(710, 450, 250, 100);
		resumeBtn.setBackground(new Color(225, 225, 0));
		resumeBtn.setForeground(Color.BLACK);
		resumeBtn.setFocusPainted(false);
		resumeBtn.setFont(new Font("Serif", Font.BOLD, 40));
		addActListeners();

	}

	/**
	 * this method will add the action listerner to the list
	 * when the button is clicked, it will pause the game
	 */
	public void addActListeners() {
		resumeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gFrame.togglePause();
			}
		});
	}

	/**
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
	 * this method will design and add the bottons in the paused panel
	 */
	public void paintComponent(Graphics g) {

		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backgroundImg, 0, 0, gFrame.screenWidth, gFrame.screenHeight, null);

		String text = "Paused";

		g2d.setFont(new Font("Serif", Font.BOLD, 65));
		g2d.setColor(Color.black);
		g2d.drawString(text, 730, 370);
	}
}

