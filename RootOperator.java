import java.util.Random;

public class RootOperator extends Operator {

	public RootOperator(int maxValue, int minValue, Random rand) {
		super(maxValue, minValue, rand);
		//shuffle();
	}

	public Operator cloneThis() {
		return new RootOperator(this.maxValue, this.minValue, this.rand);
	}

	public void shuffle() {
		// nothing to do
	}

	public boolean worksForInput(int in) {
		// test if Square Root is an Integer
		int sqrt = (int) Math.sqrt(in);
		return sqrt * sqrt == in;
	}

	public int getOutput() {
		return (int) Math.sqrt(prev.getOutput());
	}

	public String toString() {
		return "Wurzel ziehen";
	}

}
