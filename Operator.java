import java.util.Random;

public abstract class Operator {

	int maxValue;
	int minValue;
	Random rand;
	Operator prev;

	public Operator(int maxValue, int minValue, Random rand) {
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.rand = rand;
	}

	public abstract Operator cloneThis();
	public abstract boolean worksForInput(int in);
	public abstract void shuffle();

	public abstract void setPredecessor(Operator prev);
	public abstract int getOutput();
	public abstract String toString();
	public abstract String toStringWithSolution();
}
