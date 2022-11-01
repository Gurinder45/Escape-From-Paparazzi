package ui;

import javax.swing.JFrame;

import game.CollisionFinder;
import util.InputHandler;
import util.Score;

//for texts
// import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.awt.Color;
import javax.swing.JLabel;

public class GameFrame extends JFrame implements Runnable {
	private Thread thread;
	private InputHandler inpHandler;
	private GamePanel gamePanel;
	private CollisionFinder collisionFinder;
	private Score score;
	final public int cellSize = 32;
	final public int columnNum = 36;
	final public int rowNum = 24;
	final public int screenHeight = 900; //cellSize * (rowNum + 1);
	final public int screenWidth = 1500; //cellSize * columnNum;

		//texts
		JPanel fameTxt;
		Container con;

	int fps = 60;

	public GameFrame() {



		inpHandler = new InputHandler();
		score = new Score();
		gamePanel = new GamePanel(inpHandler, this);
		collisionFinder = new CollisionFinder(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screenWidth, screenHeight); // 46 columns 27 rows
		this.setTitle("Papparazi Escape!");
		this.addKeyListener(inpHandler);
		this.add(gamePanel);
		this.setVisible(true);
		startThread();

		//for texts
		con = this.getContentPane();
		fameTxt = new JPanel();
		fameTxt.setBounds(600, 700, 100, 200);
		fameTxt.setBackground(Color.black);
		con.add(fameTxt); //??

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
