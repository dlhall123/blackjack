//To-DO:
//Implement something if the player runs out of cash
//Implement ability to set amount in wallet at game start
//Implement Dealer and Player win counters
//Dealer Up Card/Hole Card - only show up card at start
//change view to use getters
//More testing on Aces 11/1 logic
//Review and clean up code in general
//Extras: Double Down, Insurance

package com.lmig.gfc.blackjack.models;

public class BlackjackGame {
	private Shoe cardShoe;
	private Player player;
	private Person dealer;
	private int cardsInDeck;
	private int numDecks;
	private int bet = 10; // Implement ability to set from user input
	// private int dealerWins;
	// private int playerWins;
	private boolean newGame = true;
	private boolean isGameOver = false;
	private boolean playerWinner = false;
	private boolean dealerWinner = false;
	private boolean push = false;
	private boolean playerTurn = true;

	public BlackjackGame() {
		player = new Player();
		dealer = new Person();

	}

	public void newGame(int numDecks) {
		this.numDecks = numDecks;
		newGame = false;
		cardShoe = new Shoe(numDecks);
		player = new Player();
		dealer = new Person();
		newHand();

	}

	public void newHand() {
		if (getWallet().getAmount() >= bet) {
			setWalletAmount(-bet);
		}

		if (cardsInDeck <= 15) {
			cardShoe = new Shoe(numDecks);
		}
		isGameOver = false;
		playerWinner = false;
		dealerWinner = false;
		playerTurn = true;
		push = false;
		player.newHand();
		dealer.newHand();
		deal();
	}

	private void deal() {
		hit(player);
		hit(dealer);
		hit(player);
		hit(dealer);
		if (getPlayerHand().isBlackjack() || getDealerHand().isBlackjack()) {
			evaluateWinner();
		}
		cardsInDeck = cardShoe.getShoe().size();
	}

	public void hitPlayer() {
		hit(player);
		if (getPlayerHand().isBusted()) {
			evaluateWinner();
		}

	}

	// defect if dealer wins when player stands - no message displayed
	public void stand() {
		playerTurn = false;
		while (dealer.getHand().getHandValue() < 17) {
			hit(dealer);
		}
		evaluateWinner();

	}

	private void hit(Person p) {
		Card hitCard = cardShoe.getCard();
		p.addToHand(hitCard);
		cardsInDeck = cardShoe.getShoe().size();
	}

	// Add in player payment logic
	private void evaluateWinner() {
		boolean playerBusted = getPlayerHand().isBusted();
		boolean dealerBusted = getDealerHand().isBusted();
		boolean playerBlackjack = getPlayerHand().isBlackjack();
		boolean dealerBlackjack = getDealerHand().isBlackjack();
		int dealerHandValue = getDealerHand().getHandValue();
		int playerHandValue = getPlayerHand().getHandValue();

		if (playerBlackjack || dealerBlackjack) {
			if (playerBlackjack && dealerBlackjack) {
				push = true;
				payPlayer();
			}
			if (playerBlackjack) {
				playerWinner = true;
				playerTurn = false;
				payPlayer();
			} else {
				dealerWinner = true;
				playerTurn = false;
			}
		} else if (!playerBusted && !dealerBusted) {
			if (playerHandValue > dealerHandValue) {
				playerWinner = true;
				payPlayer();
			} else if (playerHandValue == dealerHandValue) {
				push = true;
				payPlayer();
			} else {
				dealerWinner = true;
			}
		} else if (dealerBusted) {
			playerWinner = true;
			payPlayer();
		} else {
			dealerWinner = true;
		}

		isGameOver = true;

	}

	private void payPlayer() {
		if (push) {
			setWalletAmount(bet);
		} else if (getPlayerHand().isBlackjack()) {
			setWalletAmount((bet * 1.5) + bet);
		} else {
			setWalletAmount(bet * 2);
		}
	}

	public Hand getPlayerHand() {
		return player.getHand();
	}

	public Hand getDealerHand() {
		return dealer.getHand();
	}

	public Wallet getWallet() {
		return player.getWallet();
	}

	public void setWalletAmount(double setAmount) {
		getWallet().setAmount(getWallet().getAmount() + setAmount);
	}

	public Shoe getCardDeck() {
		return cardShoe;
	}

	public Player getPlayer() {
		return player;
	}

	public Person getDealer() {
		return dealer;
	}

	public int getCardsInDeck() {
		return cardsInDeck;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

}
