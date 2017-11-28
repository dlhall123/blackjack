package com.lmig.gfc.blackjack.models;

import java.util.ArrayList;

public class BlackjackGame {
	Deck cardDeck;
	Player player;
	int cardsInDeck;
	ArrayList<Card> playerHand;
	public BlackjackGame() {
		cardDeck = new Deck();
	}
	
	public void newGame() {
		player = new Player(cardDeck);
		playerHand = player.getHand();
		cardsInDeck = cardDeck.getDeck().size();
	}
	public void hit() {
		player.hit(cardDeck);
		cardsInDeck = cardDeck.getDeck().size();
		
	}

}
