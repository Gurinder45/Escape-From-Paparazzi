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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @return
 */

public class StartPanel extends JPanel {

	private JButton startBtn;
	private GameFrame gFrame;
	private BufferedImage backgroundImg;

	public StartPanel(GameFrame gFrame) {
		this.gFrame = gFrame;
		this.setLayout(null);
		loadBackground();
		this.startBtn = new JButton("START");
		this.add(startBtn);
		//startBtn.setBounds((gFrame.screenWidth / 2) - 40, (gFrame.screenHeight / 2) - 15, 80, 30);
		startBtn.setBounds(750, 370, 200, 100);
		startBtn.setBackground(new Color(143, 0, 225));
		startBtn.setForeground(Color.BLACK);
		startBtn.setFocusPainted(false);
		startBtn.setFont(new Font("Serif", Font.BOLD, 40));
		addActListeners();

	}

	/**
	 * 
	 * @return
	 */
	public void addActListeners() {
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gFrame.startGame();
			}
		});
	}

	/**
	 * 
	 * @return
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
	 * @return
	 */
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backgroundImg, 0, 0, gFrame.screenWidth, gFrame.screenHeight, null);
/* 
		String text = "Paparazzi Escape!";
		// roughly position text in middle of screen
		int x = (gFrame.screenWidth / 2) - (2 * gFrame.cellSize);
		int y = gFrame.screenHeight / 4;

		g2d.setColor(Color.white);
		g2d.drawString(text, x, y);*/
	}
}
