package com.example.QuestionApp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.QuestionApp.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
	public List<Like> findByPostIdAndUserId(Long postId, Long userId);
	public List<Like> findByPostId(Long postId);
	public List<Like> findByUserId(Long userId);
}
