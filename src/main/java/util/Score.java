package util;

public class Score {
	private int score;
	
	public Score() {
		this.score = 0;
	}

	public void addScore(int val){
		score += val;
	}
	
	public void substractScore(int val){
		score -= val;
	}
	
	public int getScore() {
		return score;
	}
}
