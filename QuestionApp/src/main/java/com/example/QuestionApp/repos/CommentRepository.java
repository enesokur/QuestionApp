package com.example.QuestionApp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.QuestionApp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	public List<Comment> findByPostIdAndUserId(Long postId, Long userId);
	public List<Comment> findByPostId(Long postId);
	public List<Comment> findByUserId(Long userId);
}
