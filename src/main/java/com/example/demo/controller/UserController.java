package com.example.demo.controller;

import java.io.Console;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ConfirmationToken;
import com.example.demo.entity.User;
import com.example.demo.service.ConfirmationTokenService;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@GetMapping("/sign-in")
	String signIn() {

		return "sign-in";
	}

	@GetMapping("/sign-up")
	String signUp() {
		System.out.println("Signup called");
		return "sign-up";
	}

	@PostMapping("/sign-up")
	String signUp(User user) {
		System.out.println("called");
		userService.signUpUser(user);

		return "redirect:/sign-in";
	}

	@GetMapping("/sign-up/confirm")
	String confirmMail(@RequestParam("token") String token) {

		Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService
				.findConfirmationTokenByToken(token);

		optionalConfirmationToken.ifPresent(userService::confirmUser);

		return "redirect:/sign-in";
	}
}
