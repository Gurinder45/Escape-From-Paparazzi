package util;

/**
 * handles the score of the game
 * @author julio patrick Asifiwe
 *
 */
public class Score {
	private int score;

	public Score() {
		this.score = 0;
	}

	/**
	 * after you fail and restart the score is zerod
	 */
	public void restartScore() {
		score = 0;
	}

	/**
	 * adds val amount to the score to be used  after you pick any kind of reward
	 * @param val to be added on score total
	 */
	public void addScore(int val) {
		score += val;
	}

	/**
	 * sabstracts val amount from the score
	 * @param val to be added on score total
	 */
	public void substractScore(int val) {
		score -= val;
	}

	/**
	 * @return total score
	 */
	public int getScore() {
		return score;
	}
}
