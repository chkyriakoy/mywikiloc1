package com.example.mywikiloc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.mywikiloc.model.Route;
import com.example.mywikiloc.model.User;
import com.example.mywikiloc.repository.RouteRepository;

@Service
public class RouteService {
	
	@Autowired
	private RouteRepository routeRepo;
	
	@Autowired
	private MongoOperations routeMongoRepo;
	
	@Autowired
	private SequenceGeneratorService seqService;
	
	
	
	public List<Route> listAll(){
		return routeRepo.findAll();
	}
	
	public List<Route> getAllUserRoutes(Integer id){
		List<Route> routes = new ArrayList<Route>();
		Query query = new Query();
		query.addCriteria(Criteria.where("usersId").is(id));
		
		routes = routeMongoRepo.find(query, Route.class);
		
		
		
		return routes;
	}
	
	
	public String saveRoute(Route route) {
		String msg = "";
		int id = seqService.getSequenceNumber(Route.SEQUENCE_NAME);
		if(id!=-1) {
			route.setId(id);
			routeRepo.save(route);	
			msg = "OK";
		}
		else{
			msg = "Cannot increment  route_sequence";
			return msg;
		}
		
		return msg;
	}

}
