package com.lmig.gfc.blackjack.models;

public class Wallet {
	private double amount;

	public Wallet(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	// Implement
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void changeWalletAmount(double change) {
		amount += change;
	}

}
