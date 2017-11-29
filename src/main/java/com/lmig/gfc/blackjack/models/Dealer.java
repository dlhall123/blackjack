package com.lmig.gfc.blackjack.models;

public class Dealer implements Person {

	Hand hand;

	public Dealer() {
		hand = new Hand();
	}

	// public void hit(Deck deck) {
	// Card hitCard = deck.getCard();
	// hand.add(hitCard);
	// handValue += hitCard.getValue();
	// }

	@Override
	public Hand getHand() {
		return hand;
	}

	@Override
	public void addToHand(Card card) {
		hand.addToHand(card);
	}

}
