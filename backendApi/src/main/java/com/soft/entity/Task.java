package com.soft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;

	 private String title;
	 private String description;
	 
	 @ManyToOne
	 @JoinColumn(name="user_id")
	 @JsonIgnore
	 private User user;
	 
	 private boolean compleated=false;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCompleated() {
		return compleated;
	}

	public void setCompleated(boolean compleated) {
		this.compleated = compleated;
	}
	 
	 
}
