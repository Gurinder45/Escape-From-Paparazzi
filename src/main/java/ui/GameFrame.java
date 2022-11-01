package ui;

import java.awt.CardLayout;

import javax.swing.JFrame;

import game.CollisionFinder;
import util.InputHandler;
import util.Score;

public class GameFrame extends JFrame implements Runnable {
	private Thread thread;
	private InputHandler inpHandler;
	private GamePanel gamePanel;
	private StartPanel startPanel;
	private CardLayout cardLayout;
	private CollisionFinder collisionFinder;
	private Score score;
	final public int cellSize = 32;
	final public int columnNum = 36;
	final public int rowNum = 24;
	final public int screenHeight = cellSize * (rowNum + 1);
	final public int screenWidth = cellSize * columnNum;

	int fps = 60;

	public GameFrame() {
		inpHandler = new InputHandler();
		cardLayout = new CardLayout();
		startPanel = new StartPanel(this);
		score = new Score();
		gamePanel = new GamePanel(this);
		collisionFinder = new CollisionFinder(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screenWidth, screenHeight); // 46 columns 27 rows
		this.setTitle("Papparazi Escape!");
		this.addKeyListener(inpHandler);
		this.setFocusable(true);
		this.setVisible(true);
		createCardLayout();
	}
	
	public void createCardLayout() {
		this.setLayout(cardLayout);
		this.add(startPanel, "startPanel");
		this.add(gamePanel, "gamePanel");
		cardLayout.show(getContentPane(), "startPanel");
	}
	
	public void startGame() {
		cardLayout.show(getContentPane(), "gamePanel");
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

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public InputHandler getInputHandler() {
		return inpHandler;
	}

	public CollisionFinder getCollisionFinder() {
		return collisionFinder;
	}
	
	public void addScore(int val){
		score.addScore(val); 
	}
	
	public void substractScore(int val){
		score.substractScore(val);
		 
	}
	
	public int getScore() {
		return score.getScore();
	}
}
