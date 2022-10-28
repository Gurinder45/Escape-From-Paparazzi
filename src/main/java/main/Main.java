package main;

import javax.swing.JFrame;

import ui.GamePanel;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Papparazi Escape!");
		frame.setVisible(true);
		
		GamePanel gamePanel =  new GamePanel();
		frame.add(gamePanel);
		frame.pack();
		gamePanel.startThread();
	}

}
