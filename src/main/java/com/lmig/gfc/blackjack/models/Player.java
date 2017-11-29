package com.lmig.gfc.blackjack.models;

public class Player implements Person {

	private Hand hand;

	public Player() {
		hand = new Hand();
	}

	@Override
	public Hand getHand() {
		return hand;
	}

	@Override
	public void addToHand(Card card) {
		hand.addToHand(card);

	}

}
