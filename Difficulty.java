import java.util.Random;

public class Difficulty {

	public enum Level { EASY, MEDIUM, HARD };

	public int min;
	public int max;
	public Level level;

	private Difficulty(min, max, level) {
		this.min = min;
		this.max = max;
		this.level = level;
	}

	public static Difficulty getEasyDifficulty() {
		return new Difficulty(0, 101, EASY);
	}

	public static Difficulty getMediumDifficulty() {
		return new Difficulty(0, 501, MEDIUM);
	}

	public static Difficulty getHardDifficulty() {
		return new Difficulty(-250, 501, HARD);
	}


	public int getStartValue(Random rand) {
		switch (level) {
			case EASY:
				return rand.nextInt(10) + 1;
				break;
			case MEDIUM:
				return rand.nextInt(50) + 1;
				break;
			case HARD:
				return rand.nextInt(101) - 50;
				break;
		}
	}

}
