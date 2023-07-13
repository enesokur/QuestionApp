package com.example.QuestionApp.business.abstracts;

import java.util.List;

import com.example.QuestionApp.entities.User;
import com.example.QuestionApp.requests.RegisterRequest;

public interface UserService {
	public List<User> getAll();
	public User save(RegisterRequest registerRequest);
	public User getById(Long id);
	public User update(Long userId,User user);
	public void delete(Long id);
	public User getByName(String userName);
}
