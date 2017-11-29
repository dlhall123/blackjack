package com.lmig.gfc.blackjack.models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deck;

	public Deck(int numDecks) {
		deck = new ArrayList<Card>();
		initialize(numDecks);
		shuffle();
	}

	private void initialize(int numDecks) {
		for (int i = 0; i < numDecks; i += 1) {
			for (Suit s : Suit.values()) {
				for (Rank r : Rank.values()) {
					Card card = new Card(s, r);
					deck.add(card);
				}
			}
		}
	}

	private void shuffle() {
		Collections.shuffle(deck);
	}

	public Card getCard() {
		Card returnCard = deck.get(0);
		deck.remove(0);
		return returnCard;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

}
