import java.util.Random;

public class MultiplicationOperator extends Operator {

	private int value;
	
	public MultiplicationOperator(int maxValue, int minValue, Random rand) {
		super(maxValue, minValue, rand);
		shuffle();
	}

	public Operator cloneThis() {
		MultiplicationOperator n = new MultiplicationOperator(this.maxValue, this.minValue, this.rand);
		n.value = this.value;
		return n;
	}

	public void shuffle() {
		value = rand.nextInt((int)(maxValue / 10)) + 2;
	}

	public boolean worksForInput(int in) {
		return in * value < maxValue && in * value > minValue;
	}

	public int getOutput() {
		return prev.getOutput() * value;
	}

	public String toString() {
		return "mal " + value;
	}

}
