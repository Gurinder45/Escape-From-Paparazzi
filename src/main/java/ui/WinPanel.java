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
		//restartBtn.setBounds((gFrame.screenWidth / 2) - 40, (gFrame.screenHeight / 2) - 15, 80, 30);
		restartBtn.setBounds(750, 370, 200, 100);
		restartBtn.setBackground(new Color(143, 0, 225));
		restartBtn.setForeground(Color.YELLOW);
		restartBtn.setFocusPainted(false);
		restartBtn.setFont(new Font("Serif", Font.BOLD, 40));
		addActListeners();

	}

	public void addActListeners() {
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gFrame.restartGame();
			}
		});
	}

	public void loadBackground() {
		try {
			backgroundImg = ImageIO.read(getClass().getResourceAsStream("/images/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();

	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backgroundImg, 0, 0, gFrame.screenWidth, gFrame.screenHeight, null);

		String text = "YOU WON!";
		// roughly position text in middle of screen
		int x = (gFrame.screenWidth / 2) - (2 * gFrame.cellSize);
		int y = gFrame.screenHeight / 4;
		g2d.setBackground(new Color(143, 0, 225));
		g2d.setFont(new Font("Serif", Font.BOLD, 40));

		g2d.setColor(Color.white);
		g2d.drawString(text, x, y);
	}
}