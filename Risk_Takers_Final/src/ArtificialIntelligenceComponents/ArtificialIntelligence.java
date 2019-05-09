package ArtificialIntelligenceComponents;

import ModelClasses.Player;

public class ArtificialIntelligence {
	
	protected Player binding;
	
	
	public ArtificialIntelligence(Player binding) {
		this.binding = binding;
	}


	public boolean equals(ArtificialIntelligence check) {
		return binding == check.binding;
	}


	public boolean update() {
		boolean terminated = true;
		
		
		return !terminated;
	}
	
	

}
