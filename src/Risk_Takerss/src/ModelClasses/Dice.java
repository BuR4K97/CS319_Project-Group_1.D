package ModelClasses;
import java.util.ArrayList;

public class Dice {

	private static class Die implements Comparable<Die> {
		
		private int faceNumber = 0;
		private void throwDie() { faceNumber = (int)(Math.random() * 6) + 1; }
		@Override
		public int compareTo(Die dieToCompare) {
			if(faceNumber < dieToCompare.faceNumber) return -1;
			if(faceNumber > dieToCompare.faceNumber) return 1;
			return 0;
		}
		
		
	}//endInnerClass
	
	private ArrayList<Die> dice;
	
	public Dice(int numberOfDice) {
		dice = new ArrayList<Die>();
		for(int i = 0; i < numberOfDice; i++) dice.add(new Die());
	}
	
	public void rollDice() {
		for(Die currDie : dice) currDie.throwDie();
		sortDice();
	}
	private void sortDice() { dice.sort(null); }
	
	public ArrayList<Integer> getFaceNumbers() {
		ArrayList<Integer> faceNumbers = new ArrayList<Integer>();
		for(Die currDie : dice) faceNumbers.add(currDie.faceNumber);
		return faceNumbers;
	}
}
