package pgdp.pingumath;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.function.LongPredicate;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;

import de.tum.in.test.api.jupiter.HiddenTest;
import de.tum.in.test.api.jupiter.PublicTest;

@W04H01

public class SATTest {

	@DisplayName("- | Public Test isPow")
	@PublicTest
	@Order(1)
	void testIsPowPublic() {
		int[][] in = { { 1, 2 }, { 1, 3 }, { 2, 4 }, { 3, 8 }, { 2, 8 } };
		Boolean[] out = Arrays.stream(in).map(i -> {
			return SAT.isPow(i[0], i[1]);
		}).toArray(Boolean[]::new);
		Boolean[] expected = { true, true, true, true, false };

		if (!Arrays.equals(out, expected))
			fail("Some basic mistake was made. More details in the hidden cases.");
	}

	@DisplayName("- | Hidden Test isPow - Basic")
	@HiddenTest
	@Order(2)
	void testIsPowHidden() {
		int[][] in = { { 1, 2 }, { 1, 3 }, { 2, 4 }, { 3, 8 }, { 2, 8 } };
		Boolean[] out = Arrays.stream(in).map(i -> {
			return SAT.isPow(i[0], i[1]);
		}).toArray(Boolean[]::new);
		Boolean[] expected = { true, true, true, true, false };

		if (!Arrays.equals(out, expected)) {
			String errorMSG = IntStream.range(0, in.length).filter(i -> {
				return out[i] != expected[i];
			}).mapToObj(i -> {
				return "in: <" + in[i][0] + ", " + in[i][1] + "> out: <" + out[i] + "> expected: <" + expected[i]
						+ ">\n";
			}).reduce((acc, i) -> {
				return acc + i;
			}).orElse("The test itself failed!");
			fail("The following cases failed:\n" + errorMSG);
		}
	}

	@DisplayName("- | Hidden Test isPow - Edge Cases")
	@HiddenTest
	@Order(3)
	void testIsPowHiddenEC() {
		// i
		if (SAT.isPow(-1, 2))
			fail("i<0 not returning <false>");
		if (!SAT.isPow(0, 1))
			fail("i=0, n=1 not returning <true>");
		if (SAT.isPow(0, 2))
			fail("i=0, n!=1 not returning <false>");
		if (!SAT.isPow(1, 1000000000))
			fail("i=1, n=1000000000 not returning <true>");

		// n
		if (!SAT.isPow(10, 1))
			fail("n=1 not returning <true>");
		if (SAT.isPow(0, 0) || SAT.isPow(1, -1))
			fail("n<=0 not returning <false>");
	}

	@DisplayName("- | Public Test isCentralBin")
	@PublicTest
	@Order(4)
	void testIsCentralBinPublic() {
		long[] in = { 1, 2, 6, 20, 923, 925 };
		Boolean[] out = Arrays.stream(in).mapToObj(i -> {
			return SAT.isCentralBin(i);
		}).toArray(Boolean[]::new);
		Boolean[] expected = { true, true, true, true, false, false };

		if (!Arrays.equals(out, expected))
			fail("Some basic mistake was made. More details in the hidden cases.");
	}

	@DisplayName("- | Hidden Test isCentralBin")
	@HiddenTest
	@Order(5)
	void testIsCentralBinHidden() {
		// public
		long[] in = { 1, 2, 6, 20, 923, 925 };
		Boolean[] out = Arrays.stream(in).mapToObj(i -> {
			return SAT.isCentralBin(i);
		}).toArray(Boolean[]::new);
		Boolean[] expected = { true, true, true, true, false, false };

		if (!Arrays.equals(out, expected)) {
			String errorMSG = IntStream.range(0, in.length).filter(i -> {
				return out[i] != expected[i];
			}).mapToObj(i -> {
				return "in: <" + in[i] + "> out: <" + out[i] + "> expected: <" + expected[i] + ">\n";
			}).reduce((acc, i) -> {
				return acc + i;
			}).orElse("The test itself failed!");
			fail("The following cases failed:\n" + errorMSG);
		}

		for (int i = 20; i <= 29; i++) {
			long binom = binom(2 * i, i);
			if (!SAT.isCentralBin(binom))
				fail("in: <" + binom + "> out: <false> expected: <true>\n");
			if (SAT.isCentralBin(binom - 1))
				fail("in: <" + (binom - 1) + "> out: <true> expected: <false>\n");
			if (SAT.isCentralBin(binom + 1))
				fail("in: <" + (binom + 1) + "> out: <true> expected: <false>\n");
		}
	}

	private static long binom(long n, long k) {
		long result = 1;
		for (long i = 0; i < k; i++)
			result = (result * (n - i)) / (i + 1);
		return result;
	}

	@DisplayName("- | Hidden Test isCentralBin - Edge Cases")
	@HiddenTest
	@Order(6)
	void testIsCentralBinHiddenEC() {
		if (SAT.isCentralBin(0) || SAT.isCentralBin(-1))
			fail("n<=0 not returning <false>");
		if (!SAT.isCentralBin(1))
			fail("n=1 not returning <true>");
	}

	@DisplayName("- | Public Test isJacobsthal")
	@PublicTest
	@Order(7)
	void testIsJacobPublic() {
		long[] in = { 3, 5, 171, 8, 13 };
		Boolean[] out = Arrays.stream(in).mapToObj(i -> {
			return SAT.isJacobsthal(i);
		}).toArray(Boolean[]::new);
		Boolean[] expected = { true, true, true, false, false };

		if (!Arrays.equals(out, expected))
			fail("Some basic mistake was made. More details in the hidden cases.");
	}

	@DisplayName("- | Hidden Test isJacobsthal - Basic")
	@HiddenTest
	@Order(8)
	void testIsJacobHidden() {
		long[] in = { 3, 5, 171, 8, 13 };
		Boolean[] out = Arrays.stream(in).mapToObj(i -> {
			return SAT.isJacobsthal(i);
		}).toArray(Boolean[]::new);
		Boolean[] expected = { true, true, true, false, false };

		if (!Arrays.equals(out, expected)) {
			String errorMSG = IntStream.range(0, in.length).filter(i -> {
				return out[i] != expected[i];
			}).mapToObj(i -> {
				return "in: <" + in[i] + "> out: <" + out[i] + "> expected: <" + expected[i] + ">\n";
			}).reduce((acc, i) -> {
				return acc + i;
			}).orElse("The test itself failed!");
			fail("The following cases failed:\n" + errorMSG);
		}

		lucasHelperTest(3, 5, 2, 1, n -> SAT.isJacobsthal(n));
	}

	@DisplayName("- | Hidden Test isJacobsthal - Edge Cases")
	@HiddenTest
	@Order(9)
	void testIsJacobHiddenEC() {
		if (SAT.isJacobsthal(-2) || SAT.isJacobsthal(-1))
			fail("n<0 not returning <false>");
		if (!SAT.isJacobsthal(0))
			fail("n=0 not returning <true>");
		if (!SAT.isJacobsthal(1))
			fail("n=1 not returning <true>");
	}

	@DisplayName("- | Public Test isLucasLikeSequence")
	@PublicTest
	@Order(10)
	void testIsLucasPublic() {
		int[][] in = { { 0, 1, 1, 1, 2 }, { 0, 1, 1, 1, 3 }, { 0, 1, 1, 1, 5 }, { 0, 1, 1, 1, 7 }, { 0, 1, 1, 1, 9 } };
		Boolean[] out = Arrays.stream(in).map(i -> {
			return SAT.isLucasLikeSequence(i[0], i[1], i[2], i[3], i[4]);
		}).toArray(Boolean[]::new);
		Boolean[] expected = { true, true, true, false, false };

		if (!Arrays.equals(out, expected))
			fail("Some basic mistake was made. More details in the hidden cases.");
	}

	@DisplayName("- | Hidden Test isLucasLikeSequence - Basic")
	@HiddenTest
	@Order(11)
	void testIsLucasHidden() {
		// public
		int[][] in = { { 0, 1, 1, 1, 2 }, { 0, 1, 1, 1, 3 }, { 0, 1, 1, 1, 5 }, { 0, 1, 1, 1, 7 }, { 0, 1, 1, 1, 9 } };
		Boolean[] out = Arrays.stream(in).map(i -> {
			return SAT.isLucasLikeSequence(i[0], i[1], i[2], i[3], i[4]);
		}).toArray(Boolean[]::new);
		Boolean[] expected = { true, true, true, false, false };

		if (!Arrays.equals(out, expected)) {
			String errorMSG = IntStream.range(0, in.length).filter(i -> {
				return out[i] != expected[i];
			}).mapToObj(i -> {
				return "in: <" + in[i][0] + ", " + in[i][1] + ", " + in[i][2] + ", " + in[i][3] + ", " + in[i][4]
						+ "> out: <" + out[i] + "> expected: <" + expected[i] + ">\n";
			}).reduce((acc, i) -> {
				return acc + i;
			}).orElse("The test itself failed!");
			fail("The following cases failed:\n" + errorMSG);
		}

		lucasHelperTest(3, 5, 1, 1, n -> SAT.isLucasLikeSequence(3, 5, 1, 1, n));
	}

	@DisplayName("- | Hidden Test isLucasLikeSequence - Edge Cases")
	@HiddenTest
	@Order(12)
	void testIsLucasHiddenEC() {
		if (SAT.isLucasLikeSequence(0, 0, 0, 0, -1))
			fail("n<0 not returning <false>");

		if (!SAT.isLucasLikeSequence(3, 4, 1, 1, 3))
			fail("n==x0 not returning <true>");
		if (!SAT.isLucasLikeSequence(3, 4, 1, 1, 4))
			fail("n==x1 not returning <true>");

		if (SAT.isLucasLikeSequence(3, 3, 0, 1, 5))
			fail("Constant sequence failed");

		// Jacobsthal - General Parameter
		int[][] inJ = { { 0, 1, 2, 1, 3 }, { 0, 1, 2, 1, 5 }, { 0, 1, 2, 1, 171 }, { 0, 1, 2, 1, 8 },
				{ 0, 1, 2, 1, 13 } };
		Boolean[] outJ = Arrays.stream(inJ).map(i -> {
			return SAT.isLucasLikeSequence(i[0], i[1], i[2], i[3], i[4]);
		}).toArray(Boolean[]::new);
		Boolean[] expectedJ = { true, true, true, false, false };

		if (!Arrays.equals(outJ, expectedJ)) {
			String errorMSG = IntStream.range(0, inJ.length).filter(i -> {
				return outJ[i] != expectedJ[i];
			}).mapToObj(i -> {
				return "in: <" + inJ[i][0] + ", " + inJ[i][1] + ", " + inJ[i][2] + ", " + inJ[i][3] + ", " + inJ[i][4]
						+ "> out: <" + outJ[i] + "> expected: <" + expectedJ[i] + ">\n";
			}).reduce((acc, i) -> {
				return acc + i;
			}).orElse("The test itself failed!");
			fail("General Parameterization - The following cases failed:\n" + errorMSG);
		}
		lucasHelperTest(3, 5, 2, 1, n -> SAT.isLucasLikeSequence(3, 5, 2, 1, n));
	}

	private void lucasHelperTest(long x0, long x1, int a, int b, LongPredicate studentImplementation) {
		long prev = x0, current = x1, next;
		do {
			if (!studentImplementation.test(current))
				fail("in: <" + current + "> out: <false> expected: <true>\n");
			if (studentImplementation.test(current - 1))
				fail("in: <" + (current - 1) + "> out: <true> expected: <false>\n");
			if (studentImplementation.test(current + 1))
				fail("in: <" + (current + 1) + "> out: <true> expected: <false>\n");
			next = a * prev + b * current;
			if (prev == current && current == next)
				break;
			prev = current;
			current = next;
		} while (current < 1000000000000000L);
	}
}
