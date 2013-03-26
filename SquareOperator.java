import java.util.Random;

public class SquareOperator extends Operator {

	public SquareOperator(Difficulty diff, Random rand) {
		super(diff, rand);
		shuffle();
	}

	public Operator cloneThis() {
		return new SquareOperator(this.diff, this.rand);
	}

	public void shuffle() {
		// nothing to do
	}

	public boolean worksForInput(int in) {
		return in * in < diff.max;
	}

	public int getOutput() {
		return prev.getOutput() * prev.getOutput();
	}

	public String toString() {
		return "mit sich selbst multiplizieren";
	}

}
