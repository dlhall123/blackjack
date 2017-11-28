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
		game = new BlackjackGame();
		game.newGame();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("default");
		mv.addObject("game", game);
		return mv; 
	}
	
	@PostMapping("/hit")
	public ModelAndView hit() {
		game.hit();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("default");
		mv.addObject("game", game);
		return mv; 
	}

}
