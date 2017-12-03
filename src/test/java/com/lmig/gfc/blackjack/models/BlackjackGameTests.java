package com.lmig.gfc.blackjack.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class BlackjackGameTests {

	private BlackjackGame blackjackGame;

	@Before
	public void setUp() {
		blackjackGame = new BlackjackGame();
	}

	@Test
	public void payPlayer_wallet_amount_win_with_blackjack() {
		// Arrange
		blackjackGame.newGame(1, 30, 30);
		blackjackGame.newHand();
		blackjackGame.getPlayerHand().addToHand(new Card(Suit.CLUBS, Rank.ACE));
		blackjackGame.getPlayerHand().addToHand(new Card(Suit.DIAMONDS, Rank.TEN));
		blackjackGame.getDealerHand().addToHand(new Card(Suit.SPADES, Rank.EIGHT));
		blackjackGame.getDealerHand().addToHand(new Card(Suit.SPADES, Rank.EIGHT));
		// Act
		blackjackGame.stand();

		// Assert
		assertThat(blackjackGame.playerWalletAmount()).isEqualTo(75.0);
	}

	@Test
	public void payPlayer_wallet_amount_win_without_blackjack() {
		// Arrange
		blackjackGame.newGame(1, 30, 30);
		blackjackGame.newHand();
		blackjackGame.getPlayerHand().addToHand(new Card(Suit.CLUBS, Rank.NINE));
		blackjackGame.getPlayerHand().addToHand(new Card(Suit.DIAMONDS, Rank.TEN));
		blackjackGame.getDealerHand().addToHand(new Card(Suit.SPADES, Rank.EIGHT));
		blackjackGame.getDealerHand().addToHand(new Card(Suit.SPADES, Rank.NINE));
		// Act
		blackjackGame.stand();

		// Assert
		assertThat(blackjackGame.playerWalletAmount()).isEqualTo(60.0);
	}

	@Test
	public void payPlayer_wallet_amount_push_with_blackjack() {
		// Arrange
		blackjackGame.newGame(1, 30, 30);
		blackjackGame.newHand();
		blackjackGame.getPlayerHand().addToHand(new Card(Suit.CLUBS, Rank.ACE));
		blackjackGame.getPlayerHand().addToHand(new Card(Suit.DIAMONDS, Rank.TEN));
		blackjackGame.getDealerHand().addToHand(new Card(Suit.SPADES, Rank.ACE));
		blackjackGame.getDealerHand().addToHand(new Card(Suit.SPADES, Rank.QUEEN));
		// Act
		blackjackGame.stand();

		// Assert
		assertThat(blackjackGame.playerWalletAmount()).isEqualTo(30);
	}

	@Test
	public void payPlayer_wallet_amount_push_without_blackjack() {
		// Arrange
		blackjackGame.newGame(1, 30, 30);
		blackjackGame.newHand();
		blackjackGame.getPlayerHand().addToHand(new Card(Suit.CLUBS, Rank.EIGHT));
		blackjackGame.getPlayerHand().addToHand(new Card(Suit.DIAMONDS, Rank.TEN));
		blackjackGame.getDealerHand().addToHand(new Card(Suit.SPADES, Rank.EIGHT));
		blackjackGame.getDealerHand().addToHand(new Card(Suit.SPADES, Rank.QUEEN));
		// Act
		blackjackGame.stand();

		// Assert
		assertThat(blackjackGame.playerWalletAmount()).isEqualTo(30);
	}

	@Test
	public void payPlayer_wallet_amount_loss() {
		// Arrange
		blackjackGame.newGame(1, 30, 30);
		blackjackGame.newHand();
		blackjackGame.getPlayerHand().addToHand(new Card(Suit.CLUBS, Rank.SEVEN));
		blackjackGame.getPlayerHand().addToHand(new Card(Suit.DIAMONDS, Rank.TEN));
		blackjackGame.getDealerHand().addToHand(new Card(Suit.SPADES, Rank.EIGHT));
		blackjackGame.getDealerHand().addToHand(new Card(Suit.SPADES, Rank.QUEEN));
		// Act
		blackjackGame.stand();

		// Assert
		assertThat(blackjackGame.playerWalletAmount()).isEqualTo(0);
	}

	@Test
	public void newGame_true_when_wallet_less_than_bet() {
		// Arrange
		blackjackGame.newGame(1, 30, 40);

		// Act
		blackjackGame.newHand();

		// Assert
		assertThat(blackjackGame.isNewGame()).isEqualTo(true);
	}

	@Test
	public void newGame_false_when_wallet_more_than_bet() {
		// Arrange
		blackjackGame.newGame(1, 40, 30);

		// Act
		blackjackGame.newHand();

		// Assert
		assertThat(blackjackGame.isNewGame()).isEqualTo(false);
	}

	@Test
	public void newGame_false_when_wallet_equal_to_bet() {
		// Arrange
		blackjackGame.newGame(1, 40, 40);

		// Act
		blackjackGame.newHand();

		// Assert
		assertThat(blackjackGame.isNewGame()).isEqualTo(false);
	}
}
