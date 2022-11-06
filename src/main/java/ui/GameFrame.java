/**
 * This class creates a game frame / the window that our game is played on
 * 
 * @author Haruka Mibuchi
 */
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

	/**
	 * this method sets up the panels and their layouts 
	 * then adds all the game panels to our game
	 */
	public void createCardLayout() {
		this.setLayout(cardLayout);
		this.add(startPanel, "startPanel");
		this.add(gamePanel, "gamePanel");
		this.add(pausedPanel, "pausedPanel");
		this.add(winPanel, "winPanel");
		this.add(losePanel, "losePanel");
		cardLayout.show(getContentPane(), "startPanel");
	}

	/**
	 * this methods creates the intro page for a game
	 */
	public void startGame() {
		this.addKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "gamePanel");
		paused = false;
		startThread();
	}

	/**
	 * this methods reset things such as score and items and then show the game window
	 */
	public void restartGame() {
		enemyMovement.clearAll();
		score.restartScore();
		gamePanel.placeElements();
		collisionFinder.resetDisguises();
		this.addKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "gamePanel");
		paused = false;

	}

	/**
	 * this method is used when the user loses the game
	 * it will remove all the key and show the lose panel
	 */
	public void loseGame() {
		paused = true;
		this.removeKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "losePanel");
	}

	/**
	 * this method is used when the user wins the game
	 * it will show the win panel
	 */
	public void winGame() {
		paused = true;
		this.removeKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "winPanel");
	}

	/**
	 * this method reates a new thread and automatically call the run method
	 * and start the theread
	 */
	public void startThread() {
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * this method creates a game loop which will update things such as
	 * chracter position and draw the screen with updated position
	 */
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

	/**
	 * @return the game panel
	 */
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	/**
	 * @return input handler
	 */
	public InputHandler getInputHandler() {
		return inpHandler;
	}

	/**
	 * @return collision finder
	 */
	public CollisionFinder getCollisionFinder() {
		return collisionFinder;
	}

	/**
	 * @return 
	 */
	public EnemyMovement getEnemyMovement() {
		return enemyMovement;
	}

	/**
	 * this method will add the item score to the current total score
	 * @param val
	 */
	public void addScore(int val) {
		score.addScore(val);
	}

	/**
	 * this method will remove certain points from the total score
	 * @param val
	 */
	public void substractScore(int val) {
		score.substractScore(val);

	}

	/**
	 * 
	 * @return the total score 
	 */
	public int getScore() {
		return score.getScore();
	}

	/**
	 * 
	 */
	public void togglePause() {
		if (paused == false) {
			paused = true;
			cardLayout.show(getContentPane(), "pausedPanel");

		} else {
			paused = false;
			cardLayout.show(getContentPane(), "gamePanel");
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPaused() {
		return paused;
	}

	/**
	 * 
	 * @param num
	 */
	public void loadBonusRewards(int num) {
		gamePanel.loadBonusRewards(num);
	}

	/**
	 * 
	 * @return
	 */
	public Paparazzi[] getPaparazzis() {
		return gamePanel.getPaparazzis();
	}
}
