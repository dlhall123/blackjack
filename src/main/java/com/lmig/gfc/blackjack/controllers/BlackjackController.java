package com.lmig.gfc.blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.blackjack.models.BlackjackGame;

@Controller
public class BlackjackController {
	BlackjackGame game;

	public BlackjackController() {
		game = new BlackjackGame();
	}

	@GetMapping("/")
	public ModelAndView defaultPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("default");
		mv.addObject("game", game);
		return mv;
	}

	@PostMapping("/newGame")
	public ModelAndView deal(Integer numDecks, Double walletAmount, Double betAmount) {
		if (numDecks == null) {
			numDecks = 6;
		}
		if (walletAmount == null) {
			walletAmount = 500d;
		}
		if (betAmount == null) {
			betAmount = 50d;
		}
		game = new BlackjackGame();
		game.newGame(numDecks, walletAmount, betAmount);
		game.newHand();
		game.deal();
		return redirectToHome();
	}

	@PostMapping("/changeBet")
	public ModelAndView changeBet(double newBetAmount) {
		game.setNewBetAmount(newBetAmount);
		return redirectToHome();
	}

	@PostMapping("/hit")
	public ModelAndView hit() {
		game.hitPlayer();
		return redirectToHome();
	}

	@PostMapping("/stand")
	public ModelAndView stand() {
		game.stand();
		return redirectToHome();
	}

	@PostMapping("/split")
	public ModelAndView split() {
		game.split();
		return redirectToHome();
	}

	@PostMapping("/newHand")
	public ModelAndView newHand() {
		game.newHand();
		game.deal();
		return redirectToHome();
	}

	@PostMapping("/doubleDown")
	public ModelAndView doubleDown() {
		game.doubleDown();
		return redirectToHome();
	}

	@PostMapping("/brokeNewGame")
	public ModelAndView brokeNewGame() {
		game = new BlackjackGame();
		return redirectToHome();
	}

	@PostMapping("/insuranceNo")
	public ModelAndView insuranceNo() {
		game.insuranceNo();
		return redirectToHome();
	}

	@PostMapping("/insuranceYes")
	public ModelAndView insuranceYes() {
		game.insuranceYes();
		return redirectToHome();
	}

	private ModelAndView redirectToHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

}
