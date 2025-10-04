package com.soft.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.soft.entity.Role;
import com.soft.entity.User;
import com.soft.repository.UserRepository;
import com.soft.service.IuserService;
import com.soft.validation.ExistById;

@Service
public class UserImpl implements IuserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AuthenticationManager authenticationmanager;
	@Autowired
	private JWTService jwtservice;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Map<String, Object> saveuser(User user) {
		Map<String, Object> response= new HashMap<String, Object>();
		User existUser= userRepo.findByUserEmail(user.getUserEmail());
		if(existUser!=null) {
			response.put("statusCode", "404");
			response.put("Message", "Email Already Exist");
		}
		else {
			 String encodedPassword = bCryptPasswordEncoder.encode(user.getUserPassword());
		        user.setUserPassword(encodedPassword);
			User saveUser= userRepo.save(user);
			response.put("statuCode","200");
			response.put("Message", "Create Sucessfullay");
			response.put("saveuser", saveUser);
		}
		return response;
	}

	@Override
	public List<User> userlist() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	public Map<String, Object> verify(User user) {
	    Authentication authentication = authenticationmanager.authenticate(
	            new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPassword())
	    );

	    if (authentication.isAuthenticated()) {
	        String token = jwtservice.generatToken(user);

	        // Fetch full user details from DB
	        User dbUser = userRepo.findByUserEmail(user.getUserEmail());

	        Map<String, Object> response = new HashMap<>();
	        response.put("token", token);
	        response.put("userId", dbUser.getUserId());
	        response.put("userName", dbUser.getUserName());
	        response.put("role", dbUser.getRole());

	        return response; 
	    }

	    return null; // or throw exception for invalid login
	}

	@Override
	public User getUserById(int id) {
		 return userRepo.findById(id)
		            .orElseThrow(() -> new ExistById("User with ID " + id + " not found"));
		
	}

	@Override
	public void deletById(int id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
		
	}

	@Override
	public User updateUser(int id, User user) {
		Optional<User> exists=userRepo.findById(id);
		if(exists.isPresent()) {
			User updatedUser= exists.get();
			updatedUser.setUserName(user.getUserName());
			updatedUser.setUserEmail(user.getUserEmail());
			
//			String encodedPassword = bCryptPasswordEncoder.encode(user.getUserPassword());
//			updatedUser.setUserPassword(encodedPassword);
//			
			updatedUser.setRole(user.getRole());
			return userRepo.save(updatedUser);
		}
		return null;
		
	}

	
	


}
