package com.example.QuestionApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.QuestionApp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUserName(String userName);
}
