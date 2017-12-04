package com.lmig.gfc.blackjack.models;

public class BlackjackGame {
	private Shoe cardShoe;
	private Player player;
	private Person dealer;
	private int numDecks;
	private boolean newGame = true;
	private boolean playerTurn = true;
	private boolean isSplit = false;
	private boolean insurance = false;// Insurance
	private boolean insuranceTaken = false;
	private double amountPaid = 0d;

	public BlackjackGame() {
		player = null;
		dealer = null;
	}

	// Method to handle new game setup
	public void newGame(int numDecks, double walletAmount, double betAmount) {
		// initialize the number of decks of cards to play with
		this.numDecks = numDecks;
		// update newGame flag
		newGame = false;
		// create a new shoe of cards
		cardShoe = new Shoe(numDecks);
		// create new player and dealer objects
		player = new Player(walletAmount, betAmount);
		dealer = new Person();

	}

	// Method for setting up a new hand
	public void newHand() {
		// Set bet amount to the original amount (in case of splits or double down)
		player.setBetToOriginal();
		// start a new game if the wallet amount is less than the player bet
		if (playerWalletAmount() < getPlayerBet()) {
			newGame = true;
		} else {
			if (playerWalletAmount() >= getPlayerBet()) {
				// remove the amount of the bet from the player's wallet
				walletTransaction(-getPlayerBet());
			}
			// create and shuffle a new shoe if 15 or less cards remain in show
			if (getNumCardsInShoe() <= 15) {
				cardShoe = new Shoe(numDecks);
			}
			// set amount paid, playerturn, and isSplit values to initial values
			amountPaid = 0d;
			playerTurn = true;
			isSplit = false;
			insurance = false;
			insuranceTaken = false;
			// create new hands for the player and dealer
			player.newHand();
			dealer.newHand();
		}
	}

	// Method to hit the player and the dealer on initial hand start
	public void deal() {
		// hit the player and the dealer twice each
		hit(player);
		hit(dealer);
		hit(player);
		hit(dealer);
		// If either the player or dealer get a blackjack, game is over, evaluate
		// payments
		if (!insuranceIsAvailable()) {
			if (player.hasBlackjack() || dealer.hasBlackjack()) {
				payPlayer();
			}
		}
	}

	// Method to get a new card for the player hand, until it is busted
	public void hitPlayer() {
		hit(player);
		if (player.isBusted()) {
			payPlayer();
		}

	}

	// method to handle if a player stands (either explicitly or via double down or
	// split)
	public void stand() {
		playerTurn = false;
		// while loop to get dealer cars as long as the hand value of the dealer < 17
		while (dealer.getHandValue() < 17) {
			hit(dealer);
		}

		// At this point, gameplay is over and can evaluate payments
		payPlayer();

	}

	// Method to handle the hit action, based on the person object that is passed in
	private void hit(Person p) {
		// Get a card from the shoe
		Card hitCard = cardShoe.getACard();
		// add the new card to the hand of the person object provided
		p.addToHand(hitCard);
	}

	// Method to handle a player double down
	public void doubleDown() {
		// deduct the amount of the current bet to cover the split
		walletTransaction(-player.getBet());
		// set the bet to the updated amount
		player.setBet(getPlayerBet() * 2);
		// Call the hitPlayer method, since we only get one card after double down
		hitPlayer();
		if (player.isBusted()) {
			// automatically stop if the double down results in a player bust
			payPlayer();
		} else {
			// if player not busted, call the stand method for dealer to get cards
			stand();
		}
	}

	// Method to handle if player wants to split
	public void split() {
		isSplit = true;
		// deduct the amount of the current bet to cover the split
		walletTransaction(-player.getBet());
		// set the bet to the updated amount
		player.setBet(getPlayerBet() * 2);
		// Call the split method on the player
		player.split();
		// call hit method to get a card for the players first hand
		hit(player);
		// add a card to the split hand
		player.addToSplitHand(cardShoe.getACard());
		// no hitting after split implemented, so call the stand method
		stand();
	}

	public void insuranceNo() {
		insurance = true;
		if (player.hasBlackjack() || dealer.hasBlackjack()) {
			payPlayer();
		}

	}

	public void insuranceYes() {
		insurance = true;
		insuranceTaken = true;
		double playerBet = getPlayerBet();
		walletTransaction(-playerBet * 0.5);
		player.setBet(playerBet * 1.5);
		if (dealer.hasBlackjack()) {
			walletTransaction((playerBet * 0.5) * 3);
			amountPaid += (playerBet * 0.5) * 3;
			payPlayer();
		} else if (player.hasBlackjack()) {
			payPlayer();
		}
	}

	private void payPlayer() {
		double playerBet = getPlayerBet();
		if (insuranceTaken) {
			playerBet = (getPlayerBet() / 3) * 2;
		}

		// Set player turn = false so that the appropriate display controls disappear
		playerTurn = false;
		if (isSplit) {
			paySplit();
		}
		// Logic to follow if the hand was not split (only the main hand to evaluate
		else {
			// if hand is a push, add the bet amount to the player wallet
			if (isPush()) {
				amountPaid += playerBet;
				walletTransaction(playerBet);
			} else if (isPlayerWinner()) {
				// pay blackjack at 3:2, or bet * 1.5
				if (getPlayerHand().isBlackjack()) {
					amountPaid += (playerBet * 1.5) + playerBet;
					walletTransaction((playerBet * 1.5) + playerBet);
				}
				// pay all other wins at 1:1
				else {
					amountPaid += playerBet * 2;
					walletTransaction(playerBet * 2);
				}
			}
		}

	}

	private void paySplit() {
		// create local variables to keep track of money for each hand
		double handOnePayAmount = 0d;// local variable to track payments for hand 1
		double handTwoPayAmount = 0d;// local variable to track payments for hand 2

		// There are no special payments for splits (i.e. a 21 value split hand does not
		// pay blackjack)

		// Hand 1 pay evaluation
		if (isPush()) {
			handOnePayAmount = player.getOriginalBet();
		} else if (isPlayerWinner()) {
			handOnePayAmount = player.getOriginalBet() * 2;
		}

		// Hand 2 pay evaluation
		if (isSplitPush()) {
			handTwoPayAmount = player.getOriginalBet();
		} else if (isSplitWinner()) {
			handTwoPayAmount = player.getOriginalBet() * 2;
		}
		// add the payment amount results from both hands to the player wallet
		amountPaid = handOnePayAmount + handTwoPayAmount;
		walletTransaction(handOnePayAmount + handTwoPayAmount);
	}

	// method to handle the user updating bet amount
	public void setNewBetAmount(double newBet) {
		// Set the bet in the player object
		player.setBet(newBet);
		// set the original bet in the player object so that the bet resets correctly
		// for a split or double down
		player.setOriginalBet();
	}

	// Returns the player hand
	public Hand getPlayerHand() {
		return player.getHand();
	}

	// returns a split hand
	public Hand getPlayerSplitHand() {
		return player.getSplitHand();
	}

	// returns the dealer hand
	public Hand getDealerHand() {
		return dealer.getHand();
	}

	// returns the player bet amount
	public double getPlayerBet() {
		return player.getBet();
	}

	// calls a method on the player to update the wallet with a change amount based
	// on bets and payments
	public void walletTransaction(double setAmount) {
		player.setWalletChangeAmount(setAmount);
		;
	}

	// returns the amount in the player's wallet
	public double playerWalletAmount() {
		return player.getWalletAmount();
	}

	// returns a boolean on if a double down is available
	public boolean playerDoubleDownAvailable() {
		return (player.getHandSize() == 2 && playerWalletAmount() >= getPlayerBet());
	}

	// returns the number of cards remaining in a shoe
	public int getNumCardsInShoe() {
		return cardShoe.getShoeSize();
	}

	// determines if the player hand is a push with the dealer hand
	public boolean isPush() {
		if (!playerTurn && !player.isBusted() && !dealer.isBusted()) {
			return player.getHandValue() == dealer.getHandValue();
		} else {
			return false;
		}
	}

	// determines if the player split hand is a push with the dealer hand
	public boolean isSplitPush() {
		if (!playerTurn && !player.isSplitHandBusted() && !dealer.isBusted()) {
			return player.getSplitHandValue() == dealer.getHandValue();
		} else {
			return false;
		}
	}

	// returns the newGame flag
	public boolean isNewGame() {
		return newGame;
	}

	// returns whether or not the player hand won
	public boolean isPlayerWinner() {
		return dealer.isBusted() || (!player.isBusted() && player.getHandValue() > dealer.getHandValue());
	}

	// returns whether or not the player's split hand won
	public boolean isSplitWinner() {
		return dealer.isBusted() || (!player.isSplitHandBusted() && player.getSplitHandValue() > dealer.getHandValue());
	}

	// returns whether or not the dealer won the main hand
	public boolean isDealerWinner() {
		return !isPlayerWinner() && !isPush();
	}

	// returns whether or not the dealer won the split hand
	public boolean isSplitDealerWinner() {
		return !isSplitWinner() && !isSplitPush();
	}

	// returns the playerTurn flag
	public boolean isPlayerTurn() {
		return playerTurn;
	}

	// returns whether or not splitting is currently available to the player
	public boolean splitIsAvailable() {
		return player.getHandSize() == 2 && player.getCardValue(0) == player.getCardValue(1) && !isPlayerBroke()
				&& !isSplit;
	}

	// returns whether or not insurance is available
	public boolean insuranceIsAvailable() {
		return player.getHandSize() == 2 && getDealerHand().getHand().get(0).getValue() == 11 && !insurance;
	}

	// returns if the player is broke
	public boolean isPlayerBroke() {
		return player.isBroke();
	}

}
