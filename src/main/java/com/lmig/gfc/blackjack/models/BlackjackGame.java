package com.lmig.gfc.blackjack.models;

public class BlackjackGame {
	private Deck cardDeck;
	private Player player;
	private Dealer dealer;
	private int cardsInDeck;
	private boolean isGameOver = true;
	// private Hand playerHand = new Hand();

	public BlackjackGame() {
		player = new Player();
		dealer = new Dealer();

	}

	public void newGame(int numDecks) {
		isGameOver = false;
		cardDeck = new Deck(numDecks);
		player = new Player();
		dealer = new Dealer();
		initialDeal();

	}

	private void initialDeal() {
		hit(player);
		hit(dealer);
		hit(player);
		hit(dealer);
		cardsInDeck = cardDeck.getDeck().size();
	}

	public void hitPlayer() {
		hit(player);
	}

	public void stand() {
		while (dealer.getHand().getHandValue() < 17) {
			hit(dealer);
		}
	}

	private void hit(Person p) {
		Card hitCard = cardDeck.getCard();
		p.addToHand(hitCard);
		cardsInDeck = cardDeck.getDeck().size();
		if (player.getHand().isBusted() || dealer.getHand().isBusted()) {
			isGameOver = true;
		}

	}

	public Hand getPlayerHand() {
		return player.getHand();
	}

	public Hand getDealerHand() {
		return dealer.getHand();
	}

	public Deck getCardDeck() {
		return cardDeck;
	}

	public Player getPlayer() {
		return player;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public int getCardsInDeck() {
		return cardsInDeck;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

}
