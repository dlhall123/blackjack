package com.lmig.gfc.blackjack.models;

import java.util.ArrayList;
import java.util.Collections;

public class Shoe {
	private ArrayList<Card> shoe;

	public Shoe(int numDecks) {
		shoe = new ArrayList<Card>();
		initialize(numDecks);
	}

	private void initialize(int numDecks) {
		for (int i = 0; i < numDecks; i += 1) {
			for (Suit suit : Suit.values()) {
				for (Rank rank : Rank.values()) {
					Card card = new Card(suit, rank);
					shoe.add(card);
				}
			}
		}
		Collections.shuffle(shoe);
	}

	public Card getACard() {
		Card returnCard = shoe.get(0);
		shoe.remove(0);
		return returnCard;
	}

	// size method to get count
	public int getShoeSize() {
		return shoe.size();
	}

}
