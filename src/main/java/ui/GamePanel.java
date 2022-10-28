package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import utilities.Direction;
import utilities.InputHandler;

public class GamePanel extends JPanel implements Runnable{
	
	Thread thread;
	
	InputHandler inpHandler = new InputHandler();
	
	int fps = 60;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(768, 576));
		this.setDoubleBuffered(true);
		this.setBackground(Color.black);
		this.addKeyListener(inpHandler);
		this.setFocusable(true);
		
	}
	
	public void startThread() {
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		double interval = 1000000000/fps;
		double nextStart = System.nanoTime() + interval;
		while(thread != null) {
			long curTime = System.nanoTime();
			
		
			update();
			repaint();
			
			
			
			try {
				double timeRemaining = (nextStart - System.nanoTime())/1000000;
				
				if(timeRemaining < 0) {
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
		if(inpHandler.getDirection() == Direction.UP) {
			
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
}
