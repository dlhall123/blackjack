package com.lmig.gfc.blackjack.models;

public class Player extends Person {
	Wallet wallet;
	Hand splitHand;
	double originalBet;
	double bet;

	public Player(double walletAmount, double betAmount) {
		super();
		wallet = new Wallet(walletAmount);
		this.bet = betAmount;
		setOriginalBet();
	}

	public Wallet getWallet() {
		return wallet;
	}

	public double getWalletAmount() {
		return wallet.getAmount();
	}

	public void setWalletChangeAmount(double amount) {
		wallet.changeWalletAmount(amount);
	}

	// Method to handle split
	public void split() {
		splitHand = new Hand();
		splitHand.addToHand(getFirstCardFromHand());
	}

	public void addToSplitHand(Card card) {
		splitHand.addToHand(card);
	}

	public Hand getSplitHand() {
		return splitHand;
	}

	public boolean isSplitHandBusted() {
		return splitHand.isBusted();
	}

	public int getSplitHandValue() {
		return splitHand.getHandValue();
	}

	public double getBet() {
		return bet;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	public void setBetToOriginal() {
		bet = originalBet;
	}

	public void setOriginalBet() {
		originalBet = bet;
	}

	public boolean isBroke() {
		return getWalletAmount() < originalBet;
	}

	public double getOriginalBet() {
		return originalBet;
	}

}
