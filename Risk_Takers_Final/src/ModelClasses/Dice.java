package ModelClasses;
import java.util.ArrayList;

/**
 * Dice class holds as many die as given numberOfDie.
 * Its roll behaviour works as normal die roll.
 * It includes inner die class to determine single die behavior.
**/
public class Dice {

	/**
	 * Randomly select and number between 1-6 as normal die.
	 * Since it is comparable, an list of Die can be sorted easily. See Collections.sort().
	**/
	private static class Die implements Comparable<Die> {
		
		private int faceNumber = 0;
		
		private void throwDie() { 
			faceNumber = (int)(Math.random() * 6) + 1;
		}
		
		@Override
		public int compareTo(Die dieToCompare) {
			if(faceNumber < dieToCompare.faceNumber) return -1;
			if(faceNumber > dieToCompare.faceNumber) return 1;
			return 0;
		}
		
	}//endInnerClass
	
	private ArrayList<Die> dice;
	
	/**
	 * Creates a die group including as many die as given numberOfDie. 
	**/
	public Dice(int numberOfDie) {
		dice = new ArrayList<Die>();
		for(int i = 0; i < numberOfDie; i++) dice.add(new Die());
	}
	
	/**
	 * Rolls all dice and sorts them.
	 * Rolling dice, the greatest will be at the end of the list. See Dice.sortDice().
	**/
	public void rollDice() {
		for(Die currDie : dice) currDie.throwDie();
		sortDice();
	}
	
	/**
	 * Just sorts the die group in ascending order.
	 * The greatest one will be at the end of the list.
	 **/
	private void sortDice() { 
		dice.sort(null);
	}
	
	/**
	 * Fills and returns a list with dice's face numbers through sorted dice list.
	 * The numbers are sorted in ascending order. See Dice.rollDice().
	**/
	public ArrayList<Integer> getFaceNumbers() {
		ArrayList<Integer> faceNumbers = new ArrayList<Integer>();
		for(Die currDie : dice) faceNumbers.add(currDie.faceNumber);
		return faceNumbers;
	}
}
