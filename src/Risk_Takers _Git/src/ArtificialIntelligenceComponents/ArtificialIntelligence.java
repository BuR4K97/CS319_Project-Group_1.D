package ArtificialIntelligenceComponents;

import java.util.ArrayList;

import AnimationComponents.AnimationHandler;
import Controller.GameController;
import Controller.GameInteractions;
import ModelClasses.Card;
import ModelClasses.Card.CARD_ACTIVATION;
import ModelClasses.Card.CARD_TYPES;
import ModelClasses.Combat;
import ModelClasses.Combat.COMBAT_TYPE;
import ModelClasses.GameState;
import ModelClasses.Player;
import ModelClasses.Territory;
import ModelClasses.TerritoryGraph;
import ModelClasses.Turn;
import ModelClasses.UnitPocket;
import ModelClasses.Turn.TURN_PHASE;

public class ArtificialIntelligence {

	protected Player binding;


	public ArtificialIntelligence(Player binding) {
		this.binding = binding;
	}


	public boolean equals(ArtificialIntelligence check) {
		return binding == check.binding;
	}


	public boolean update() {
		if(Turn.activePhase == TURN_PHASE.DRAFT) return draftPhaseUpdate();
		else if(Turn.activePhase == TURN_PHASE.ATTACK) return attackPhaseUpdate();
		else return fortifyPhaseUpdate();
	}

	private boolean draftPhaseUpdate() {
		boolean terminated = true;
		activateCards();

		GameMoment initialMoment = new GameMoment(binding, GameState.extractGameState()); initialMoment.extractHeuristicValue();
		ArrayList<GameMoment> searchList = new ArrayList<GameMoment>(); searchList.add(initialMoment);

		int availableUnit = binding.getAvailableUnitAmount();
		GameMoment executeMoment = null; GameMoment branchMoment;
		while(availableUnit > 0 && searchList.get(0) != executeMoment) {
			executeMoment = searchList.get(0);

			for(Territory terr : executeMoment.territoryMoment) {
				branchMoment = executeMoment.copy();
				branchMoment.territoryMoment.get(branchMoment.territoryMoment.indexOf(terr)).addUnits(availableUnit);
				branchMoment.extractHeuristicValue();
				searchList.add(branchMoment);
			}

			availableUnit = 0;
			searchList.sort(null);
		}
		GameMoment goalMoment = searchList.get(0);
		goalMoment.flushHeuristicMoment();

		int transfer;
		for(int i = 0; i < initialMoment.territoryMoment.size(); i++) {
			transfer = goalMoment.territoryMoment.get(i).getUnitNumber() 
					- initialMoment.territoryMoment.get(i).getUnitNumber();
			if(transfer > 0) {
				GameController.interactions.synchronizeDirectFocusTerritories(initialMoment.territoryMoment.get(i), null);
				GameController.interactions.requestAction(transfer);
				GameInteractions.requestManualGameUpdate();
				terminated = false;
			}
		}
		return !terminated;
	}
	
	private boolean attackPhaseUpdate() {
		boolean terminated = true;
		if(binding.getCardSet().size() >= UnitPocket.MAX_CARD) activateCards();

		GameMoment initialMoment = new GameMoment(binding, GameState.extractGameState()); initialMoment.extractHeuristicValue();
		ArrayList<GameMoment> searchList = new ArrayList<GameMoment>(); searchList.add(initialMoment);
		GameMoment executeMoment = searchList.get(0); ArrayList<Territory> connects; GameMoment branchMoment;
		for(Territory execute : executeMoment.territoryMoment) {
			connects = executeMoment.extractConnectedTerritories(execute);
			for(Territory connect : connects) {
				if(connect.getPlayer() != binding) {
					branchMoment = executeMoment.copy();
					if(branchMoment.executeAttack(branchMoment.extractCorrespondingTerritory(execute)
							, branchMoment.extractCorrespondingTerritory(connect))) {
						branchMoment.extractHeuristicValue();
						searchList.add(branchMoment); 
					}
				}
			}
		}
		searchList.sort(null);
		GameMoment goalMoment = searchList.get(0);

		for(int i = 0; i < initialMoment.territoryMoment.size(); i++) {
			if(initialMoment.territoryMoment.get(i).getUnitNumber() != goalMoment.territoryMoment.get(i).getUnitNumber()) {
				GameController.interactions.synchronizeDirectFocusTerritories(goalMoment.territoryMoment.get(i)
						, goalMoment.territoryMoment.get(goalMoment.territoryMoment.size() - 1));
				GameController.interactions.requestAction(0);
				ArtificialIntelligenceHandler.requestSuspendAttack();
				terminated = false;
				break;
			}
		}
		return !terminated;
	}

	private boolean fortifyPhaseUpdate() {
		boolean terminated = true;

		GameMoment initialMoment = new GameMoment(binding, GameState.extractGameState());
		GameMoment goalMoment = initialMoment.copy(); goalMoment.flushHeuristicMoment();

		Territory source = null, target = null, initialSource = null;
		for(int i = 0; i < initialMoment.territoryMoment.size(); i++) {
			if(initialMoment.territoryMoment.get(i).getUnitNumber() < goalMoment.territoryMoment.get(i).getUnitNumber())
				target = goalMoment.territoryMoment.get(i);
			else if(initialMoment.territoryMoment.get(i).getUnitNumber() > goalMoment.territoryMoment.get(i).getUnitNumber()) {
				source = goalMoment.territoryMoment.get(i); initialSource = initialMoment.territoryMoment.get(i);
			}
			if(source != null && target != null) break;
		}

		if(source != null && target != null) {
			GameController.interactions.synchronizeDirectFocusTerritories(source, target);
			GameController.interactions.requestAction(initialSource.getUnitNumber() - source.getUnitNumber());
			terminated = false;
		}

		return !terminated;
	}

	private void activateCards() {
		int[] combinatorials = new int[] {0, 0, 0};
		int extreme = 0;
		for(Card card : binding.getCardSet()) {
			if(card.cardType == CARD_TYPES.EASY_UNIT)
				combinatorials[0] += 1;
			else if(card.cardType == CARD_TYPES.MODERATE_UNIT)
				combinatorials[1] += 1;
			else if(card.cardType == CARD_TYPES.HARD_UNIT)
				combinatorials[2] += 1;
			else if(card.cardType == CARD_TYPES.EXTREME_UNIT)
				extreme += 1;
		}

		int combinationNumber = UnitPocket.MAX_CARD;
		for(int combinatorial : combinatorials)
			if(combinatorial < combinationNumber)
				combinationNumber = combinatorial;

		final int combinationalScore = (CARD_TYPES.getUnitBuff(CARD_TYPES.EASY_UNIT)
				+ CARD_TYPES.getUnitBuff(CARD_TYPES.MODERATE_UNIT) + CARD_TYPES.getUnitBuff(CARD_TYPES.HARD_UNIT))
				/ CARD_ACTIVATION.COMBINATIONAL.activation;
		int maxScore = 0, currScore, activateCombination = 0;
		for(int i = 0; i < combinationNumber + 1; i++) {
			currScore = 0;
			int[] copy = new int[] {combinatorials[0], combinatorials[0], combinatorials[0]};

			copy[0] -= i; copy[1] -= i; copy[2] -= i;
			CARD_TYPES cardType = CARD_TYPES.EASY_UNIT;
			for(int count : copy) {
				currScore += (count / CARD_ACTIVATION.COMBINATIONAL.activation) * CARD_TYPES.getUnitBuff(cardType);
				if(cardType == CARD_TYPES.EASY_UNIT)
					cardType = CARD_TYPES.MODERATE_UNIT;
				else
					cardType = CARD_TYPES.HARD_UNIT;
			}
			currScore += i * combinationalScore;
			if(currScore > maxScore) {
				maxScore = currScore;
				activateCombination = i;
			}
		}

		ArrayList<Card> activates = new ArrayList<Card>(); 
		ArrayList<CARD_TYPES> combinationalTypes = new ArrayList<>(); combinationalTypes.add(CARD_TYPES.EASY_UNIT);
		combinationalTypes.add(CARD_TYPES.MODERATE_UNIT); combinationalTypes.add(CARD_TYPES.HARD_UNIT);
		ArrayList<CARD_TYPES> cardTypes;
		for(int i = activateCombination; i > 0; i--) {
			cardTypes = new ArrayList<CARD_TYPES>(combinationalTypes);
			for(Card card : binding.getCardSet()) {
				if(cardTypes.contains(card.cardType)) {
					activates.add(card);
					cardTypes.remove(card.cardType);
					if(cardTypes.isEmpty()) break;
				}
			}
			GameInteractions.requestDirectCardActivation(activates);
			activates.clear();
			combinatorials[0]--; combinatorials[1]--; combinatorials[2]--;
		}
		CARD_TYPES cardType;
		for(int i = 0; i < combinatorials.length; i++) {
			while(combinatorials[i] >= CARD_ACTIVATION.COMBINATIONAL.activation) {
				if(i == 0) cardType = CARD_TYPES.EASY_UNIT;
				else if(i == 1) cardType = CARD_TYPES.MODERATE_UNIT;
				else cardType = CARD_TYPES.HARD_UNIT;
				for(Card card : binding.getCardSet()) {
					if(card.cardType == cardType) {
						activates.add(card);
						if(activates.size() == CARD_ACTIVATION.COMBINATIONAL.activation) break;
					}
				}
				GameInteractions.requestDirectCardActivation(activates);
				activates.clear();
				combinatorials[i] -= CARD_ACTIVATION.COMBINATIONAL.activation;
			}
		}

		while(extreme >= CARD_ACTIVATION.COMBINATIONAL.activation) {
			for(Card card : binding.getCardSet()) {
				if(card.cardType == CARD_TYPES.EXTREME_UNIT) {
					activates.add(card);
					if(activates.size() == CARD_ACTIVATION.COMBINATIONAL.activation) break;
				}
			}
			GameInteractions.requestDirectCardActivation(activates);
			activates.clear();
			extreme -= CARD_ACTIVATION.COMBINATIONAL.activation;
		}
	}

	private static class GameMoment extends GameState implements Comparable<GameMoment> {

		private Player momentFocus;
		private ArrayList<Territory> territoryMoment;
		private ArrayList<Card> cardMoment;
		private double heuristicValue;

		private GameMoment(Player momentFocus) {
			this.momentFocus = momentFocus;
			territoriesState = new ArrayList<Territory>();
			territoryMoment = new ArrayList<Territory>();
			cardMoment = new ArrayList<Card>();
		}

		private GameMoment(Player momentFocus, GameState gameState) {
			this.momentFocus = momentFocus;
			territoriesState = new ArrayList<Territory>();
			territoryMoment = new ArrayList<Territory>();
			Territory stateCopy;
			for(Territory terr : gameState.getTerritoriesState()) {
				stateCopy = terr.stateCopy();
				territoriesState.add(stateCopy);
				if(terr.getPlayer() == momentFocus)
					territoryMoment.add(stateCopy); 
			}
			cardMoment = new ArrayList<Card>();
			for(Card card : momentFocus.getCardSet())
				cardMoment.add(card);
		}

		private GameMoment copy() {
			GameMoment copy = new GameMoment(momentFocus);
			Territory stateCopy;
			for(Territory terr : territoriesState) {
				stateCopy = terr.stateCopy();
				copy.territoriesState.add(stateCopy);
				if(terr.getPlayer() == momentFocus)
					copy.territoryMoment.add(stateCopy); 
			}
			for(Card card : momentFocus.getCardSet())
				copy.cardMoment.add(card);
			return copy;
		}

		private Territory extractCorrespondingTerritory(Territory extract) {
			for(Territory terr : territoriesState)
				if(terr.equals(extract)) return terr;
			return null;
		}

		private ArrayList<Territory> extractConnectedTerritories(Territory extract) {
			TerritoryGraph graph = GameController.activeMode.territoryGraph;
			ArrayList<Territory> connects = new ArrayList<Territory>();
			for(Territory terr : territoriesState)
				if(graph.checkConnect(extract, terr)) connects.add(terr);
			return connects;
		}

		private boolean executeAttack(Territory sourceTerritory, Territory targetTerritory) {
			if(!Combat.combatable(sourceTerritory, targetTerritory)) return false;
			Combat execute = new Combat(sourceTerritory, targetTerritory);
			execute.combatType = COMBAT_TYPE.SIMULATIVE;
			execute.combatTillCapture();
			if(targetTerritory.getUnitNumber() < Combat.MIN_DEFENSE_UNIT) {
				targetTerritory.setPlayer(momentFocus);
				territoryMoment.add(targetTerritory);
				targetTerritory.addUnits(sourceTerritory.getUnitNumber() - Combat.MIN_DEFENSE_UNIT);
				sourceTerritory.removeUnits(sourceTerritory.getUnitNumber() - Combat.MIN_DEFENSE_UNIT);
				cardMoment.add(GameController.activeMode.findItsCardCorresponding(targetTerritory.getCorrespondingTag()));
				return true;
			}
			return false;
		}

		private void flushHeuristicMoment() {
			Territory heuristicTerritory = null;
			for(Territory terr : territoryMoment) {
				if(heuristicTerritory == null) heuristicTerritory = terr;
				else if(terr.getUnitNumber() > heuristicTerritory.getUnitNumber()) heuristicTerritory = terr;
			}
			if(heuristicTerritory != null) {
				Territory flush = GameController.activeMode.territoryGraph.extractFlushTerritory(heuristicTerritory);
				if(flush == null) return;
				flush = extractCorrespondingTerritory(flush);
				if(flush == heuristicTerritory) return;
				flush.addUnits(heuristicTerritory.getUnitNumber() - Combat.MIN_DEFENSE_UNIT);
				heuristicTerritory.removeUnits(heuristicTerritory.getUnitNumber() - Combat.MIN_DEFENSE_UNIT); 
			}
		}

		@Override
		public int compareTo(GameMoment check) {
			if(heuristicValue > check.heuristicValue) return 1;
			if(heuristicValue < check.heuristicValue) return -1;
			return 0;
		}

		private void extractHeuristicValue() {
			double territoryNumber = extractTerritoryNumber();
			double territorialMomentScore = extractTerritorialMomentScore();
			double distributionalScore = extractDistributionalScore();
			int continentalCardScore = extractContinentalCardScore();
			double territorialCardScore = extractTerritorialCardScore();
			int activationalCardScore = extractActivationalCardScore();
			
			double territorialScore = (territoryNumber / CARD_ACTIVATION.COMBINATIONAL.activation) + territorialCardScore
					+ continentalCardScore + Math.pow(activationalCardScore, 1.13);
			territorialScore = (territorialScore / 100) + 1;
			territorialScore = Math.pow(territorialScore, 10) * Math.pow(territorialMomentScore, 0.33);
			heuristicValue = 1000 * Math.pow(distributionalScore + 1, -3) / (territorialScore + 1);
		}

		private int extractTerritoryNumber() {
			return territoryMoment.size();
		}

		private double extractTerritorialMomentScore() {
			double momentScore = 0;
			for(Territory terr : territoryMoment)
				momentScore += Math.pow(terr.getUnitNumber(), 2);
			momentScore = Math.log1p(momentScore);
			return momentScore;
		}

		private double extractDistributionalScore() {
			int unitNumber = 0;
			for(Territory terr : territoryMoment) unitNumber += terr.getUnitNumber();
			TerritoryGraph graph = GameController.activeMode.territoryGraph;
			return graph.extractDistributionalScore(territoryMoment) / (Math.log1p(Math.pow(territoryMoment.size(), 2))
					* Math.log1p(unitNumber) + 1);
		}

		private int extractContinentalCardScore() {
			return GameController.activeMode.extractModeSpecificScore(territoryMoment);
		}

		private double extractTerritorialCardScore() {
			double territorialCardScore = 0;
			for(Card card : cardMoment)
				territorialCardScore += card.getUnitBuff() / CARD_ACTIVATION.COMBINATIONAL.activation;
			return territorialCardScore;
		}

		private int extractActivationalCardScore() {
			int[] combinatorials = new int[] {0, 0, 0};
			int extreme = 0;
			for(Card card : cardMoment) {
				if(card.cardType == CARD_TYPES.EASY_UNIT)
					combinatorials[0] += 1;
				else if(card.cardType == CARD_TYPES.MODERATE_UNIT)
					combinatorials[1] += 1;
				else if(card.cardType == CARD_TYPES.HARD_UNIT)
					combinatorials[2] += 1;
				else if(card.cardType == CARD_TYPES.EXTREME_UNIT)
					extreme += 1;
			}

			int combinationNumber = UnitPocket.MAX_CARD;
			for(int combinatorial : combinatorials)
				if(combinatorial < combinationNumber)
					combinationNumber = combinatorial;

			final int combinationalScore = (CARD_TYPES.getUnitBuff(CARD_TYPES.EASY_UNIT)
					+ CARD_TYPES.getUnitBuff(CARD_TYPES.MODERATE_UNIT) + CARD_TYPES.getUnitBuff(CARD_TYPES.HARD_UNIT))
					/ CARD_ACTIVATION.COMBINATIONAL.activation;
			int maxScore = 0, currScore;
			for(int i = 0; i < combinationNumber + 1; i++) {
				currScore = 0;
				int[] copy = new int[] {combinatorials[0], combinatorials[0], combinatorials[0]};

				copy[0] -= i; copy[1] -= i; copy[2] -= i;
				CARD_TYPES cardType = CARD_TYPES.EASY_UNIT;
				for(int count : copy) {
					currScore += (count / CARD_ACTIVATION.COMBINATIONAL.activation) * CARD_TYPES.getUnitBuff(cardType);
					if(cardType == CARD_TYPES.EASY_UNIT)
						cardType = CARD_TYPES.MODERATE_UNIT;
					else
						cardType = CARD_TYPES.HARD_UNIT;
				}
				currScore += i * combinationalScore;
				if(currScore > maxScore) maxScore = currScore;
			}

			maxScore += (extreme / CARD_ACTIVATION.COMBINATIONAL.activation)
					* CARD_TYPES.getUnitBuff(CARD_TYPES.EXTREME_UNIT);
			return maxScore;
		}

		private void print() {
			for(Territory terr : territoryMoment) {
				terr.print(); System.out.println();
			}
		}

	}

}
