import java.util.*; import java.nio.file.*;
import java.nio.charset.*;

public class ChallengeSheet {

	public static void makeSheet() {

		Difficulty[] ds = {
			Difficulty.getEasyDifficulty(), 
			Difficulty.getMediumDifficulty(), 
			Difficulty.getHardDifficulty()
		};

		Challenge[] cs = new Challenge[3];

		{
			int found = 0;
			while (found != 3) {
				Challenge c = new Challenge(ds[found]);
				ArrayList<Operator> operators = c.getChallenge();
				boolean fail = false;

				// Throw away bad Challenges that use the same operator twice in a row
				for (int i = 2; i < operators.size(); i++) {
					if (operators.get(i).getClass().equals(operators.get(i-1).getClass())) {
						fail = true;
					}
				}

				if (!fail) {
					cs[found++] = c;
				}
			}
		}

		int[] solutions = { cs[0].getSolution(), cs[1].getSolution(), cs[2].getSolution() };

		// Open Files

		List<String> head = null;
		List<String> tail = null;
		List<String> chHead = null;
		List<String> chTail = null;

		StringBuilder sb = new StringBuilder();

		try {
			Path headP = FileSystems.getDefault().getPath("tex", "head.tex");
			Path tailP = FileSystems.getDefault().getPath("tex", "tail.tex");
			Path chHeadP = FileSystems.getDefault().getPath("tex", "challengeBefore.tex");
			Path chTailP = FileSystems.getDefault().getPath("tex", "challengeAfter.tex");

			Charset charset = Charset.defaultCharset();
			head = Files.readAllLines(headP, charset);
			tail = Files.readAllLines(tailP, charset);
			chHead = Files.readAllLines(chHeadP, charset);
			chTail = Files.readAllLines(chTailP, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Build Document

		for (String s : head) {
			sb.append(s);
			sb.append("\n");
		}

		for (Challenge c : cs) {
			
			for (String s : chHead) {
				sb.append(s);
				sb.append("\n");
			}

			for (Operator o : c.getChallenge()) {
				sb.append(o.toTexString());
				sb.append(" & ");
			}
			sb.append("\\textcolor{white}{?} \\\\");

			for (String s : chTail) {
				sb.append(s);
				sb.append("\n");
			}
		}

		sb.append("\\begin{turn}{180}");
		sb.append("Lösungen: ");
		sb.append(solutions[0]);
		sb.append(", ");
		sb.append(solutions[1]);
		sb.append(", ");
		sb.append(solutions[2]);
		sb.append("\n\\end{turn}");


		for (String s : tail) {
			sb.append(s);
			sb.append("\n");
		}

		// Run pdflatex
		final Path tempDir = Files.createTempDirectory("30seconds");
		File log = new File("/dev/null");
		ProcessBuilder pb = new ProcessBuilder("pdflatex", "-jobname", "30seconds");
		pb.directory(tempDir.toFile());
		pb.redirectErrorStream(true);
		pb.redirectOutputStream(Redirect.appendTo(log));

		try {
			Process p = pb.start();
			OutputStream out = p.getOutputStream();
			out.write(sb.toString().getBytes());
			out.flush(); out.close();
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
