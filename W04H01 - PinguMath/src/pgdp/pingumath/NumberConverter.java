package pgdp.pingumath;

//import static org.junit.Assert.*;
//
//import org.junit.Test;

public class NumberConverter {

	// Hilfsmethode um eine Deizimalzahl ins Ternärsystem umzuwandeln.

	public static int intToTernary(int n) {
		int rest = 0;

		// Bei jeder Stelle im Dezimalsystem wird der Faktor um 10 multipliziert (10^0,
		// 10^1, 10^2, ...)
		int decimalFactor = 1;

		while (n > 0) {
			rest += (n % 3) * decimalFactor;
			decimalFactor *= 10;
			n /= 3;
		}
		return rest;
	}

	public static String intToPinguNum(int n) {

		String result = "";

		// Überprüfen ob eine negative Zahl eingegeben wurde

		if (n < 0) {
			return "N.D.";
		} else {

			// Umwandeln der Dezimalzahl ins Ternärsystem und in einem String
			String ternary = String.valueOf(intToTernary(n));

			// Falls die Nummer im Ternärsystem nur aus eine Ziffer besteht
			if (ternary.length() == 1) {
				if (intToTernary(n) == 0) {
					return "In";
				} else if (intToTernary(n) == 1) {
					return "Gu";
				} else {
					return "Pin";
				}
			}

			// Erste Ziffer muss Großgeschrieben sein

			if (ternary.charAt(0) == '0') {
				result += "In";
			} else if (ternary.charAt(0) == '1') {
				result += "Gu";
			} else {
				result += "Pin";
			}

			// Alle andere Ziffern

			for (int i = 1; i < ternary.length(); i++) {
				if (ternary.charAt(i) == '0') {
					result += "in";
				} else if (ternary.charAt(i) == '1') {
					result += "gu";
				} else if (ternary.charAt(i) == '2') {
					result += "pin";
				}
			}

			return result;

		}

	}

	public static int pinguNumToInt(String pinguNum) {

		String result = "";
		int decimal = 0;

		int n = 0;
		boolean isRealPinguNum = false;
		int step = 2;

		// Überprüft ob die erten 2 bzw. 3 (bei Pin) gültig sind. Falls nicht wird -1
		// zurückgegeben.

		if (pinguNum.length() > 0) {
			switch (pinguNum.charAt(0)) {
			case 'I': {
				if (pinguNum.charAt(1) == 'n') {
					isRealPinguNum = true;
					result += "0";
				}
				break;
			}
			case 'G': {
				if (pinguNum.charAt(1) == 'u') {
					isRealPinguNum = true;
					result += "1";
				}
				break;
			}
			case 'P': {
				if (pinguNum.charAt(1) == 'i' && pinguNum.charAt(2) == 'n') {
					isRealPinguNum = true;
					result += "2";
					step = 3;
				}
				break;
			}
			default:
				isRealPinguNum = false;
				break;
			}
		} else {
			return -1;
		}

		// Falls die ersten Silben gültig sind werden hier alle anderen überprüft und
		// die Ternärzahl in result als String gespeichert.

		if (isRealPinguNum == true) {
			for (int i = step; i < pinguNum.length() - 1; i += step) {
				if (pinguNum.substring(i, i + 2).equals("in")) {
					result += "0";
					step = 2;
				} else if (pinguNum.substring(i, i + 2).equals("gu")) {
					result += "1";
					step = 2;
				} else if (pinguNum.substring(i, i + 3).equals("pin")) {
					result += "2";
					step = 3;
				} else {
					isRealPinguNum = false;
				}
			}
		} else {
			return -1;
		}

		// Wandelt die Ternärzahl ins Dezimalsystem um (durch Multiplizierung mit 3 hoch
		// den jeweiligen Exponenten bei jeder Stelle)(mit Math.pow viel einfacher)

		if (isRealPinguNum == true) {
			if (result.equals("0")) {
				return 0;
			} else {
				for (int i = 0; i <= result.length() - 1; i++) {
					int threePower = (result.length() - 1) - i;
					long threeToThePowerOfI = 1;
					while (threePower != 0) {
						threeToThePowerOfI *= 3;
						--threePower;
					}

					decimal += (Integer.valueOf(String.valueOf(result.charAt(i))) * threeToThePowerOfI);
				}

				return decimal;
			}
		} else {
			return -1;
		}
	}

//	// Tests
//
//	@Test
//	public void testintToPinguNum() {
//		assertEquals("In", intToPinguNum(0));
//		assertEquals("Gu", intToPinguNum(1));
//		assertEquals("Pin", intToPinguNum(2));
//		assertEquals("Pinguin", intToPinguNum(21));
//		assertEquals("Gugupin", intToPinguNum(14));
//		assertEquals("Gupinpinguin", intToPinguNum(156));
//		assertEquals("N.D.", intToPinguNum(-5));
//
//	}
//
//	@Test
//	public void testPinguNumToInt() {
//		assertEquals(0, pinguNumToInt("In"));
//		assertEquals(1, pinguNumToInt("Gu"));
//		assertEquals(2, pinguNumToInt("Pin"));
//		assertEquals(21, pinguNumToInt("Pinguin"));
//		assertEquals(14, pinguNumToInt("Gugupin"));
//		assertEquals(156, pinguNumToInt("Gupinpinguin"));
//		assertEquals(-1, pinguNumToInt("123"));
//		assertEquals(-1, pinguNumToInt("Abc"));
//		assertEquals(-1, pinguNumToInt("pingu"));
//
//	}

}
