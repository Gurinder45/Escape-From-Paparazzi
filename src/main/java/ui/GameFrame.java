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

/**
 * Frame which handles all the display panels
 * 
 * @author Gurinder Bhogal
 */

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
	 * Adds all the different display panels to a card layout
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
	 * Changes panel to the game panel and starts the thread
	 */
	public void startGame() {
		this.addKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "gamePanel");
		paused = false;
		startThread();
	}

	/**
	 * this methods reset things such as score and items and then show the game window
	 * Resets game elements and displays the game panel for a game restart
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
	 * Stops the game and displays losing panel
	 */
	public void loseGame() {
		paused = true;
		this.removeKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "losePanel");
	}

	/**
	 * this method is used when the user wins the game
	 * it will show the win panel
	 * Stops the game and displays winning panel
	 */
	public void winGame() {
		paused = true;
		this.removeKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "winPanel");
	}

	/**
	 * this method reates a new thread and automatically call the run method
	 * and start the theread
	 * Begins the game thread
	 */
	public void startThread() {
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * this method creates a game loop which will update things such as
	 * chracter position and draw the screen with updated position
	 * Tells the game panel to update and repaint
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
	 * Get the game panel of the frame
	 * 
	 * @return the game panel
	 */
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	/**
	 * @return input handler
	 * Get the input handler for the frame
	 * 
	 * @return the input handler
	 */
	public InputHandler getInputHandler() {
		return inpHandler;
	}

	/**
	 * @return collision finder
	 * Get the collision finder of the frame
	 * 
	 * @return the collision finder
	 */
	public CollisionFinder getCollisionFinder() {
		return collisionFinder;
	}

	/**
	 * @return 
	 * Get the enemy movement handler of the frame
	 * 
	 * @return the enemy movement handler
	 */
	public EnemyMovement getEnemyMovement() {
		return enemyMovement;
	}

	/**
	 * this method will add the item score to the current total score
	 * @param val
	 * Add the given score to the score total
	 * 
	 * @param val value to be added
	 */
	public void addScore(int val) {
		score.addScore(val);
	}

	/**
	 * this method will remove certain points from the total score
	 * @param val
	 * Subtract the given score from the score total
	 * 
	 * @param val value to be subtracted
	 */
	public void substractScore(int val) {
		score.substractScore(val);
	}

	/**
	 * 
	 * @return the total score 
	 * Get the current score
	 * 
	 * @return the current score
	 */
	public int getScore() {
		return score.getScore();
	}

	/**
	 * 
	 * toggles the game in and out of the pauses state
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
	 * returns true if the game is the paused state, false if not
	 * 
	 * @return
	 */
	public boolean isPaused() {
		return paused;
	}

	/**
	 * 
	 * @param num
	 * for the given the given number it loads corresponding reward
	 * 
	 * @param num corresponds to the reward number to load
	 */
	public void loadBonusRewards(int num) {
		gamePanel.loadBonusRewards(num);
	}

	/**
	 * 
	 * @return
	 * Returns all the paparazzi in the game
	 * 
	 * @return an array of type paparazzi
	 */
	public Paparazzi[] getPaparazzis() {
		return gamePanel.getPaparazzis();
	}
}
