package com.lmig.gfc.blackjack.models;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand;
	private int handValue = 0;
	private boolean isBlackjack = false;
	private boolean isBusted = false;
	private int numAcesCounted11 = 0;

	public Hand() {
		hand = new ArrayList<Card>();
	}

	public void addToHand(Card card) {
		handValue += card.getValue();
		if (card.getValue() == 11) {
			numAcesCounted11 += 1;
		}

		hand.add(card);

		if (hand.size() == 2 && handValue == 21) {
			isBlackjack = true;
		}

		while (handValue > 21 && numAcesCounted11 > 0) {
			handValue -= 10;
			numAcesCounted11 -= 1;
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
