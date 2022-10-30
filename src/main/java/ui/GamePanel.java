package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Character.Celebrity;
import util.InputHandler;

public class GamePanel extends JPanel{
	private Celebrity celebrity;
	private InputHandler InputHandler;
	
	public GamePanel(InputHandler InpHandler) {
		this.InputHandler = InpHandler;
		this.celebrity = new Celebrity(100, 100, this, InpHandler);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("painting");
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
        celebrity.draw(g2d);
        g2d.dispose();	
	}
	
	public void update() {
		celebrity.update();
	}
	
	

}
