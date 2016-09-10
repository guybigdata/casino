package server;

import java.util.Random;
import java.util.stream.IntStream;

public class RouletaWheel {
	
	private int[] Numbers;
	
	public int[] getNumbers() {
		return Numbers;
	}

	public void setNumbers(int[] numbers) {
		Numbers = numbers;
	}

	
	public int lotteryNumber() {
		int[] numbers = IntStream.rangeClosed(2, 3).toArray();
		int idx = new Random().nextInt(numbers.length);
		int random = (numbers[idx]);
		return random;
	}
	
	public String lotteryColor(){
		String[] colors = new String[2];
		colors[0] = "b";
		colors[1] = "r";
		int idx = new Random().nextInt(colors.length);
		String random = (colors[idx]);
		return random;
		
	}
}
