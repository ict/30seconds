import java.util.Random;

public class RootOperator extends Operator {

	int which;

	public RootOperator(Difficulty diff, Random rand) {
		super(diff, rand);
		shuffle();
	}

	public Operator cloneThis() {
		RootOperator op = new RootOperator(this.diff, this.rand);
		op.which = which;
		return op;
	}

	public void shuffle() {
		if (diff.level == Difficulty.Level.HARD) {
			which = rand.nextInt(2) + 2;
		}
	}

	public boolean worksForInput(int in) {
		boolean second = false;
		boolean third = false;

		// test if Square Root is an Integer
		int sqrt = (int) Math.sqrt(in);
		second = sqrt * sqrt == in;
		if (second)
			which = 2;

		int curt = (int) Math.pow(in, 1d/3d);
		third = curt * curt * curt == in;
		if (third)
			which = 3;

		return second || third;
	}

	public int getOutput() {
		return (int) Math.pow(prev.getOutput(), 1d/which);
	}

	public String toString() {
		return (which == 3 ? "3. " : "") + "Wurzel ziehen";
	}

}
