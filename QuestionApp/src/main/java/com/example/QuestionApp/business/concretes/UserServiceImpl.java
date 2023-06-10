package com.example.QuestionApp.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.QuestionApp.business.abstracts.UserService;
import com.example.QuestionApp.entities.User;
import com.example.QuestionApp.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Override
	public List<User> getAll() {
		return this.userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User update(Long userId,User user) {
		Optional<User> userfromdb = userRepository.findById(userId);
		if(userfromdb.isPresent()) {
			User founduser = userfromdb.get();
			founduser.setUserName(user.getUserName());
			founduser.setPassword(user.getPassword());
			userRepository.save(founduser);
			return founduser;
		}
		else {
			return null;
		}
		
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	
	
	
}
