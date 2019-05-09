package ArtificialIntelligenceComponents;

import java.util.ArrayList;

import Controller.GameController;
import Controller.GameInteractions;
import ModelClasses.Player;

public class ArtificialIntelligenceHandler {
	
	private static ArrayList<ArtificialIntelligence> agents = new ArrayList<ArtificialIntelligence>();
	
	public static void requestArtificialIntelligenceBinding(Player binding) {
		ArtificialIntelligence bindingAgent = new ArtificialIntelligence(binding);
		for(ArtificialIntelligence agent : agents)
			if(agent.equals(bindingAgent)) return;
		agents.add(bindingAgent);
	}

	public static void update() {
		ArtificialIntelligence activeAgent = null;
		for(ArtificialIntelligence agent : agents) {
			if(agent.binding == GameInteractions.getActivePlayer()) {
				activeAgent = agent;
				break;
			}
		}
		if(activeAgent != null) {
			if(!activeAgent.update()) {
				GameController.interactions.requestNextPhase();
			}
		}
	}

}
