package com.example.mywikiloc.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

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
	//@GetMapping()
	// VIEWER
	@GetMapping("/")
	//@RolesAllowed({"VIEWER", "EDITOR","ADMIN" })
	public List<Route> list() {
		return routeService.listAll();
	}
	
	@GetMapping("/test")
	public Map<String, String> sayHello() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("key", "value");
	    map.put("foo", "bar");
	    map.put("aa", "bb");
	    return map;
	}
	
	@GetMapping("/admin")
	public Map<String, String> sayHelloAdmin() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("key", "Admin");
	    map.put("foo", "bar");
	    map.put("aa", "bb");
	    return map;
	}
	
	@GetMapping("/editor")
	public Map<String, String> sayHelloviewer() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("key", "editor");
	    map.put("foo", "bar");
	    map.put("aa", "bb");
	    return map;
	}
	
	
	
	/*
	 * Insert a route via rest and get back the new route with id from db.
	 * curl -v -H "Content-Type: application/json" -d "{\"name\": \"A route via rest \"}" localhost:8080/routes 
	 * 
	 * curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLGpEb2VAbXllbWFpbC5jb20iLCJpc3MiOiJNeVdpa2lMb2MiLCJpYXQiOjE2Njg2MDI3OTIsImV4cCI6MTY2ODY4OTE5Mn0.wyimihfTJv_JmCAyehNN7PuO9uC0Tz4eDdua_Bjb88kRtiHzWZCAILzw3eWZ-DutPibEvEYK7zVgtbjepYVzsA" localhost:8080/routes
	 * eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLGpEb2VAbXllbWFpbC5jb20iLCJpc3MiOiJNeVdpa2lMb2MiLCJpYXQiOjE2Njg2MDI3OTIsImV4cCI6MTY2ODY4OTE5Mn0.wyimihfTJv_JmCAyehNN7PuO9uC0Tz4eDdua_Bjb88kRtiHzWZCAILzw3eWZ-DutPibEvEYK7zVgtbjepYVzsA
	 */
	//@PostMapping()
	@PostMapping()
	//@RolesAllowed({"EDITOR","ADMIN" })
    public ResponseEntity<Route> create(@RequestBody  Route route) {
        Integer routeId = routeService.saveRouteRerutnId(route);
        route.setId(routeId);
        URI routeURI = URI.create("/routes/" + route.getId());
        return ResponseEntity.created(routeURI).body(route);
    }
	
	
	

}
