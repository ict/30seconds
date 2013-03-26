import java.util.Random;

public class HalfDoubleOperator extends Operator {

	private boolean half;
	
	public HalfDoubleOperator(Difficulty diff, Random rand) {
		super(diff, rand);
		shuffle();
	}

	public Operator cloneThis() {
		HalfDoubleOperator n = new HalfDoubleOperator(this.diff, this.rand);
		n.half = this.half;
		return n;
	}

	public void shuffle() {
		half = rand.nextBoolean();
	}

	public boolean worksForInput(int in) {
		if (half) {
			return (in > 1) && (in % 2 == 0);
		} else {
			return in * 2 < diff.max;
		}
	}

	public int getOutput() {
		return half ? prev.getOutput() / 2 : prev.getOutput() * 2;
	}

	public String toString() {
		return half ? "halbieren" : "verdoppeln";
	}

}
