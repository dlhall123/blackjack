package com.lmig.gfc.blackjack.models;

public class Player extends Person {
	Wallet wallet;

	public Player() {
		super();
		wallet = new Wallet(100);
	}

	public Wallet getWallet() {
		return wallet;
	}

}
