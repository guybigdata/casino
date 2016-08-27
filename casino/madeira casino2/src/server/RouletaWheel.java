package server;

import java.util.Random;
import java.util.stream.IntStream;

public class RouletaWheel {

	public int[] getNumbers() {
		return Numbers;
	}

	public void setNumbers(int[] numbers) {
		Numbers = numbers;
	}

	private int[] Numbers;

	public int lotteryNumber() {
		int[] numbers = IntStream.rangeClosed(1, 36).toArray();
		int idx = new Random().nextInt(numbers.length);
		int random = (numbers[idx]);
		return random;
	}
}
