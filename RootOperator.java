import java.util.Random;

public class RootOperator extends Operator {

	public RootOperator(Difficulty diff, Random rand) {
		super(diff, rand);
		//shuffle();
	}

	public Operator cloneThis() {
		return new RootOperator(this.diff, this.rand);
	}

	public void shuffle() {
		// nothing to do
	}

	public boolean worksForInput(int in) {
		// test if Square Root is an Integer
		int sqrt = (int) Math.sqrt(in);
		return sqrt * sqrt == in && sqrt * sqrt < diff.max;
	}

	public int getOutput() {
		return (int) Math.sqrt(prev.getOutput());
	}

	public String toString() {
		return "Wurzel ziehen";
	}

}
