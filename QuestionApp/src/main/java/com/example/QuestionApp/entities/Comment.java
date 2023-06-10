package com.example.QuestionApp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="comment")
@Data
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@Lob
	@Column(name = "text",columnDefinition = "text")
	private String text;
}
