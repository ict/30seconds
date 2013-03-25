import java.util.Random;

public class PercentageOperator extends Operator {

	private int factor;
	private boolean add;
	
	public PercentageOperator(int maxValue, int minValue, Random rand) {
		super(maxValue, minValue, rand);
		shuffle();
	}

	public Operator cloneThis() {
		PercentageOperator n = new PercentageOperator(this.maxValue, this.minValue, this.rand);
		n.factor = this.factor;
		n.add = this.add;
		return n;
	}

	public void shuffle() {
		// We need to cheat for this operator to be useful, so no real shuffling here
		add = true;
		factor = 0;
	}

	public boolean worksForInput(int in) {
		// find a fraction;
		factor = 0;
		int[] factors = {2, 4, 5};
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

		add = rand.nextBoolean();

		return true;
	}

	public int getOutput() {
		int in = prev.getOutput();

		if (add) {
			return in + (in / factor);
		} else {
			return in - (in / factor);
		}
	}

	public String toString() {
		return "" + (100/factor) + (add ? "% mehr" : "% weniger");
	}
}
