package com.stackroute.activitystream.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.userservice.model.User;
import com.stackroute.activitystream.userservice.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll(); 
	}
	
	@GetMapping("/{email:.+}")
	public User getUser(@PathVariable("email") String email) {
		
		return userRepository.findOne(email);
	}
	
	@PostMapping("/")
	public void addUser(@RequestBody User user) {
		
		userRepository.save(user);
	}
	
	@PutMapping("/")
	public void updateUser(@RequestBody User user) {
		
		userRepository.save(user);
	}

	
}
