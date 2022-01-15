package pgdp.pingumath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class SAT {

	public static double binomResult = 1;
	public static long lucasSeq = -1;

	/*
	 * 'efficient' power implementation
	 * 
	 * This is part of the template, do not change this method!
	 */
	private static long pow(long a, int b) {
		if (b < 0) {
			System.out.println("Unexpected input: negative b is not allowed!");
			return 0;
		} else if (b == 0)
			return 1;

		long result = 1;
		while (b > 0) {
			if ((b & 1) == 0) {
				a *= a;
				b >>>= 1;
			} else {
				result *= a;
				b--;
			}
		}
		return result;
	}

	/*
	 * Hilfsmethode pow mit long Paramatern
	 */
	private static long pow1(long a, long b) {
		if (b < 0) {
			System.out.println("Unexpected input: negative b is not allowed!");
			return 0;
		} else if (b == 0)
			return 1;

		long result = 1;
		while (b > 0) {
			if ((b & 1) == 0) {
				a *= a;
				b >>>= 1;
			} else {
				result *= a;
				b--;
			}
		}
		return result;
	}

	/*
	 * check if there exists x s.t. x^i == n (naive implementation)
	 */
	public static boolean isPow(int i, long n) {

		boolean isPower = false;
		int j = 0;

		// Fix
		if (i == 0 && n == 1) {
			return true;
		}

		if (i > 0) {

			while (isPower == false && pow(j, i) <= n) {
				if (pow(j, i) == n) {
					isPower = true;
					break;
				}
				j++;

			}

		} else {
			isPower = false;
		}

		return isPower;
	}

	/*
	 * calculate binomial coefficient
	 */

	private static long binom(long n, long k) {

		// Sonderfälle
		if (k == 0 || k == n) {
			return 1;
		} else {
			while (k >= 1) {
				// global variable damit es auch in isCentralBin verwendet werden kann
				binomResult *= ((n - (k - 1)) / (double) k);
				k--;
			}

			// Umwandlung des Ergebnisses in long
			long resultLong = (long) binomResult;

			// Korrektur der Rundungsfehlern
			if (binomResult >= (resultLong + 0.5)) {
				resultLong++;
			}

			return resultLong;
		}
	}

	/*
	 * check if n is central binomial coefficient
	 */

	public static boolean isCentralBin(long n) {
		boolean result = false;

		if (n <= 0) {
			return false;
		} else {
			// Array List um die Zentralen Binomialkoeffizienten zu speichern
			ArrayList<Long> centralBin = new ArrayList<Long>();

			// Berechnung der zentralen Binomialkoeffizienten bis sie größer als n sind
			for (int i = 0; i < 32; i++) {
				centralBin.add(binom(2 * i, i));
				if (centralBin.get(i) > n)
					break;
				binomResult = 1;
			}

			// Korriegieren des Rundungsfehlers (nicht oprimal, aber funktioniert für Zahlen
			// kleiner gleich 10^17)
			if (centralBin.size() > 30) {
				centralBin.set(30, (centralBin.get(30) - 17));
				centralBin.set(29, (centralBin.get(29) - 1));
				centralBin.set(28, (centralBin.get(28) - 3));
			} else if (centralBin.size() > 29) {
				centralBin.set(29, (centralBin.get(29) - 1));
				centralBin.set(28, (centralBin.get(28) - 3));
			} else if (centralBin.size() > 28) {
				centralBin.set(28, (centralBin.get(28) - 3));
			}

			// Durchsuchen der Array list für n
			for (int i = 0; i < centralBin.size(); i++) {
				if (centralBin.get(i) == n) {
					result = true;
				}
			}
			return result;
		}
	}

	/*
	 * Hilfsmethode, die den jacobsthal rechnet.
	 */
	private static long jacobsthal(long x) {
		long jacobsthal = -1;

		if (x == 0) {
			jacobsthal = 0;
		} else if (x == 1) {
			jacobsthal = 1;
		} else if (x > 1) {
			// Formel aus Wikipedia
			jacobsthal = (pow1(2, x) - (pow1(-1, x))) / 3;
		}
		return jacobsthal;
	}

	/*
	 * check if n is part of Jacobsthal sequence
	 */
	public static boolean isJacobsthal(long n) {

		boolean result = false;
		long i = 0;

		// Jacobsthal Sequenz erstellen solange die Elemente kleiner gleich n sind und
		// gucken ob n gleich eins der Elementen ist
		while (result == false && jacobsthal(i) <= n) {
			if (n == jacobsthal(i) && n >= 0) {
				result = true;
				break;
			}
			i++;
		}

		return result;
	}

	/*
	 * given parameters for Lucas Sequence, check if n is part of this sequence
	 */
	public static boolean isLucasLikeSequence(long x0, long x1, int a, int b, long n) {

		boolean result = false;

		// Überprüft ob alle Parameter in N0 liegen
		if (x0 >= 0 && x1 >= 0 && a >= 0 && b >= 0 && n >= 0) {

			// Falls x0 und x1 gleich 0 sind dann sind alle Elemente der
			// LucasLikeSequencelike 0
			if (x0 == 0 && x1 == 0 && n != 0) {
				return false;
			} else if (x0 == 0 && x1 == 0 && n == 0) {
				return true;
			}

			// Array list um die Elemente der gegebenen Sequenz zu speichern
			ArrayList<Long> l = new ArrayList<Long>();

			// Erste 2 Elemente sind x0 und x1
			l.add(x0);
			l.add(x1);

			int i = 2;

			// Weitere Elemente bis die Elemente größer als n werden (+ ein paar um sicher
			// zu sein)
			while (true) {
				l.add((a * (l.get(i - 2)) + (b * l.get(i - 1))));

				if (l.get(i - 1) > n) {
					break;
				}

				i++;
			}

			// Durchsuchen der Array Liste für n
			for (int j = 0; j < l.size(); j++) {
				if (l.get(j) == n) {
					result = true;
				}
			}
		}
		return result;
	}

	/*
	 * this method returns a String of the analysis of the input n
	 * 
	 * This is part of the template, do not change this method!
	 */
	public static String checkSpecial(long n) {
		String result = "Input: \t\t" + n + "\n";

		String[] sequenceNames = { "Square", "Cubic", "Quartic", "Central Bin.", "Jacobsthal", "Fibonacci" };
		Boolean[] sequenceResults = { isPow(2, n), isPow(3, n), isPow(4, n), isCentralBin(n), isJacobsthal(n),
				isLucasLikeSequence(0, 1, 1, 1, n) };

		result += "Results: ";
		if (Arrays.stream(sequenceResults).anyMatch(x -> x)) {
			String analysis = IntStream.range(0, sequenceNames.length).mapToObj(i -> {
				return sequenceNames[i] + ": \t" + (sequenceNames[i].equals("Cubic") ? "\t" : "")
						+ (sequenceResults[i] ? "Yes" : "No") + "\n";
			}).reduce("", (a, b) -> a + b);
			result += "Interesting!\n" + analysis;
		} else
			result += "Nothing special about this ...\n";
		return result;
	}

	public static void main(String[] args) {
		System.out.println(isLucasLikeSequence(0, 1, 1, 1, 6765));
	}

// Tests

//	@Test
//	public void testIsPow() {
	// assertTrue(isPow(1, 1000000000));
//		assertFalse(isPow(0, 2));
//		assertTrue(isPow(9, 1000000000));
//		assertFalse(isPow(-1050, 760981));
//	}

//
//	@Test
//	public void testIsCentralBin() {
//		assertTrue(isCentralBin(1));
//		assertTrue(isCentralBin(6));
//		assertTrue(isCentralBin(924));
//		assertFalse(isCentralBin(10));
//		assertFalse(isCentralBin(0));
//		assertFalse(isCentralBin(pow(10, 17) - 1));
//		assertFalse(isCentralBin(-150));
//		assertTrue(isCentralBin(118264581564861424L));
//	}
//
//	@Test
//	public void testIsJacobsthal() {
//		assertTrue(isJacobsthal(0));
//		assertTrue(isJacobsthal(1));
//		assertTrue(isJacobsthal(21));
//		assertFalse(isJacobsthal(10));
//		assertFalse(isJacobsthal(pow(10, 15)));
//		assertFalse(isJacobsthal(-150));
//	}
//
	@Test
	public void testIsLucasLikeSequence() {
		long x0 = 0;
		long x1 = 1;
		int a = 1;
		int b = 1;
		long n = 1;
		assertTrue(isLucasLikeSequence(x0, x1, a, b, n));
//		n = 10;
//		assertFalse(isLucasLikeSequence(x0, x1, a, b, n));
//		x0 = 0;
//		x1 = 0;
//		a = 0;
//		b = -3;
//		assertFalse(isLucasLikeSequence(x0, x1, a, b, n));
//		x0 = 2;
//		x1 = 3;
//		a = 0;
//		b = 0;
//		n = 0;
//		assertFalse(isLucasLikeSequence(x0, x1, a, b, n));
		x0 = 3;
		x1 = 3;
		a = 0;
		b = 1;
		n = 5;
		assertFalse(isLucasLikeSequence(x0, x1, a, b, n));

	}
}
