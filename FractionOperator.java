import java.util.Random;

public class FractionOperator extends Operator {

	private int numerator;
	private int denominator;
	
	public FractionOperator(Difficulty diff, Random rand) {
		super(diff, rand);
		shuffle();
	}

	public Operator cloneThis() {
		FractionOperator n = new FractionOperator(this.diff, this.rand);
		n.numerator = this.numerator;
		n.denominator = this.denominator;
		return n;
	}

	public void shuffle() {
		// We need to cheat for this operator to be useful, so no real shuffling here
		numerator = 0;
		denominator = 0;
	}

	public boolean worksForInput(int in) {
		// find a denominator
		denominator = 0;
		for (int i = 12; i > 2; --i) {
			if (in % i == 0)
				denominator = i;
				if (rand.nextInt(100) < 60)
					break;
		}

		// fail?
		if (denominator == 0) {
			return false;
		}

		// use a numerator?
		if (rand.nextBoolean()) {
			numerator = rand.nextInt(denominator - 1) + 2;
		} else {
			numerator = 1;
		}

		return true;
	}

	public int getOutput() {
		return prev.getOutput() * numerator / denominator;
	}

	public String toString() {
		if (numerator == 1) {
			if (denominator == 2) 
				return "halbieren";
			return "durch " + denominator;

		} else {
			return "" + numerator + " / " + denominator + " davon";
		}
	}

	public String toTexString() {
		if (numerator == 1) {
			if (denominator == 2) 
				return "halbieren";
			return "durch " + denominator;

		} else {
			return "$\\frac{" + numerator + "}{" + denominator + "}$ "+ " davon";
		}
	}
}
