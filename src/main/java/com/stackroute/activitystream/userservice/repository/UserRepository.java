package com.stackroute.activitystream.userservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.stackroute.activitystream.userservice.model.User;

public interface UserRepository extends CrudRepository<User, String>{

	
}
