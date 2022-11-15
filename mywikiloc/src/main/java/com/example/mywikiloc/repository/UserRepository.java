package com.example.mywikiloc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mywikiloc.model.User;

public interface UserRepository extends MongoRepository <User, Integer> {
	
	public User findByEmail(String email);
	

}
