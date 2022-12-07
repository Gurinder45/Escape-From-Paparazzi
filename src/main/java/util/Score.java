package util;

/**
 * handles the score of the game
 * 
 * @author julio patrick Asifiwe
 *
 */
public class Score {
	private static Score instance = null;
	private int score;

	private Score() {
		instance = this;
		this.score = 0;
	}

	public static Score getInstance() {
		if (instance == null) {
			new Score();
		}
		return instance;
	}

	/**
	 * Resets the score to zero
	 */
	public void restartScore() {
		score = 0;
	}

	/**
	 * Adds given amount to score
	 * 
	 * @param val integer to be added
	 */
	public void addScore(int val) {
		score += val;
	}

	/**
	 * subtracts given value from score
	 * 
	 * @param val integer to be subtracted
	 */
	public void substractScore(int val) {
		score -= val;
	}

	/**
	 * Get the score
	 * 
	 * @return integer representing score
	 */
	public int getScore() {
		return score;
	}
}
