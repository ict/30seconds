import java.util.*;

public class Main {

	public static void main(String[] args) {

		Random rand = new Random();
		final int max = 250;
		final int min = 0;

		ArrayList<Operator> operators = new ArrayList<Operator>(10);
		operators.add(new AddSubOperator(max, min, rand));


		ArrayList<Operator> challenge = new ArrayList<Operator>(11);

		int input = 15;
		StartOperator start = new StartOperator(max, min, rand);
		start.setValue(input);
		challenge.add(start);

		Operator prev = start;

		for (int i = 0; i < 10; i++) {
			Operator o;

			do {
				int oi = rand.nextInt(operators.size());
				o = operators.get(oi);

				if (o.worksForInput(input)) {
					break;
				}

				o.shuffle();

			} while (true);

			Operator usedOp = o.cloneThis();
			usedOp.setPredecessor(prev);
			challenge.add(usedOp);

			prev = usedOp;
			input = usedOp.getOutput();

			for (int j = 0; j < operators.size(); j++) {
				operators.get(j).shuffle();
			}
		}

		for (int i = 0; i < challenge.size(); i++) {
			System.out.println(challenge.get(i).toStringWithSolution());
		}
	}
}
