import java.util.Random;

public class HalfDoubleOperator extends Operator {

	private boolean half;
	
	public HalfDoubleOperator(int maxValue, int minValue, Random rand) {
		super(maxValue, minValue, rand);
		shuffle();
	}

	public Operator cloneThis() {
		HalfDoubleOperator n = new HalfDoubleOperator(this.maxValue, this.minValue, this.rand);
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
			return in * 2 < maxValue;
		}
	}

	public int getOutput() {
		return half ? prev.getOutput() / 2 : prev.getOutput() * 2;
	}

	public String toString() {
		return half ? "halbieren" : "verdoppeln";
	}

}
