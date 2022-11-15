package com.example.mywikiloc.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mywikiloc.model.Route;

public interface RouteRepository extends MongoRepository <Route, Integer> {
	
	public Optional<Route> findById(Integer id);

}
