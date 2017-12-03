package com.lmig.gfc.blackjack.models;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand;
	private int handValue = 0;
	private boolean isBlackjack = false;
	private boolean isBusted = false;
	private int numAcesCounted11 = 0;

	public Hand() {
		// initialize ArrayList to hold the hand of cards
		hand = new ArrayList<Card>();
	}

	public void addToHand(Card card) {
		handValue += card.getValue();

		// If the card is an ace (value stored in card 11), increment ace counter
		if (card.getValue() == 11) {
			numAcesCounted11 += 1;
		}
		// Add the card passed into the hand
		hand.add(card);
		// set the blackjack flag if there are two cards in the hand and the value of
		// the hand is 21
		if (hand.size() == 2 && handValue == 21) {
			isBlackjack = true;
		}
		// If the hand is over 21 and there are aces, want to decrement the hand by 10
		// until the hand is below 21 or we're out of aces that we added to the hand.
		// this will allow an ace to count for 1 or 11, depending on the situation
		while (handValue > 21 && numAcesCounted11 > 0) {
			handValue -= 10;
			numAcesCounted11 -= 1;
		}

		// Set the busted flag if the value of the hand is over 21
		if (handValue > 21) {
			isBusted = true;
		}

	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public Card getCardFromHand(int index) {
		Card returnCard = hand.get(index);
		handValue -= returnCard.getValue();
		hand.remove(index);
		return returnCard;
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
