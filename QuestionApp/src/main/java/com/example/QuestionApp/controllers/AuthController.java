package com.example.QuestionApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuestionApp.business.abstracts.UserService;
import com.example.QuestionApp.entities.User;
import com.example.QuestionApp.requests.LoginRequest;
import com.example.QuestionApp.requests.RegisterRequest;
import com.example.QuestionApp.responses.AuthResponse;
import com.example.QuestionApp.security.JwtTokenProvider;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
	
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	private UserService userService;
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setMessage("Bearer " + jwtToken);
		User userToLogin = userService.getByName(loginRequest.getUserName());
		authResponse.setUserId(userToLogin.getId());
		authResponse.setUserName(userToLogin.getUserName());
		return authResponse;
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
		AuthResponse authResponse = new AuthResponse();
		if(userService.getByName(registerRequest.getUserName()) != null){
			authResponse.setMessage("Username already in use");
			authResponse.setUserId(null);
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}
		User userRegistered = userService.save(registerRequest);
		authResponse.setMessage("User successfully registered");
		authResponse.setUserId(userRegistered.getId());
		authResponse.setUserName(userRegistered.getUserName());
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}
}
