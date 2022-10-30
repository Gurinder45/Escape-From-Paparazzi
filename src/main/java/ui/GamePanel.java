package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Character.Celebrity;
import map.Map;
import util.InputHandler;

public class GamePanel extends JPanel {
	private Celebrity celebrity;
	private Map map;
	private GameFrame gFrame;
	

	public GamePanel(InputHandler InpHandler, GameFrame gFrame) {
		this.celebrity = new Celebrity(100, 100, this, InpHandler);
		this.gFrame = gFrame;
		this.map = new Map(gFrame);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		map.draw(g2d);
		celebrity.draw(g2d);
		g2d.dispose();
	}

	public void update() {
		celebrity.update();
	}
	
	public int getCellSize() {
		return gFrame.cellSize;
	}

}
