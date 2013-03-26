import java.util.Random;

public class StartOperator extends Operator {

	private int value;
	
	public StartOperator(Difficulty diff, Random rand) {
		super(diff, rand);
		shuffle();
	}

	public Operator cloneThis() {
		StartOperator n = new StartOperator(this.diff, this.rand);
		n.value = this.value;
		return n;
	}

	public void shuffle() {
	}

	public boolean worksForInput(int in) {
		throw new UnsupportedOperationException("Startoperator worksForInput!");
	}

	public int getOutput() {
		return value;
	}

	public void setValue(int val) {
		this.value = val;
	}

	public void setPredecessor(Operator prev) {
		throw new UnsupportedOperationException("Startoperator cannot have predecessor!");
	}

	public String toString() {
		return "" + value;
	}

	public String toStringWithSolution() {
		return "" + value;
	}
}

