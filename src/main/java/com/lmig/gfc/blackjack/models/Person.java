package com.lmig.gfc.blackjack.models;

import java.util.ArrayList;

public abstract class Person {
	private ArrayList<Card> hand;
	private int handValue;
	
	protected abstract void hit(Deck deck);

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getHandValue() {
		return handValue;
	}

}
