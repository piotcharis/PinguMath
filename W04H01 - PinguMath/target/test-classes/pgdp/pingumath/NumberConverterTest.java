package pgdp.pingumath;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;

import de.tum.in.test.api.jupiter.HiddenTest;
import de.tum.in.test.api.jupiter.PublicTest;

@W04H01
public class NumberConverterTest {

	@DisplayName("- | Public Test Int -> Pingu")
	@PublicTest
	@Order(1)
	void testIntToPinguPublic() {
		int[] in = { 0, 1, 2, 21, 156 };
		String[] out = Arrays.stream(in).mapToObj(x -> {
			return NumberConverter.intToPinguNum(x);
		}).toArray(String[]::new);
		String[] expected = { "In", "Gu", "Pin", "Pinguin", "Gupinpinguin" };

		if (!Arrays.equals(out, expected))
			fail("Some basic mistake was made. More details in the hidden cases.");
	}

	@DisplayName("- | Hidden Test Int -> Pingu - Basic")
	@HiddenTest
	@Order(2)
	void testIntToPinguHidden() {
		int[] in = { 0, 1, 2, 21, 156 };
		String[] out = Arrays.stream(in).mapToObj(x -> {
			return NumberConverter.intToPinguNum(x);
		}).toArray(String[]::new);
		String[] expected = { "In", "Gu", "Pin", "Pinguin", "Gupinpinguin" };

		if (!Arrays.equals(out, expected)) {
			String errorMSG = IntStream.range(0, in.length).filter(i -> {
				return !out[i].equals(expected[i]);
			}).mapToObj(i -> {
				return "in: <" + in[i] + "> out: <" + out[i] + "> expected: <" + expected[i] + ">\n";
			}).reduce((acc, i) -> {
				return acc + i;
			}).orElse("The test itself failed!");
			fail("The following cases failed:\n" + errorMSG);
		}
	}

	@DisplayName("- | Hidden Test Int -> Pingu - Edge Cases")
	@HiddenTest
	@Order(3)
	void testIntToPinguHiddenEC() {
		int[] in = { -1, -5 };
		String[] out = Arrays.stream(in).mapToObj(x -> {
			return NumberConverter.intToPinguNum(x);
		}).toArray(String[]::new);
		String[] expected = { "N.D.", "N.D." };

		if (!Arrays.equals(out, expected)) {
			String errorMSG = IntStream.range(0, in.length).filter(i -> {
				return !out[i].equals(expected[i]);
			}).mapToObj(i -> {
				return "in: <" + in[i] + "> out: <" + out[i] + "> expected: <" + expected[i] + ">\n";
			}).reduce((acc, i) -> {
				return acc + i;
			}).orElse("The test itself failed!");
			fail("The following cases failed:\n" + errorMSG);
		}
	}

	@DisplayName("- | Public Test Pingu -> Int")
	@PublicTest
	@Order(4)
	void testPinguToIntPublic() {
		String[] in = { "In", "Gu", "Pin", "Pinguin", "Gupinpinguin" };
		int[] out = Arrays.stream(in).mapToInt(x -> {
			return NumberConverter.pinguNumToInt(x);
		}).toArray();
		int[] expected = { 0, 1, 2, 21, 156 };

		if (!Arrays.equals(out, expected))
			fail("Some basic mistake was made. More details in the hidden cases.");
	}

	@DisplayName("- | Hidden Test Pingu -> Int - Basic")
	@HiddenTest
	@Order(5)
	void testPinguToIntHidden() {
		String[] in = { "In", "Gu", "Pin", "Pinguin", "Gupinpinguin" };
		int[] out = Arrays.stream(in).mapToInt(x -> {
			return NumberConverter.pinguNumToInt(x);
		}).toArray();
		int[] expected = { 0, 1, 2, 21, 156 };

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
	}

	@DisplayName("- | Hidden Test Pingu -> Int - Edge Cases")
	@HiddenTest
	@Order(6)
	void testPinguToIntHiddenEC() {
		String[] in = { "In", "Gu", "Pin", "pin", "gu", "in", "pinguin", "PinGuIn", "PinGuPin", "Abc", "abc", "P", "p",
				"Pgu" };
		int[] out = Arrays.stream(in).mapToInt(x -> {
			return NumberConverter.pinguNumToInt(x);
		}).toArray();
		int[] expected = { 0, 1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

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
	}

}
