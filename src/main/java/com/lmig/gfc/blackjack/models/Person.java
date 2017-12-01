package com.lmig.gfc.blackjack.models;

public class Person {

	private Hand hand;

	public Person() {
		hand = new Hand();

	}

	public void addToHand(Card card) {
		hand.addToHand(card);
	}

	public Hand getHand() {
		return hand;
	}

	public int getHandSize() {
		return hand.getHand().size();
	}

	public int getHandValue() {
		return hand.getHandValue();
	}

	public boolean isBusted() {
		return hand.isBusted();
	}

	public boolean hasBlackjack() {
		return hand.isBlackjack();
	}

	public void newHand() {
		hand = new Hand();
	}

}
