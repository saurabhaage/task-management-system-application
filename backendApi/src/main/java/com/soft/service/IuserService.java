package com.soft.service;

import java.util.List;
import java.util.Map;

import com.soft.entity.User;

public interface IuserService {

	public Map<String, Object> saveuser(User user);
	
	public List<User> userlist();
	
	public Map<String, Object> verify(User user);
	
	public User getUserById(int id);
	
	public void deletById(int id);
	
	public User updateUser(int id , User user);
	
	
}
