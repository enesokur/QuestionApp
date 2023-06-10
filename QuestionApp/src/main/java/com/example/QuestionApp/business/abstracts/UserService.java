package com.example.QuestionApp.business.abstracts;

import java.util.List;

import com.example.QuestionApp.entities.User;

public interface UserService {
	public List<User> getAll();
	public User save(User user);
	public User getById(Long id);
	public User update(Long userId,User user);
	public void delete(Long id);
}
