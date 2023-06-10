package com.example.QuestionApp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.QuestionApp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	public List<Post> findByUserId(Long userId);
}
