package GameAssets.DefaultRiskMode;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ModelClasses.Card.CARD_TYPES;
import UIComponents.Coordinate;
import UIComponents.VisualCard;

public class DefaultRiskVisualCard extends VisualCard {
	
	public DefaultRiskMode.TERRITORIES territory;
	public ArrayList<Coordinate> characteristicVisualData;
	
	public DefaultRiskVisualCard(DefaultRiskMode.TERRITORIES territory, CARD_TYPES cardType) {
		super(cardType);
		this.territory = territory;
	}

	@Override
	protected String getCardTag() {
		return territory.toString();
	}

	@Override
	protected ArrayList<Coordinate> getCharacteristicVisualData() {
		return characteristicVisualData;
	}

	@Override
	protected BufferedImage getSymbolicData() {
		if(cardType == CARD_TYPES.EASY_UNIT) 
			return DefaultRiskMode.CARD_SYMBOLS[0];
		else if(cardType == CARD_TYPES.MODERATE_UNIT) 
			return DefaultRiskMode.CARD_SYMBOLS[1];
		else if(cardType == CARD_TYPES.HARD_UNIT) 
			return DefaultRiskMode.CARD_SYMBOLS[2];
		else if(cardType == CARD_TYPES.EXTREME_UNIT) 
			return DefaultRiskMode.CARD_SYMBOLS[3];
		return null;
	}

	@Override
	public boolean checkItsCorresponding(String checkTag) {
		try {
			DefaultRiskMode.TERRITORIES check = DefaultRiskMode.TERRITORIES.valueOf(checkTag);
			if(check == null) return false;
			
			return territory == check;
		}
		catch(IllegalArgumentException exception) {
			return false;
		}
	}

	@Override
	public String getCorrespondingTag() {
		return territory.toString();
	}

	@Override
	public void print() {
		System.out.println(territory.toString() + ": " + cardType.toString());
	}

}
