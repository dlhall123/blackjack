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
			for (Suit s : Suit.values()) {
				for (Rank r : Rank.values()) {
					Card card = new Card(s, r);
					shoe.add(card);
				}
			}
		}
		Collections.shuffle(shoe);
	}

	public Card getCard() {
		Card returnCard = shoe.get(0);
		shoe.remove(0);
		return returnCard;
	}

	public ArrayList<Card> getShoe() {
		return shoe;
	}

}
