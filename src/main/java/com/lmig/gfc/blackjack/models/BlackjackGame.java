//To-DO:
//Implement Dealer and Player win counters
//change view to use getters
//Review and clean up code in general
//Extras: Double Down, Insurance
//See if flags are derivable from somewhere else
//make methods to have player manage wallet

package com.lmig.gfc.blackjack.models;

public class BlackjackGame {
	private Shoe cardShoe;
	private Player player;
	private Person dealer;
	private int numDecks;
	private int bet;
	private int originalBet;
	// private int dealerWins;
	// private int playerWins;
	private boolean newGame = true;
	// private boolean playerWinner = false;
	// private boolean dealerWinner = false;
	private boolean playerTurn = true;

	public BlackjackGame() {
		player = null;
		dealer = null;
	}

	public void newGame(int numDecks, int walletAmount, int betAmount) {
		this.numDecks = numDecks;
		this.bet = betAmount;
		originalBet = betAmount;
		newGame = false;
		cardShoe = new Shoe(numDecks);
		player = new Player(walletAmount);
		dealer = new Person();
		newHand();

	}

	public void newHand() {
		bet = originalBet;
		if (playerWalletAmount() < bet) {
			newGame = true;
		} else {
			if (playerWalletAmount() >= bet) {
				walletTransaction(-bet);
			}

			if (getCardsInDeck() <= 15) {
				cardShoe = new Shoe(numDecks);
			}
			// playerWinner = false;
			// dealerWinner = false;
			playerTurn = true;
			player.newHand();
			dealer.newHand();
			deal();
		}
	}

	private void deal() {
		hit(player);
		hit(dealer);
		hit(player);
		hit(dealer);
		if (player.hasBlackjack() || dealer.hasBlackjack()) {
			playerTurn = false;
			evaluateWinner();
		}
	}

	public void hitPlayer() {
		hit(player);
		if (player.isBusted()) {
			evaluateWinner();
		}

	}

	public void stand() {
		playerTurn = false;
		while (dealer.getHandValue() < 17) {
			hit(dealer);
		}
		evaluateWinner();

	}

	private void hit(Person p) {
		Card hitCard = cardShoe.getACard();
		p.addToHand(hitCard);
	}

	private void evaluateWinner() {
		playerTurn = false;
		payPlayer();
		// if (isPush()) {
		// payPlayer();
		// } else if (dealer.isBusted() || (!player.isBusted() && player.getHandValue()
		// > dealer.getHandValue())) {
		// payPlayer();
		// playerWinner = true;
		// } else {
		// dealerWinner = true;
		// }

		// else if (player.hasBlackjack() || dealer.hasBlackjack()) {
		// if (player.hasBlackjack()) {
		// playerWinner = true;
		// payPlayer();
		// } else {
		// dealerWinner = true;
		// }
		// } else if (!player.isBusted() && !dealer.isBusted()) {
		// if (player.getHandValue() > dealer.getHandValue()) {
		// playerWinner = true;
		// payPlayer();
		// } else {
		// dealerWinner = true;
		// }
		// } else if (dealer.isBusted()) {
		// playerWinner = true;
		// payPlayer();
		// }

	}

	public void doubleDown() {
		walletTransaction(-bet);
		bet += bet;
		hitPlayer();
		if (player.getHand().isBusted()) {
			evaluateWinner();
		} else {
			stand();
		}
	}

	private void payPlayer() {
		if (isPush()) {
			walletTransaction(bet);
		} else if (isPlayerWinner()) {

			if (getPlayerHand().isBlackjack()) {
				walletTransaction((bet * 1.5) + bet);
			} else {
				walletTransaction(bet * 2);
			}
		}
	}

	public void setOriginalBet(int originalBet) {
		this.originalBet = originalBet;
		bet = originalBet;
	}

	public Hand getPlayerHand() {
		return player.getHand();
	}

	public Hand getDealerHand() {
		return dealer.getHand();
	}

	public void walletTransaction(double setAmount) {
		player.setWalletChangeAmount(setAmount);
		;
	}

	public double playerWalletAmount() {
		return player.getWalletAmount();
	}

	public boolean playerDoubleDownAvailable() {
		return (player.getHandSize() == 2 && playerWalletAmount() >= bet);
	}

	public int getCardsInDeck() {
		return cardShoe.getShoeSize();
	}

	// public boolean isHandOver() {
	// return dealerWinner || playerWinner || isPush();
	// }

	public boolean isPush() {
		if (!playerTurn && !player.isBusted() && !dealer.isBusted()) {
			return player.getHandValue() == dealer.getHandValue();
		} else {
			return false;
		}
	}

	public boolean isNewGame() {
		return newGame;
	}

	public boolean isPlayerWinner() {
		return dealer.isBusted() || (!player.isBusted() && player.getHandValue() > dealer.getHandValue());
	}

	public boolean isDealerWinner() {
		return !isPlayerWinner() && !isPush();
	}

	public boolean isPlayerTurn() {
		return playerTurn;
	}

}
