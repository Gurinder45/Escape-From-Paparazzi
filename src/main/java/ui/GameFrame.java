package ui;

import javax.swing.JFrame;
import util.InputHandler;

public class GameFrame extends JFrame implements Runnable {
	public int tileSize = 16;
	Thread thread;
	InputHandler inpHandler = new InputHandler();
	GamePanel gamePanel = new GamePanel(inpHandler);

	int fps = 60;

	public GameFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1152, 864);
		this.setTitle("Papparazi Escape!");
		this.addKeyListener(inpHandler);
		this.add(gamePanel);
		this.setVisible(true);
		startThread();
	}

	public void startThread() {
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		double interval = 1000000000 / fps;
		double nextStart = System.nanoTime() + interval;

		while (thread != null) {
			gamePanel.update();

			gamePanel.repaint();
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
}
