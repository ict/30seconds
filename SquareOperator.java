import java.util.Random;

public class SquareOperator extends Operator {

	public SquareOperator(int maxValue, int minValue, Random rand) {
		super(maxValue, minValue, rand);
		shuffle();
	}

	public Operator cloneThis() {
		return new SquareOperator(this.maxValue, this.minValue, this.rand);
	}

	public void shuffle() {
		// nothing to do
	}

	public boolean worksForInput(int in) {
		return in * in < maxValue;
	}

	public int getOutput() {
		return prev.getOutput() * prev.getOutput();
	}

	public String toString() {
		return "mit sich selbst multiplizieren";
	}

}
