import java.util.Random;

public class Difficulty {

	public enum Level { EASY, MEDIUM, HARD };

	public int min;
	public int max;
	public Level level;

	private Difficulty(int min,int max, Level level) {
		this.min = min;
		this.max = max;
		this.level = level;
	}

	public static Difficulty getEasyDifficulty() {
		return new Difficulty(0, 101, Level.EASY);
	}

	public static Difficulty getMediumDifficulty() {
		return new Difficulty(0, 501, Level.MEDIUM);
	}

	public static Difficulty getHardDifficulty() {
		return new Difficulty(-250, 501, Level.HARD);
	}


	public int getStartValue(Random rand) {
		switch (level) {
			case EASY:
				return rand.nextInt(10) + 1;
			case MEDIUM:
				return rand.nextInt(50) + 1;
			case HARD:
				return rand.nextInt(101) - 50;
			default:
				throw new RuntimeException("Invalid Difficulty");
		}
	}

}
