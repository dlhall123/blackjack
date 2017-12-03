//To-DO:
//Implement Dealer and Player win counters
//Extras: Insurance
//either implement split or remove methods (BlackJack game, Player, Person, and HTML)

package com.lmig.gfc.blackjack.models;

public class BlackjackGame {
	private Shoe cardShoe;
	private Player player;
	private Person dealer;
	private int numDecks;
	private boolean newGame = true;
	private boolean playerTurn = true;

	public BlackjackGame() {
		player = null;
		dealer = null;
	}

	public void newGame(int numDecks, double walletAmount, double betAmount) {
		this.numDecks = numDecks;
		newGame = false;
		cardShoe = new Shoe(numDecks);
		player = new Player(walletAmount, betAmount);
		dealer = new Person();

	}

	public void newHand() {
		player.setBetToOriginal();
		if (playerWalletAmount() < getPlayerBet()) {
			newGame = true;
		} else {
			if (playerWalletAmount() >= getPlayerBet()) {
				walletTransaction(-getPlayerBet());
			}

			if (getNumCardsInDeck() <= 15) {
				cardShoe = new Shoe(numDecks);
			}
			playerTurn = true;
			player.newHand();
			dealer.newHand();
		}
	}

	public void deal() {
		hit(player);
		hit(dealer);
		hit(player);
		hit(dealer);
		if (player.hasBlackjack() || dealer.hasBlackjack()) {
			playerTurn = false;
			payPlayer();
		}
	}

	public void hitPlayer() {
		hit(player);
		if (player.isBusted()) {
			payPlayer();
		}

	}

	public void stand() {
		playerTurn = false;
		while (dealer.getHandValue() < 17) {
			hit(dealer);
		}
		payPlayer();

	}

	private void hit(Person p) {
		Card hitCard = cardShoe.getACard();
		p.addToHand(hitCard);
	}

	public void doubleDown() {
		walletTransaction(-player.getBet());
		player.setBet(getPlayerBet() * 2);
		hitPlayer();
		if (player.getHand().isBusted()) {
			payPlayer();
		} else {
			stand();
		}
	}

	private void payPlayer() {
		playerTurn = false;
		if (isPush()) {
			walletTransaction(getPlayerBet());
		} else if (isPlayerWinner()) {

			if (getPlayerHand().isBlackjack()) {
				walletTransaction((getPlayerBet() * 1.5) + getPlayerBet());
			} else {
				walletTransaction(getPlayerBet() * 2);
			}
		}
	}

	public void setNewBetAmount(double newBet) {
		player.setBet(newBet);
		player.setOriginalBet();
	}

	public Hand getPlayerHand() {
		return player.getHand();
	}

	public Hand getDealerHand() {
		return dealer.getHand();
	}

	public double getPlayerBet() {
		return player.getBet();
	}

	public void walletTransaction(double setAmount) {
		player.setWalletChangeAmount(setAmount);
		;
	}

	public double playerWalletAmount() {
		return player.getWalletAmount();
	}

	public boolean playerDoubleDownAvailable() {
		return (player.getHandSize() == 2 && playerWalletAmount() >= getPlayerBet());
	}

	public int getNumCardsInDeck() {
		return cardShoe.getShoeSize();
	}

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

	public boolean splitIsAvailable() {
		return player.getHandSize() == 2 && player.getCardValue(0) == player.getCardValue(1);
	}

	public boolean isPlayerBroke() {
		return player.isBroke();
	}

}
