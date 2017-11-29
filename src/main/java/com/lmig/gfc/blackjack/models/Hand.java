package com.lmig.gfc.blackjack.models;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand;
	private int handValue = 0;
	private boolean isBlackjack = false;
	private boolean isBusted = false;

	public Hand() {
		hand = new ArrayList<Card>();
	}

	public void addToHand(Card card) {
		handValue += card.getValue();
		hand.add(card);

		if (hand.size() == 2 && handValue == 21) {
			isBlackjack = true;
		}
		if (handValue > 21) {
			isBusted = true;
		}

	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getHandValue() {
		return handValue;
	}

	public boolean isBlackjack() {
		return isBlackjack;
	}

	public boolean isBusted() {
		return isBusted;
	}

}
