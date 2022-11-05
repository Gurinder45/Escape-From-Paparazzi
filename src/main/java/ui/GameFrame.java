package ui;

import java.awt.CardLayout;

import javax.swing.JFrame;

import game.CollisionFinder;
import game.EnemyMovement;
import moveable_entity.Paparazzi;
import util.InputHandler;
import util.Score;

public class GameFrame extends JFrame implements Runnable {
	private Thread thread;
	private InputHandler inpHandler;
	private EnemyMovement enemyMovement;
	private GamePanel gamePanel;
	private StartPanel startPanel;
	private PausedPanel pausedPanel;
	private WinPanel winPanel;
	private LosePanel losePanel;
	private CardLayout cardLayout;
	private CollisionFinder collisionFinder;
	private Score score;
	private boolean paused;
	final public int cellSize = 32;
	final public int columnNum = 36;
	final public int rowNum = 24;
	final public int screenHeight = cellSize * (rowNum + 1);
	final public int screenWidth = cellSize * columnNum;

	int fps = 60;

	public GameFrame() {
		inpHandler = new InputHandler(this);
		cardLayout = new CardLayout();
		startPanel = new StartPanel(this);
		pausedPanel = new PausedPanel(this);
		winPanel = new WinPanel(this);
		losePanel = new LosePanel(this);
		score = new Score();
		enemyMovement = new EnemyMovement(this);
		gamePanel = new GamePanel(this);
		collisionFinder = new CollisionFinder(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screenWidth, screenHeight); // 46 columns 27 rows
		this.setTitle("Papparazi Escape!");
		this.setFocusable(true);
		this.setVisible(true);
		this.setResizable(false);
		createCardLayout();
	}

	public void createCardLayout() {
		this.setLayout(cardLayout);
		this.add(startPanel, "startPanel");
		this.add(gamePanel, "gamePanel");
		this.add(pausedPanel, "pausedPanel");
		this.add(winPanel, "winPanel");
		this.add(losePanel, "losePanel");
		cardLayout.show(getContentPane(), "startPanel");
	}

	public void startGame() {
		this.addKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "gamePanel");
		paused = false;
		startThread();
	}

	public void restartGame() {
		enemyMovement.clearAll();
		score.restartScore();
		gamePanel.placeElements();
		collisionFinder.resetDisguises();
		this.addKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "gamePanel");
		paused = false;

	}

	public void loseGame() {
		paused = true;
		this.removeKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "losePanel");
	}

	public void winGame() {
		paused = true;
		this.removeKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "winPanel");
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

	public EnemyMovement getEnemyMovement() {
		return enemyMovement;
	}

	public void addScore(int val) {
		score.addScore(val);
	}

	public void substractScore(int val) {
		score.substractScore(val);

	}

	public int getScore() {
		return score.getScore();
	}

	public void togglePause() {
		if (paused == false) {
			paused = true;
			cardLayout.show(getContentPane(), "pausedPanel");

		} else {
			paused = false;
			cardLayout.show(getContentPane(), "gamePanel");
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public void loadBonusRewards(int num) {
		gamePanel.loadBonusRewards(num);
	}

	public Paparazzi[] getPaparazzis() {
		return gamePanel.getPaparazzis();
	}
}
