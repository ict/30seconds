import java.util.*;

public class Challenge {

	private ArrayList<Operator> challenge;

	private static boolean uniqueStep(Operator op, Operator prev, HashSet<Integer> previous) {
		op.setPredecessor(prev);
		int result = op.getOutput();
		op.setPredecessor(null);

		return previous.add(result);
	}


	public Challenge(Difficulty diff) {

		Random rand = new Random();
		final int max = diff.max;
		final int min = diff.min;

		ArrayList<Operator> operators = new ArrayList<Operator>(10);
		operators.add(new AddSubOperator(diff, rand));
		operators.add(new MultiplicationOperator(diff, rand));
		operators.add(new HalfDoubleOperator(diff, rand));
		operators.add(new FractionOperator(diff, rand));
		operators.add(new SquareOperator(diff, rand));
		operators.add(new PercentageOperator(diff, rand));

		ArrayList<Operator> specialOperators = new ArrayList<Operator>(5);
		if (diff.level != Difficulty.Level.EASY) {
			specialOperators.add(new RootOperator(diff, rand));
		}


		challenge = new ArrayList<Operator>(11);

		int input = diff.getStartValue(rand);

		HashSet<Integer> intermediates = new HashSet<Integer>(10);
		intermediates.add(input);

		StartOperator start = new StartOperator(diff, rand);
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

		// Just for debugging
		for (int i = 0; i < challenge.size(); i++) {
			System.out.println(challenge.get(i).toStringWithSolution());
		}
	}

	public ArrayList<Operator> getChallenge() {
		return challenge;
	}
}
