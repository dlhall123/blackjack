package com.lmig.gfc.blackjack.models;

public class Player extends Person {
	Wallet wallet;

	public Player(int walletAmount) {
		super();
		wallet = new Wallet(walletAmount);
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

}
