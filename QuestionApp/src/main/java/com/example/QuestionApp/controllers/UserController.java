package com.example.QuestionApp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuestionApp.business.abstracts.UserService;
import com.example.QuestionApp.entities.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
	private UserService userService;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAll();
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.save(user);
	}
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable Long userId) {
		// custom exception
		return userService.getById(userId);
	}
	
	@PutMapping("/{userId}")
	public User updateUserById(@PathVariable Long userId,@RequestBody User user) {
		return userService.update(userId,user);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteById(@PathVariable Long userId) {
		userService.delete(userId);
	}
}
