import java.util.*;

public class Main {

	private static boolean uniqueStep(Operator op, Operator prev, HashSet<Integer> previous) {
		op.setPredecessor(prev);
		int result = op.getOutput();
		op.setPredecessor(null);

		return previous.add(result);
	}


	public static void main(String[] args) {

		Random rand = new Random();
		final int max = 101;
		final int min = 0;

		ArrayList<Operator> operators = new ArrayList<Operator>(10);
		operators.add(new AddSubOperator(max, min, rand));
		operators.add(new MultiplicationOperator(max, min, rand));
		operators.add(new HalfDoubleOperator(max, min, rand));
		operators.add(new FractionOperator(max, min, rand));
		operators.add(new SquareOperator(max, min, rand));
		operators.add(new PercentageOperator(max, min, rand));

		ArrayList<Operator> specialOperators = new ArrayList<Operator>(5);
		specialOperators.add(new RootOperator(max, min, rand));


		ArrayList<Operator> challenge = new ArrayList<Operator>(11);

		int input = rand.nextInt(10) + 1;
		HashSet<Integer> intermediates = new HashSet<Integer>(10);
		intermediates.add(input);
		StartOperator start = new StartOperator(max, min, rand);
		start.setValue(input);
		challenge.add(start);

		Operator prev = start;

		for (int i = 0; i < 10; i++) {
			Operator o = null;
			int failCount = 0;

			// Try special operators
			for (Operator s : specialOperators) {
				if (s.worksForInput(input)) {
					if (uniqueStep(s, prev, intermediates)) {
						o = s;
						specialOperators.remove(s);
						operators.add(s);
						break;
					}
				}
				s.shuffle();
			}

			if (o == null) { 
				do {

					// Try normal operators randomly
					int oi = rand.nextInt(operators.size());
					o = operators.get(oi);

					if (o.worksForInput(input)) {

						// Damn you, random generator
						if (failCount > 100000)
							break;
						
						// Normal, "good" case
						if (uniqueStep(o, prev, intermediates) && o.lastUsed != (i - 1))
							break;
					}

					failCount++;

					o.shuffle();

				} while (true);
			}

			o.lastUsed = i;
			Operator usedOp = o.cloneThis();
			usedOp.setPredecessor(prev);
			challenge.add(usedOp);

			prev = usedOp;
			input = usedOp.getOutput();

			for (Operator op : operators) {
				op.shuffle();
			}
		}

		for (int i = 0; i < challenge.size(); i++) {
			System.out.println(challenge.get(i).toStringWithSolution());
		}
	}
}
