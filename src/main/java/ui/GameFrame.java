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
import util.InputHandler;
import util.Score;

/**
 * Frame which handles all the display panels
 * 
 * @author Gurinder Bhogal
 */

public class GameFrame extends JFrame implements Runnable {
	private static GameFrame instance = null;
	private Thread thread;
	private InputHandler inpHandler;
	private CardLayout cardLayout;
	private Score score;
	private boolean paused;
	final public int cellSize = 32;
	final public int columnNum = 36;
	final public int rowNum = 24;
	final public int screenHeight = cellSize * (rowNum + 1);
	final public int screenWidth = cellSize * columnNum;

	int fps = 60;

	private GameFrame() {
		instance = this;
		inpHandler = InputHandler.getInstance();
		cardLayout = new CardLayout();
		score = new Score();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screenWidth, screenHeight); // 46 columns 27 rows
		this.setTitle("Papparazi Escape!");
		this.setFocusable(true);
		this.setResizable(false);
		createCardLayout();
	}

	public static GameFrame getInstance() {
		if (instance == null) {
			new GameFrame();
		}
		return instance;
	}

	/**
	 * this method sets up the panels and their layouts
	 * then adds all the game panels to our game
	 * Adds all the different display panels to a card layout
	 */
	public void createCardLayout() {
		this.setLayout(cardLayout);
		this.add(StartPanel.getInstance(), "startPanel");
		this.add(GamePanel.getInstance(), "gamePanel");
		this.add(PausedPanel.getInstance(), "pausedPanel");
		this.add(WinPanel.getInstance(), "winPanel");
		this.add(LosePanel.getInstance(), "losePanel");
		cardLayout.show(getContentPane(), "startPanel");
	}

	/**
	 * Changes panel to the game panel and starts the thread
	 */
	public void startGame() {
		this.addKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "gamePanel");
		paused = false;
		startThread();
	}

	/**
	 * Resets game elements and displays the game panel for a game restart
	 */
	public void restartGame() {
		EnemyMovement.getInstance().resetNodes();
		score.restartScore();
		GamePanel.getInstance().placeElements();
		CollisionFinder.getInstance().resetDisguises();
		this.addKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "gamePanel");
		paused = false;

	}

	/**
	 * Stops the game and displays losing panel
	 */
	public void loseGame() {
		paused = true;
		this.removeKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "losePanel");
	}

	/**
	 * Stops the game and displays winning panel
	 */
	public void winGame() {
		paused = true;
		this.removeKeyListener(inpHandler);
		cardLayout.show(getContentPane(), "winPanel");
	}

	/**
	 * Begins the game thread
	 */
	public void startThread() {
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Tells the game panel to update and repaint
	 */
	public void run() {
		double interval = 1000000000 / fps;
		double nextStart = System.nanoTime() + interval;

		while (thread != null) {
			GamePanel.getInstance().update();

			GamePanel.getInstance().repaint();
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
	 * Add the given score to the score total
	 * 
	 * @param val value to be added
	 */
	public void addScore(int val) {
		score.addScore(val);
	}

	/**
	 * Subtract the given score from the score total
	 * 
	 * @param val value to be subtracted
	 */
	public void substractScore(int val) {
		score.substractScore(val);
	}

	/**
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

}
