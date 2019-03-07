import java.util.ArrayList;

public class Turn {
	ArrayList<Player> list;
	int size;
	int whoseTurn;
	
	public Turn() {
		 list = new ArrayList<Player>();
		 size = 0;
		 whoseTurn = 0;
	}
	
	public void addPlayer(Player player) {
		list.add(player);
		size++;
	}
	public Player getWhoseTurn() {
		return list.get(whoseTurn);
	}
	public void nextTurn() {
		if(whoseTurn < size - 1)
			whoseTurn++;
		else if(whoseTurn == size - 1)
			whoseTurn = 0;
	}
	
}
