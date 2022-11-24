package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreTest {
	private Score score;
	@BeforeEach
	void setUp() {
		score=new Score();
	}

	@Test
	void testGetScore(){
		assertEquals(0,score.getScore());
	}
	@Test
	void testaddandsubstractscore(){
		score.addScore(5);
		assertEquals(5,score.getScore());
		score.substractScore(3);
		assertEquals(2,score.getScore());

	}
	@Test
	void testrestartscore(){
		score.addScore(5);
		score.restartScore();
		assertEquals(0,score.getScore());
	}



}
