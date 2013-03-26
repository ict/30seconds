import java.util.Random;

public class MultiplicationOperator extends Operator {

	private int value;
	
	public MultiplicationOperator(Difficulty diff, Random rand) {
		super(diff, rand);
		shuffle();
	}

	public Operator cloneThis() {
		MultiplicationOperator n = new MultiplicationOperator(this.diff, this.rand);
		n.value = this.value;
		return n;
	}

	public void shuffle() {
		value = rand.nextInt((int)(diff.max / 10)) + 2;
	}

	public boolean worksForInput(int in) {
		return in * value < diff.max && in * value > diff.min;
	}

	public int getOutput() {
		return prev.getOutput() * value;
	}

	public String toString() {
		return "mal " + value;
	}

}
