import java.util.Random;

public abstract class Operator {

	public int lastUsed = 42;

	protected Difficulty diff;
	protected Random rand;
	protected Operator prev;

	public Operator(Difficulty diff, Random rand) {
		this.diff = diff;
		this.rand = rand;
	}

	public abstract Operator cloneThis();
	public abstract boolean worksForInput(int in);
	public abstract void shuffle();

	public void setPredecessor(Operator prev) {
		this.prev = prev;
	}

	public abstract int getOutput();
	public abstract String toString();
	public String toStringWithSolution() {
		return toString() + ": " + getOutput();
	}
}
