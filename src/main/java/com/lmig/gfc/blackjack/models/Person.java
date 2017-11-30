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

	public void newHand() {
		hand = new Hand();
	}

}
