import java.util.Random;

public class AddSubOperator extends Operator {

	private int value;
	
	public AddSubOperator(Difficulty diff, Random rand) {
		super(diff, rand);
		shuffle();
	}

	public Operator cloneThis() {
		AddSubOperator n = new AddSubOperator(this.diff, this.rand);
		n.value = this.value;
		return n;
	}

	public void shuffle() {
		value = rand.nextInt((int)(diff.max / 3)) + 1;
		if (rand.nextBoolean()) {
			value = -value;
		}
	}

	public boolean worksForInput(int in) {
		return in + value < diff.max && in + value > diff.min;
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
