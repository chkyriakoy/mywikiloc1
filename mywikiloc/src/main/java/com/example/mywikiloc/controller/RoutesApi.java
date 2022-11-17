package com.example.mywikiloc.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.mywikiloc.model.Route;
import com.example.mywikiloc.service.RouteService;
import com.example.mywikiloc.service.UserService;

@RestController
@RequestMapping("/routes")
public class RoutesApi {
	
	@Autowired
	private UserService service;
	@Autowired
	private RouteService routeService;
	
	
	// curl -v localhost:8080/routes | json
	@GetMapping()
	public List<Route> list() {
		return routeService.listAll();
	}
	
	
	/*
	 * Insert a route via rest and get back the new route with id from db.
	 * curl -v -H "Content-Type: application/json" -d "{\"name\": \"A route via rest \"}" localhost:8080/routes 
	 * 
	 * curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLGpEb2VAbXllbWFpbC5jb20iLCJpc3MiOiJNeVdpa2lMb2MiLCJpYXQiOjE2Njg2MDI3OTIsImV4cCI6MTY2ODY4OTE5Mn0.wyimihfTJv_JmCAyehNN7PuO9uC0Tz4eDdua_Bjb88kRtiHzWZCAILzw3eWZ-DutPibEvEYK7zVgtbjepYVzsA" localhost:8080/routes
	 * eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLGpEb2VAbXllbWFpbC5jb20iLCJpc3MiOiJNeVdpa2lMb2MiLCJpYXQiOjE2Njg2MDI3OTIsImV4cCI6MTY2ODY4OTE5Mn0.wyimihfTJv_JmCAyehNN7PuO9uC0Tz4eDdua_Bjb88kRtiHzWZCAILzw3eWZ-DutPibEvEYK7zVgtbjepYVzsA
	 */
	@PostMapping
    public ResponseEntity<Route> create(@RequestBody  Route route) {
        Integer routeId = routeService.saveRouteRerutnId(route);
        route.setId(routeId);
        URI routeURI = URI.create("/routes/" + route.getId());
        return ResponseEntity.created(routeURI).body(route);
    }
	

}
