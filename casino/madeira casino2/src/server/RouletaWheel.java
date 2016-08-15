package server;

import java.util.Random;
import java.util.stream.IntStream;

public class RouletaWheel 
{

	public int[] getNumbers() {
		return Numbers;
	}

	public void setNumbers(int[] numbers) {
		Numbers = numbers;
	}

	private int[] Numbers;
	
	public int lotteryNumber(){
		int[] numbers = IntStream.rangeClosed(1, 36).toArray();//{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
		int idx = new Random().nextInt(numbers.length);
		int random = (numbers[idx]);
		return random;
	}
}
