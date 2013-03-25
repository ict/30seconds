import java.util.Random;

public class AddSubOperator extends Operator {

	private int value;
	
	public AddSubOperator(int maxValue, int minValue, Random rand) {
		super(maxValue, minValue, rand);
		shuffle();
	}

	public Operator cloneThis() {
		AddSubOperator n = new AddSubOperator(this.maxValue, this.minValue, this.rand);
		n.value = this.value;
		return n;
	}

	public void shuffle() {
		value = rand.nextInt((int)(maxValue / 3)) + 1;
		if (rand.nextBoolean()) {
			value = -value;
		}
	}

	public boolean worksForInput(int in) {
		return in + value < maxValue && in + value > minValue;
	}

	public int getOutput() {
		return prev.getOutput() + value;
	}

	public String toString() {
		if (value < 0) {
			return "" + (-value) + " abziehen";
		} else {
			return "" + value + " dazu";
		}
	}

}
