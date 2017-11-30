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
	public ModelAndView deal(int numDecks) {
		game = new BlackjackGame();
		game.newGame(numDecks);
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

	@PostMapping("/newHand")
	public ModelAndView newHand() {
		game.newHand();
		return redirectToHome();
	}

	private ModelAndView redirectToHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

}
