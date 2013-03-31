import java.util.Random;

public class PercentageOperator extends Operator {

	private int factor;
	private boolean add;
	private boolean plain;
	
	public PercentageOperator(Difficulty diff, Random rand) {
		super(diff, rand);
		shuffle();
	}

	public Operator cloneThis() {
		PercentageOperator n = new PercentageOperator(this.diff, this.rand);
		n.factor = this.factor;
		n.add = this.add;
		return n;
	}

	public void shuffle() {
		plain = diff.level == Difficulty.Level.HARD ? false : true;
		add = rand.nextBoolean();

		// The factor needs to be cheated to work
		factor = 0;
	}

	public boolean worksForInput(int in) {
		// find a fraction;
		factor = 0;
		int[] factors = {2, 4, 5, 10, 20};
		for (int i : factors) {
			if (in % i == 0)
				factor = i;
				if (rand.nextInt(100) < 50)
					break;
		}

		// fail?
		if (factor == 0) {
			return false;
		}


		return true;
	}

	public int getOutput() {
		int in = prev.getOutput();

		if (plain) {
			return in / factor;
		}

		if (add) {
			return in + (in / factor);
		} else {
			return in - (in / factor);
		}
	}

	public String toString() {
		if (plain) 
			return "" + (100/factor) + "% davon";

		return "" + (100/factor) + (add ? "% mehr" : "% weniger");
	}

	public String toTexString() {
		return toString().replaceAll("%", "\\\\%");
	}
}
