package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Character.Celebrity;
import util.Direction;
import util.InputHandler;

public class GameFrame extends JFrame implements Runnable {
	public int tileSize = 16;
	InputHandler inpHandler = new InputHandler();
	Thread thread;

	int fps = 60;
	Celebrity celebrity = new Celebrity(100, 100, this, inpHandler);
	

	public GameFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(768, 576);
		this.setTitle("Papparazi Escape!");
		this.addKeyListener(inpHandler);
		this.setVisible(true);
	}

	public void startThread() {
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		double interval = 1000000000 / fps;
		double nextStart = System.nanoTime() + interval;

		while (thread != null) {
			update();
			repaint();
			try {
				double timeRemaining = (nextStart - System.nanoTime()) / 1000000;
				if (timeRemaining < 0) {
					timeRemaining = 0;
				}
				Thread.sleep((long) timeRemaining);
				nextStart += interval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public void update() {
		celebrity.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
        celebrity.draw(g2d);
		

		
	}
}
