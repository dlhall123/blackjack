package com.lmig.gfc.blackjack.models;

public class Card {
	private Suit suit;
	private Rank rank;
	private int value;
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
		setValue();
	}
	private void setValue() {
		switch(rank){
		case TWO:
			value=2;
			break;
		case THREE:
			value=3;
			break;
		case FOUR:
			value=4;
			break;
		case FIVE:
			value=5;
			break;
		case SIX:
			value=6;
			break;
		case SEVEN:
			value=7;
			break;
		case EIGHT:
			value=8;
			break;
		case NINE:
			value=9;
			break;
		case TEN:
			value=10;
			break;
		case JACK:
			value=10;
		case QUEEN:
			value=10;
			break;
		case KING:
			value=10;
			break;
		case ACE:
			value=1;
			break;
	
		}
	}
	public Suit getSuit() {
		return suit;
	}
	public Rank getRank() {
		return rank;
	}
	public int getValue() {
		return value;
	}
}
