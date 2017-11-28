package com.lmig.gfc.blackjack.models;

import java.util.ArrayList;

public class Player {
	
	private ArrayList<Card> hand;
	private int handValue;
	
	public Player(Deck deck) {
		hand = new ArrayList<Card>();
		hit(deck);
		hit(deck);
	}
	
	public void hit(Deck deck) {
		Card hitCard = deck.getCard();
		hand.add(hitCard);
		handValue += hitCard.getValue();
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getHandValue() {
		return handValue;
	}

}
