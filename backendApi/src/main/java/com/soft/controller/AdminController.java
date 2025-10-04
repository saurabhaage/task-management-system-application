package com.soft.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft.entity.User;
import com.soft.service.IuserService;
import com.soft.validation.ExistByEmail;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IuserService userservice;
	
	 @PostMapping("/creatUser")
		public ResponseEntity<Map<String , Object>> register(@RequestBody User user){
			Map<String, Object> response = userservice.saveuser(user);
			if(response.containsKey("saveuser")) {
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			}
			throw new ExistByEmail("Email Already Exist: " + user.getUserEmail());
		
		}
	
	@GetMapping("/allUser")
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.ok(userservice.userlist());
	} 
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		Map<String, Object> result = userservice.verify(user);
		if (result != null) {
	        return ResponseEntity.ok(result);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		userservice.deletById(id);
	    return ResponseEntity.ok("Deleted Successfully");
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user){
		
		return ResponseEntity.ok(userservice.updateUser(id, user));
	}

	
}
