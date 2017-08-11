package com.stackroute.activitystream.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.userservice.model.User;
import com.stackroute.activitystream.userservice.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/user")
@Api(value="ActivityStream",description="Contains all user management operations")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	@ApiOperation(value = "View a list of users", response = Iterable.class)
	public List<User> getAllUsers() {
		
		List<User> users=(List<User>) userRepository.findAll();
		for(User user:users) {
			
			Link selfLink=linkTo(methodOn(UserController.class).getUser(user.getEmail())).withSelfRel();
			
			user.add(selfLink);
		}
		
		return (List<User>) userRepository.findAll(); 
	}
	
	@ApiOperation(value = "View a specific user", response = Iterable.class)
	@GetMapping("/{email:.+}")
	public User getUser(@PathVariable("email") String email) {
		
		return userRepository.findOne(email);
	}
	
	@PostMapping("/")
	@ApiOperation(value = "Add a new user", response = Iterable.class)
	public ResponseEntity<Void> addUser(@RequestBody User user) {
		
		if(userRepository.findOne(user.getEmail())!=null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		else {
			userRepository.save(user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/")
	@ApiOperation(value = "Update an user(if exists)", response = Iterable.class)
	public ResponseEntity<Void> updateUser(@RequestBody User user) {
		
		if((userRepository.findOne(user.getEmail()))==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			userRepository.save(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		
	}

	
}
